package com.lentach.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lentach.R;
import com.lentach.ui.adapters.FavoritesRVAdapter;
import com.lentach.repository.RealmUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lentach.ui.activities.BaseActivity.realm;
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
