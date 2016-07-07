package com.lentach.data;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lentach.R;
import com.lentach.components.CommentsComporator;
import com.lentach.components.IDCommentsComporator;
import com.lentach.components.IDUsersComporator;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallcomments.users.User;
import com.lentach.models.wallpost.Post;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ilyas on 6/22/2016.
 */

public class DataService {

    private static DataService dataService;

    public static DataService init() {
        if (dataService == null) {
            dataService = new DataService();
        }
        return dataService;
    }

    public void getPostsFromWall(final Context context, final onRequestResult listener) {

        VKRequest vkRequest = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID,-29534144,VKApiConst.COUNT,100));
       // VKRequest request123 = new VKRequest("wall.search", VKParameters.from(VKApiConst.OWNER_ID,-29534144,"query","#радиолентач"));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();
                JSONObject innerJson;
                try {

                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("items").toString();
                    List<Post> arr2= gson.fromJson( s, new TypeToken<ArrayList<Post>>(){}.getType());

                    listener.onRequestResult(arr2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getCommentsFromServer(final Context context, final onRequestCommentsResult listener,int postId) {

        VKRequest request1234 = new VKRequest("wall.getComments", VKParameters.from(VKApiConst.OWNER_ID,context.getResources().getInteger(R.integer.group_id),VKApiConst.POST_ID,postId,"need_likes","1","count",20,"extended","1"));
        request1234.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();

                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("items").toString();
                    String d = jsonObject.get("profiles").toString();

                    List<WallComment> arr2= gson.fromJson( s, new TypeToken<ArrayList<WallComment>>(){}.getType());
                    List<User> arr3= gson.fromJson( d, new TypeToken<ArrayList<User>>(){}.getType());
                    int a = 5;
                    Collections.sort(arr2, new IDCommentsComporator());
                    Collections.sort(arr3, new IDUsersComporator());

                    listener.onRequestCommentsResult(arr2,arr3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static interface onRequestResult {
        public void onRequestResult(List<Post> posts);
    }

    public static interface onFavoritesResult {
        public void onFavoritesResult(List<Post> posts);
    }
    public static interface onRequestCommentsResult {
        public void onRequestCommentsResult(List<WallComment> posts, List<User> arr3);
    }
}
