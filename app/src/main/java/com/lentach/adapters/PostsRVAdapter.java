package com.lentach.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentach.R;
import com.lentach.models.wallpost.Post;
import com.lentach.navigator.ActivityNavigator;
import com.lentach.util.FormatUtil;
import com.lentach.util.UnixConverter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An adapter for the list of Posts
 */
public class PostsRVAdapter extends RecyclerView.Adapter<PostsRVAdapter.ViewHolder> {

    private List<Post> postsList;
    private Context context;

    public PostsRVAdapter(Context context, List<Post> PostsList) {
        this.context = context;
        this.postsList = PostsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        checkIfCopyHistoryExists(viewHolder, i);

        viewHolder.likesAmountText.setText(" "+postsList.get(i).getLikes().getCount());
        viewHolder.postText.setText(FormatUtil.formatPostText(postsList.get(i).getText()));
        viewHolder.postDateText.setText(" " + UnixConverter.convertToString(postsList.get(i).getDate()));

        addClickableLinkToText(viewHolder);

        checkAndLoadPhoto(viewHolder, i);

        int a = 5;

    }

    private void checkAndLoadPhoto(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getAttachments()!=null)
        if(postsList.get(i).getAttachments().size()>0)
            if(postsList.get(i).getAttachments().get(0).getPhoto()!=null)
                Picasso.with(context).load(postsList.get(i).getAttachments().get(0).getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder)
                        .into(viewHolder.postImage);
    }

    private void addClickableLinkToText(ViewHolder viewHolder) {
        viewHolder.postText.setLinksClickable(true);
        Pattern httpPattern = Pattern.compile("[a-z]+:\\/\\/[^ \\n]*");
        Linkify.addLinks(viewHolder.postText, httpPattern,"");
    }

    private void checkIfCopyHistoryExists(ViewHolder viewHolder, int i) {
        if(postsList.get(i).getCopyHistory()!=null&&
                postsList.get(i).getCopyHistory().size()!=0 )
            if (postsList.get(i).getCopyHistory().get(0).getAttachments()!=null)
                Picasso.with(context).load(postsList.get(i).getCopyHistory().get(0).getAttachments().get(0).getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder)
                        .into(viewHolder.postImage);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name)
        TextView postText;
        @Bind(R.id.img_post)
        ImageView postImage;
        @Bind(R.id.iv_Like)
        ImageView likeImage;
        @Bind(R.id.tv_PostDate)
        TextView postDateText;
        @Bind(R.id.likesAmount)
        TextView likesAmountText;

        protected Context context;

        public ViewHolder(View view, Context context) {
            super(view);

            view.setOnClickListener(this);
            ButterKnife.bind(this, view);

            this.context = context;
        }

        @Override
        public void onClick(View v) {

            ActivityNavigator.startPostActivity((Activity)context,postsList.get(getPosition()),postImage);

        }
    }
}