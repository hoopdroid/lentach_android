package com.lentach.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import com.lentach.R;
import com.lentach.api.models.wallpost.Post;
import com.lentach.util.AnimationBuilderHelper;

import java.util.regex.Pattern;

/**
 * Created by ilyas on 6/26/2016.
 */

public class PostInfoFragment extends BaseFragment {

    private static final String ARG_TEXT_POST_PARAM = "param1";

    private Post mPost;

    private TextView tabText;
    private Button mLikeView;
    private Button mViewsView;
    private Button mShareView;


    public PostInfoFragment() {
        // Required empty public constructor
    }

    public static PostInfoFragment newInstance(Parcelable param1) {
        PostInfoFragment fragment = new PostInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TEXT_POST_PARAM, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPost = getArguments().getParcelable(ARG_TEXT_POST_PARAM);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_sample, container, false);

        Animation animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.up_from_bottom);
        animation.setStartOffset(400);
        convertView.startAnimation(animation);


        initViewElements(convertView);


        return convertView;
    }

    private void initViewElements(View convertView) {
        tabText= (TextView) convertView.findViewById(R.id.postText);
        tabText.setText(mPost.getText());

        mLikeView = (Button) convertView.findViewById(R.id.shot_like_count);
        mLikeView.setText(String.valueOf(mPost.getLikes().getCount())+" лайков");
        mViewsView = (Button) convertView.findViewById(R.id.shot_view_count);
        mViewsView.setText(String.valueOf(String.valueOf(mPost.getLikes().getCount()*6))+" просмотра");
        mShareView = (Button) convertView.findViewById(R.id.shot_share_action);

        mShareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mPost.getText());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Поделиться лентачом"));
            }
        });


        addClickableLinkToText(tabText);
        AnimationBuilderHelper.startIntroViewAnimation(getActivity(),tabText);
        AnimationBuilderHelper.startIntroViewAnimation(getActivity(),mLikeView );
        AnimationBuilderHelper.startIntroViewAnimation(getActivity(),mViewsView );
        AnimationBuilderHelper.startIntroViewAnimation(getActivity(), mShareView );


    }

    private void addClickableLinkToText(TextView postText) {
        postText.setLinksClickable(true);
        Pattern httpPattern = Pattern.compile("[a-z]+:\\/\\/[^ \\n]*");
        Linkify.addLinks(postText, httpPattern,"");
    }


}
