package com.lentach.fragments;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lentach.R;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

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
