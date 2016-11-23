package com.lentach.ui.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.lentach.R;
import com.lentach.api.vkApi.VkApiManager;
import com.lentach.api.models.wallpost.Post;
import com.lentach.router.ActivityRouter;
import com.lentach.ui.components.OnLoadMoreListener;
import com.lentach.util.FormatUtil;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An adapter for the list of Posts
 */
public class PostsRVAdapter extends RecyclerView.Adapter<PostsRVAdapter.ViewHolder> {

    private List<Post> postsList;
    private final Context context;
    private int lastPosition = -1;
    private boolean isPostLikedByUser;
    private String userIdString;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener mOnLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public PostsRVAdapter(Context context, List<Post> PostsList) {
        this.context = context;
        this.postsList = PostsList;
        removeUnusedPosts();
    }

    private void removeUnusedPosts() {
        for (int i = 0; i < postsList.size() ; i++) {
            if(postsList.get(i).getIsPinned()!=null && postsList.get(i).getIsPinned()==1 ){
                postsList.remove(i);
                continue;
            }
            if(postsList.get(i).getAttachments()!=null&&postsList.get(i).getAttachments().size()>0)
                if(postsList.get(i).getAttachments().get(0).getPhoto()==null)
                    postsList.remove(i);
            if(postsList.get(i).getAttachments()==null){
                postsList.remove(i);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);

        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

      //2checkIfCopyHistoryExists(viewHolder, i);

      defineIsLikedPost(viewHolder, i);

      viewHolder.likesAmountText.setText(" "+postsList.get(i).getLikes().getCount());

      viewHolder.postText.setText(FormatUtil.removeNewLinesFromPostText(postsList.get(i).getText(),120));
      viewHolder.postDateText.setReferenceTime(postsList.get(i).getDate()*1000L);
      if(postsList.get(i).getComments()!=null)
         viewHolder.commentsAmountText.setText(""+postsList.get(i).getComments().getCount());
      addClickableLinkToText(viewHolder);

        selectAttachmentType(viewHolder, i);

       // addAnimationToPostItem(viewHolder, i);


    }

    private void addAnimationToPostItem(ViewHolder viewHolder, int i) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (i > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_to_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = i;
    }

    private void defineIsLikedPost(final ViewHolder viewHolder, int i) {
        int userID=0;
        if(VKAccessToken.currentToken()!=null){
            userIdString = VKAccessToken.currentToken().userId;
            if(!userIdString.equals("null"))
            userID = Integer.parseInt(userIdString);
        }
        VkApiManager.init().isPostLiked(new VkApiManager.onIsLikedResult() {
            @Override
            public void onIsLikedResult(boolean isLiked) {

                isPostLikedByUser = isLiked;

                if(isLiked)
                {
                    viewHolder.likeImage.setImageResource(R.drawable.ic_heart_grey600_24dp);
                }
                else
                    viewHolder.likeImage.setImageResource(R.drawable.ic_heart_outline_grey600_24dp);

            }
        },context,postsList.get(i).getId(), userID);
    }

    private void selectAttachmentType(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getAttachments()!=null
                &&postsList.get(i).getAttachments().size()>0){
            switch (postsList.get(i).getAttachments().get(0).getType()){
                case "video":
                    loadVideo(viewHolder,i);
                    break;
                case "photo":
                    loadPhoto(viewHolder, i);
                    break;

                case "audio":
                    //TODO
                    break;
            }
        }
        //else
          //      viewHolder.postImage.setVisibility(GONE);
    }

    private void loadPhoto(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getAttachments().get(0).getPhoto()!=null){
            Picasso.with(context).load(postsList.get(i).getAttachments().get(0).getPhoto().getPhoto604()).error(R.drawable.lentach_placeholder).placeholder(R.drawable.lentach_placeholder)
                    .into(viewHolder.postImage);
        }
        else viewHolder.postImage.setImageResource(R.drawable.lentach_placeholder);
    }

    private void loadVideo(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getAttachments().get(0).getVideo()!=null)
            Picasso.with(context).load(postsList.get(i).getAttachments().get(0).getVideo().getPhoto320()).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder)
                    .into(viewHolder.postImage);
        else viewHolder.postImage.setImageResource(R.drawable.lentach_placeholder);
    }

    private void addClickableLinkToText(ViewHolder viewHolder) {
        viewHolder.postText.setLinksClickable(true);
        Pattern httpPattern = Pattern.compile("[a-z]+:\\/\\/[^ \\n]*");
        Linkify.addLinks(viewHolder.postText, httpPattern,"");
    }

    private void checkIfCopyHistoryExists(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getCopyHistory()!=null&&
                postsList.get(i).getCopyHistory().size()!=0 &&  postsList.get(i).getCopyHistory().size()>0 )
            if (postsList.get(i).getCopyHistory().get(0).getAttachments()!=null&&
                    postsList.get(i).getCopyHistory().get(0).getAttachments().size()>0&&
                    postsList.get(i).getCopyHistory().get(0).getAttachments().get(0).getPhoto()!=null)

                Picasso.with(context).load(postsList.get(i).getCopyHistory().get(0).getAttachments().get(0).getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder)
                        .into(viewHolder.postImage);
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
                    if(!isPostLikedByUser){
                        VkApiManager.init().addLikeToPost(context, new VkApiManager.onAddLikesResult() {
                                    @Override
                                    public void onAddLikesResult(int likesCount) {
                                        likeImage.setImageResource(R.drawable.ic_heart_grey600_24dp);
                                        int amount = postsList.get(getPosition()).getLikes().getCount()+1;
                                        likesAmountText.setText(""+amount);
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
                        );}

                    else {

                        VkApiManager.init().deleteLikeFromPost(context, new VkApiManager.onAddLikesResult() {
                                    @Override
                                    public void onAddLikesResult(int likesCount) {
                                        likeImage.setImageResource(R.drawable.ic_heart_outline_grey600_24dp);
                                        int amount = postsList.get(getPosition()).getLikes().getCount()-1;
                                        likesAmountText.setText(""+amount);
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
            }});
            this.context = context;
        }



        @Override
        public void onClick(View v) {

            if(v==postText||v==postImage)
                ActivityRouter.startPostActivity((Activity)context,postsList.get(getPosition()),postImage);
        }

    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;

    }
}