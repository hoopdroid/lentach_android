package com.lentach.ui.fragments;


import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

/**
 * Created by ilyas on 6/28/2016.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        ButterKnife.bind(getActivity());
    }


}
