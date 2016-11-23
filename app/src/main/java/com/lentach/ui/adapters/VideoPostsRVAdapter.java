package com.lentach.ui.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.lentach.R;
import com.lentach.api.DataService;
import com.lentach.api.models.wallpost.Post;
import com.lentach.api.vkApi.VkApiManager;
import com.lentach.router.ActivityRouter;
import com.lentach.util.FormatUtil;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.model.VKApiVideo;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An adapter for the list of Posts
 */
public class VideoPostsRVAdapter extends RecyclerView.Adapter<VideoPostsRVAdapter.ViewHolder> {

    private List<Post> postsList;
    private final Context context;
    private boolean isPostLikedByUser;
    private String userIdString;

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public VideoPostsRVAdapter(Context context, List<Post> PostsList) {
        this.context = context;
        this.postsList = PostsList;
        removeUnusedPosts();
    }

    private void removeUnusedPosts() {
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getIsPinned() != null && postsList.get(i).getIsPinned() == 1) {
                postsList.remove(i);
                continue;
            }
            if (postsList.get(i).getAttachments() != null && postsList.get(i).getAttachments().size() > 0)
                if (postsList.get(i).getAttachments().get(0).getPhoto() == null){
                    postsList.remove(i);
                    continue;}
            if (postsList.get(i).getAttachments() == null) {
                postsList.remove(i);
                continue;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_post, viewGroup, false);

        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        defineIsLikedPost(viewHolder, i);

        viewHolder.likesAmountText.setText(" " + postsList.get(i).getLikes().getCount());

        viewHolder.postText.setText(FormatUtil.removeNewLinesFromPostText(postsList.get(i).getText(), 120));
        viewHolder.postDateText.setReferenceTime(postsList.get(i).getDate() * 1000L);
        if (postsList.get(i).getComments() != null)
            viewHolder.commentsAmountText.setText("" + postsList.get(i).getComments().getCount());
        addClickableLinkToText(viewHolder);

        selectAttachmentType(viewHolder, i);


    }


    private void defineIsLikedPost(final ViewHolder viewHolder, int i) {
        int userID = 0;
        if (VKAccessToken.currentToken() != null) {
            userIdString = VKAccessToken.currentToken().userId;
            if (!userIdString.equals("null"))
                userID = Integer.parseInt(userIdString);
        }
        VkApiManager.init().isPostLiked(new VkApiManager.onIsLikedResult() {
            @Override
            public void onIsLikedResult(boolean isLiked) {

                isPostLikedByUser = isLiked;

                if (isLiked) {
                    viewHolder.likeImage.setImageResource(R.drawable.ic_heart_grey600_24dp);
                } else
                    viewHolder.likeImage.setImageResource(R.drawable.ic_heart_outline_grey600_24dp);

            }
        }, context, postsList.get(i).getId(), userID);
    }

    private void selectAttachmentType(ViewHolder viewHolder, int i) {
        if (postsList.get(i).getAttachments() != null
                && postsList.get(i).getAttachments().size() > 0) {
            switch (postsList.get(i).getAttachments().get(0).getType()) {
                case "video":
                    loadVideo(viewHolder, i);
                    break;
            }
        }

    }


    private void loadVideo(ViewHolder viewHolder, int i) {
        if (postsList.get(i).getAttachments().get(0).getVideo() != null)
            Picasso.with(context).load(postsList.get(i).getAttachments().get(0).getVideo().getPhoto320()).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder)
                    .into(viewHolder.postImage);
        else viewHolder.postImage.setImageResource(R.drawable.lentach_placeholder);
    }

    private void addClickableLinkToText(ViewHolder viewHolder) {
        viewHolder.postText.setLinksClickable(true);
        Pattern httpPattern = Pattern.compile("[a-z]+:\\/\\/[^ \\n]*");
        Linkify.addLinks(viewHolder.postText, httpPattern, "");
    }


    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.post_container)
        ViewGroup cardView;
        @Bind(R.id.tv_name)
        TextView postText;
        @Bind(R.id.img_post)
        ImageView postImage;
        @Bind(R.id.iv_Like)
        ImageView likeImage;
        @Bind(R.id.timestamp)
        RelativeTimeTextView postDateText;
        @Bind(R.id.likesAmount)
        TextView likesAmountText;
        @Bind(R.id.iv_Comments)
        ImageView commentsImage;
        @Bind(R.id.commentsAmount)
        TextView commentsAmountText;

        protected Context context;

        public ViewHolder(View view, final Context context) {
            super(view);

            ButterKnife.bind(this, view);
            postImage.setOnClickListener(this);
            postText.setOnClickListener(this);
            likeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPostLikedByUser) {
                        VkApiManager.init().addLikeToPost(context, new VkApiManager.onAddLikesResult() {
                                    @Override
                                    public void onAddLikesResult(int likesCount) {
                                        likeImage.setImageResource(R.drawable.ic_heart_grey600_24dp);
                                        int amount = postsList.get(getPosition()).getLikes().getCount() + 1;
                                        likesAmountText.setText("" + amount);
                                    }
                                }
                                ,
                                new VkApiManager.onAddLikesResultFail() {
                                    @Override
                                    public void onAddLikesResultFail(String message) {
                                        Toast.makeText(context, "Вы не авторизованы!", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                postsList.get(getPosition()).getId()
                        );
                    } else {

                        VkApiManager.init().deleteLikeFromPost(context, new VkApiManager.onAddLikesResult() {
                                    @Override
                                    public void onAddLikesResult(int likesCount) {
                                        likeImage.setImageResource(R.drawable.ic_heart_outline_grey600_24dp);
                                        int amount = postsList.get(getPosition()).getLikes().getCount() - 1;
                                        likesAmountText.setText("" + amount);
                                    }
                                }
                                ,
                                new VkApiManager.onAddLikesResultFail() {
                                    @Override
                                    public void onAddLikesResultFail(String message) {
                                        Toast.makeText(context, "Вы не авторизованы!", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                postsList.get(getPosition()).getId()
                        );
                    }
                }
            });
            this.context = context;
        }


        @Override
        public void onClick(View v) {

            if (v == postText || v == postImage) {

                int idVideo = postsList.get(getPosition()).getAttachments().get(0).getVideo().getId();


                DataService.init().getVideoFromIdVidach(context, new DataService.onRequestVideoFromId() {
                    @Override
                    public void onRequestVideoFromId(VKApiVideo vkApiVideo) {
                        if (vkApiVideo != null)
                            ActivityRouter.startVideoActivity(context, vkApiVideo.player);
                        else
                        {
                            startVideoFromYoutube((Activity)context);
                        }

                    }
                }, idVideo);

                //ActivityRouter.startPostActivity((Activity)context,postsList.get(getPosition()),postImage,1);
            }
        }

        private void startVideoFromYoutube(Activity activity) {
            Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.setPackage("com.google.android.youtube");
            intent.putExtra("query", postsList.get(getPosition()).getAttachments().get(0).getVideo().getTitle());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }

    }


}