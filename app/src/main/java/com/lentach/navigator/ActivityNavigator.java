package com.lentach.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.lentach.MainActivity;
import com.lentach.PhotoViewActivity;
import com.lentach.PostActivity;
import com.lentach.VKPermissionsActivity;
import com.lentach.components.Constants;


/**
 * Activity navigation.
 */
public class ActivityNavigator {

    public static void startPostActivity(Activity context, Parcelable parcelable, View imageView) {

        Intent intent = new Intent(context, PostActivity.class);

        intent.putExtra(Constants.POST_EXTRA, parcelable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(Constants.ARTIST_TRANSITION);
            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(context, pair1);
            context.startActivity(intent, options.toBundle());
        } else
            context.startActivity(intent);
    }

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startPhotoActivity(Context context,String photoUrl){
        Intent intent = new Intent(context, PhotoViewActivity.class);

        intent.putExtra("Photo",photoUrl);
        context.startActivity(intent);
    }
    public static void startVKPermissionActivity(Context context){
        Intent intent = new Intent(context, VKPermissionsActivity.class);
        context.startActivity(intent);
    }

}

