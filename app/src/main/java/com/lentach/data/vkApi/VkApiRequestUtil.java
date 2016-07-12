package com.lentach.data.vkApi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lentach.R;
import com.lentach.models.wallpost.Likes;
import com.lentach.models.wallpost.Post;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Илья on 12.07.2016.
 */

public class VkApiRequestUtil {

    private static VkApiRequestUtil vkApiRequestUtil;

    public static VkApiRequestUtil init() {
        if (vkApiRequestUtil == null) {
            vkApiRequestUtil = new VkApiRequestUtil();
        }
        return vkApiRequestUtil;
    }

    public static void getUserInfo(final Activity activity){
        final int[] userId = {1};
        if(VKAccessToken.currentToken()!=null) {
            String userToken = VKAccessToken.currentToken().accessToken;
            VKRequest vkRequest = new VKRequest("users.get", VKParameters.from(VKApiConst.ACCESS_TOKEN,
                    userToken, VKApiConst.FIELDS, "first_name,last_name,photo_200,domain"));
            vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                    super.attemptFailed(request, attemptNumber, totalAttempts);
                }

                @Override
                public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                    super.onProgress(progressType, bytesLoaded, bytesTotal);
                }

                @Override
                public void onComplete(VKResponse response) {
                    super.onComplete(response);
                    Gson gson = new Gson();
                    try {
                        JSONArray jsonObject = (JSONArray) response.json.get("response");
                        String s = jsonObject.getJSONObject(0).get("id").toString();
                        String firstName = jsonObject.getJSONObject(0).get("first_name").toString();
                        String lastName = jsonObject.getJSONObject(0).get("last_name").toString();
                        String domain = jsonObject.getJSONObject(0).get("domain").toString();
                        String avatarImage = jsonObject.getJSONObject(0).get("photo_200").toString();

                        userId[0] = gson.fromJson(s, new TypeToken<Integer>() {
                        }.getType());
                        SharedPreferences sharedPreferences = activity.getSharedPreferences("Default", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sharedPreferences.edit();
                        ed.putInt(VKApiConst.USER_ID, userId[0]);
                        ed.putString("first_name", firstName);
                        ed.putString("last_name", lastName);
                        ed.putString("domain", domain);
                        ed.putString("avatar_image", avatarImage);
                        ed.apply();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VKError error) {
                    super.onError(error);
                    String errorr = error.toString();
                    int a = 5;
                }
            });
        }
    }

    public static void isPostLiked(final onIsLikedResult listener,Context context,int postId,int userId){


        VKRequest vkRequest = new VKRequest("likes.isLiked", VKParameters.from(VKApiConst.USER_ID,
               userId,"type","post",VKApiConst.OWNER_ID,context.getResources().getInteger(R.integer.group_id),
                "item_id",postId) );

        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("liked").toString();
                    int likes = gson.fromJson( s, new TypeToken<Integer>(){}.getType());
                    int a =5;
                    boolean isLiked=false;
                    if(likes>0)
                        isLiked = true;
                    listener.onIsLikedResult(isLiked);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });

    }

    public  void addLikeToPost(Context context, final onAddLikesResult listener,int postId){

        VKRequest addLikeRequest = new VKRequest
                ("likes.add",
                        VKParameters.from("type","post",
                                VKApiConst.OWNER_ID, context.getResources().getInteger(R.integer.group_id),
                                "item_id",postId)
                );

        addLikeRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("likes").toString();
                    int likes = gson.fromJson( s, new TypeToken<Integer>(){}.getType());
                    listener.onAddLikesResult(likes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);


            }
        });
    }

    public  void deleteLikeFromPost(Context context, final onAddLikesResult listener,int postId){

        VKRequest addLikeRequest = new VKRequest
                ("likes.delete",
                        VKParameters.from("type","post",
                                VKApiConst.OWNER_ID, context.getResources().getInteger(R.integer.group_id),
                                "item_id",postId)
                );

        addLikeRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("likes").toString();
                    int likes = gson.fromJson( s, new TypeToken<Integer>(){}.getType());
                    listener.onAddLikesResult(likes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);


            }
        });
    }

    public static interface onIsLikedResult {
        public void onIsLikedResult(boolean isLiked);
    }

    public static interface onAddLikesResult {
        public void onAddLikesResult(int likesCount);
    }


}
