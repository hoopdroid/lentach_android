package com.lentach.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.lentach.R;

import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by ilyas on 6/26/2016.
 */

public class PostInfoFragment extends BaseFragment {

    private static final String ARG_TEXT_POST_PARAM = "param1";

    private String mPostText;

    private TextView tabText;

    private CardView cv;


    public PostInfoFragment() {
        // Required empty public constructor
    }

    public static PostInfoFragment newInstance(String param1) {
        PostInfoFragment fragment = new PostInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT_POST_PARAM, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPostText = getArguments().getString(ARG_TEXT_POST_PARAM);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_sample, container, false);
        initViewElements(convertView);

        return convertView;
    }

    private void initViewElements(View convertView) {
        tabText= (TextView) convertView.findViewById(R.id.postText);
        tabText.setText(mPostText);
        {
            tabText.setLinksClickable(true);
            Pattern httpPattern = Pattern.compile(
                    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            Linkify.addLinks(tabText, httpPattern,"");
        }
        cv = (CardView)convertView.findViewById(R.id.cv);
    }


}
