package com.lentach.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lentach.MainActivity;
import com.lentach.R;
import com.lentach.adapters.CommentsRVAdapter;
import com.lentach.adapters.FavoritesRVAdapter;
import com.lentach.adapters.PostsRVAdapter;
import com.lentach.components.CommentsComporator;
import com.lentach.data.DataServiceSingleton;
import com.lentach.db.RealmUtils;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallcomments.users.User;
import com.lentach.navigator.ActivityNavigator;
import com.lentach.util.ScreenOrientationHelper;
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
import static com.lentach.BaseActivity.realm;
import static com.vk.sdk.VKUIHelper.getApplicationContext;

/**
 * Created by ilyas on 6/26/2016.
 */

public class FavoritesFragment extends Fragment {

    private static final String ARG_COMMENTS_PARAM = "arg_comments_param";
    private static final String ARG_POSTID_PARAM = "arg_postid_param";

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_favorites, container, false);

        initViewElements(convertView);

        getFavorites();

        return convertView;
    }

    private void initViewElements(View convertView) {
        ButterKnife.bind(this, convertView);

        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager stagManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(stagManager);
    }

    //TODO Либо сделать адаптер для модели из Реалма,либо получать из Реалма уж в PostModel
    protected void getFavorites(){
        FavoritesRVAdapter mArtistsRVAdapter = new FavoritesRVAdapter(getApplicationContext(),
                RealmUtils.getAllPostsFromDB(realm));
        mRecyclerView.setAdapter(mArtistsRVAdapter);
    }
}
