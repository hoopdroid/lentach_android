package com.lentach.data;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lentach.R;
import com.lentach.components.IDCommentsComporator;
import com.lentach.components.IDUsersComporator;
import com.lentach.data.serverApi.ServerApiManager;
import com.lentach.models.comment.Comment;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallcomments.users.User;
import com.lentach.models.wallpost.Attachment;
import com.lentach.models.wallpost.Likes;
import com.lentach.models.wallpost.Photo;
import com.lentach.models.wallpost.Post;
import com.lentach.models.webapipost.WebAPIPost;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKApiPost;
import com.vk.sdk.api.model.VKApiVideo;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ilyas on 6/22/2016.
 */

public class DataServiceSingleton {

    private static DataServiceSingleton dataServiceSingleton;

    public static DataServiceSingleton init() {
        if (dataServiceSingleton == null) {
            dataServiceSingleton = new DataServiceSingleton();
        }
        return dataServiceSingleton;
    }

    public void getPostsFromWall(final Context context, final onRequestResult listener,int count) {

        VKRequest vkRequest = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID,context.getResources().getInteger(R.integer.group_id),VKApiConst.COUNT,count));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();
                String s="";
                JSONObject jsonObject = null;
                try {
                    jsonObject = (JSONObject) response.json.get("response");

                s = jsonObject.get("items").toString();
                } catch (JSONException e) {
                e.printStackTrace();
                }
                    List<Post> posts= gson.fromJson( s, new TypeToken<ArrayList<Post>>(){}.getType());
                    VKList<VKApiPost> postsApi = new VKList<>(response.json, VKApiPost.class);


                    VKAttachments.VKApiAttachment vkAttachment = postsApi.get(0).attachments.get(0);
                    List<VKApiPost> vkApiPosts = new ArrayList<VKApiPost>();
                    vkApiPosts.addAll(postsApi);


                    listener.onRequestResult(posts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(context, R.string.network_error_message,Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getCommentsFromWallPost(final Context context, final onRequestCommentsResult listener, int postId, int count) {

        VKRequest request1234 = new VKRequest
                ("wall.getComments",
                        VKParameters.from(VKApiConst.OWNER_ID,
                                context.getResources().getInteger(R.integer.group_id),VKApiConst.POST_ID,postId,
                                "need_likes","1","count",count,"extended","1"));
        request1234.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();

                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("items").toString();
                    String d = jsonObject.get("profiles").toString();

                    List<WallComment> wallComments = gson.fromJson( s, new TypeToken<ArrayList<WallComment>>(){}.getType());
                    List<User> usersList = gson.fromJson( d, new TypeToken<ArrayList<User>>(){}.getType());
                    Collections.sort(wallComments, new IDCommentsComporator());
                    Collections.sort(usersList, new IDUsersComporator());

                    // Передаем пользователей-комментаторов и сами комментарии
                    listener.onRequestCommentsResult(wallComments,usersList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(context,R.string.network_error_message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getVideoFromId(final Context context, final onRequestVideoFromId  listener, int videoId) {

        VKRequest request1234 = new VKRequest
                ("video.get",
                        VKParameters.from(VKApiConst.OWNER_ID,
                                context.getResources().getInteger(R.integer.group_id),
                                "videos",context.getResources().getInteger(R.integer.group_id) + "_" + videoId,VKApiConst.EXTENDED,"1"));
        request1234.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Gson gson = new Gson();

                try {
                    JSONObject jsonObject = (JSONObject) response.json.get("response");
                    String s = jsonObject.get("items").toString();
                    VKList<VKApiVideo> videos = new VKList<>(response.json, VKApiVideo.class);


                    listener.onRequestVideoFromId(videos.get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Toast.makeText(context,R.string.network_error_message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getBestPostsFromServer(final onRequestWebApiResult listener) {

        ServerApiManager.getApiService().getBestPosts(new Callback<List<WebAPIPost>>() {
            @Override
            public void success(List<WebAPIPost> webApiPosts, Response response) {
                // Посты десереализуются в модель WebAPIPost (old api) и пересоздаются в Post модель (vk sdk)

                List<Post> postList  = new ArrayList<Post>();

                for (int i = 0; i < webApiPosts.size() ; i++) {

                    ArrayList<Attachment> listAttach = new ArrayList<Attachment>();
                    if(webApiPosts.get(i).getAttachment()!=null&&webApiPosts.get(i).getAttachment().photo!=null){
                        Photo photo = new Photo(webApiPosts.get(i).getAttachment().photo.srcBig);
                        Attachment attach = new Attachment("photo",photo);
                        listAttach.add(attach);
                    }
                          postList.add(new Post(
                                  webApiPosts.get(i).getId(),
                                  webApiPosts.get(i).getFromId(),
                                  webApiPosts.get(i).getToId(),
                                  webApiPosts.get(i).getPostType(),
                                  webApiPosts.get(i).getDate(),
                                  webApiPosts.get(i).getText(),
                                  0, //isPinned
                                  listAttach,
                                  new Likes(webApiPosts.get(i).getLikes().count)));

                }
                listener.onRequestWebApiResult(postList);
            }

            @Override
            public void failure(RetrofitError error) {
                String errorMessage = error.toString(); // TODO Обработка ошибки

            }
        });
    }


    public void getCommentsOfDayFromServer(final onRequestCommentsOFDayResult listener) {

        ServerApiManager.getApiService().getBestComments(new Callback<List<Comment>>() {

            @Override
            public void success(List<Comment> wallComments, Response response) {

                if(wallComments.size()>0) {
                    Collections.reverse(wallComments);
                    listener.onRequestCommentsResult(wallComments);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO Обработка ошибки Retrofit

            }
        });
    }


    public interface onRequestResult {
        public void onRequestResult(List<Post> posts);
    }

    public interface onRequestWebApiResult {
        public void onRequestWebApiResult(List<Post> posts);
    }

    public  interface onRequestCommentsResult {
        public void onRequestCommentsResult(List<WallComment> posts,List<User> arr3);
    }
    public  interface onRequestCommentsOFDayResult {
        public void onRequestCommentsResult(List<Comment> posts);
    }
    public  interface onRequestVideoFromId {
        public void onRequestVideoFromId (VKApiVideo vkApiVideo);
    }
}
