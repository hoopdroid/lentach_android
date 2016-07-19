package com.lentach.fragments;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lentach.R;
import com.lentach.adapters.CommentsRVAdapter;
import com.lentach.components.CommentsComporator;
import com.lentach.data.DataServiceSingleton;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallcomments.users.User;
import com.lentach.navigator.ActivityNavigator;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.vk.sdk.VKUIHelper.getApplicationContext;

/**
 * Created by ilyas on 6/26/2016.
 */

public class PostCommentsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_COMMENTS_PARAM = "arg_comments_param";
    private static final String ARG_POSTID_PARAM = "arg_postid_param";
    private int postId;

    private ArrayList<WallComment> mCommentsList;
    private List<User> usersList;;
    private CommentsRVAdapter mCommentsRVAdapter;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.sendCommentBtn)
    ImageView mSendCommentBtn;
    @Bind(R.id.commentEditText)
    EditText mEditTextComment;
    @Bind(R.id.commentView)
    RelativeLayout commentView;
    @Bind(R.id.gotoVkAuthBtn)
    Button mAuthBtn;

    private String token;

    public PostCommentsFragment() {
        // Required empty public constructor
    }

    public static PostCommentsFragment newInstance(ArrayList<WallComment> param1,int postId) {
        PostCommentsFragment fragment = new PostCommentsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_COMMENTS_PARAM, param1);
        args.putInt(ARG_POSTID_PARAM,postId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mCommentsList = getArguments().getParcelableArrayList(ARG_COMMENTS_PARAM);
            postId = getArguments().getInt(ARG_POSTID_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_comments, container, false);

        initViewElements(convertView);

        getCommentsData();

        if(VKAccessToken.currentToken()==null) {
            commentView.setVisibility(View.GONE);
            mAuthBtn.setVisibility(View.VISIBLE);
            mAuthBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityNavigator.startVKPermissionActivity(getActivity());
                }
            });
        }
        else{
            commentView.setVisibility(View.VISIBLE);
            mAuthBtn.setVisibility(View.GONE);
            mSendCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendComment();
                }

            });
        }

        return convertView;
    }
    private void sendComment() {
        SharedPreferences sPref = getActivity().getPreferences(MODE_PRIVATE);
        String token = sPref.getString("VKAccessToken", "");
        Toast.makeText(getActivity(), token, Toast.LENGTH_SHORT).show();

        String commentText = " ";
        if(mEditTextComment.getText()!=null)
            commentText = mEditTextComment.getText().toString();
        else Toast.makeText(getActivity(), "Enter comment message!", Toast.LENGTH_SHORT).show();

        VKRequest createComment = new VKApiWall().addComment(VKParameters.from(VKApiConst.OWNER_ID,-29534144,VKApiConst.POST_ID,postId,VKApiConst.MESSAGE,commentText,VKApiConst.ACCESS_TOKEN,token));
        createComment.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                Toast.makeText(getActivity(), "OK!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(getActivity(), "NOT OK!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViewElements(View convertView) {
        ButterKnife.bind(this, convertView);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void getCommentsData() {

        DataServiceSingleton.init().getCommentsFromWallPost(getActivity(),new DataServiceSingleton.onRequestCommentsResult() {
            @Override
            public void onRequestCommentsResult(List<WallComment> posts, List<User> arr3) {

                for (int i = 0; i < posts.size() ; i++) {
                    if(arr3.size()>i)
                        posts.get(i).setUsername(arr3.get(i));
                }

                Collections.sort(posts,new CommentsComporator());

                mCommentsRVAdapter = new CommentsRVAdapter(getApplicationContext(),
                        posts);
                mRecyclerView.setAdapter(mCommentsRVAdapter);
                mCommentsRVAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }


        },postId,10);

    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getCommentsData();
    }
}
