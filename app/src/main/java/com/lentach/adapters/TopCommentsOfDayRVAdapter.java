package com.lentach.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.lentach.R;
import com.lentach.models.comment.Comment;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.navigator.ActivityNavigator;
import com.lentach.util.UnixConverter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * An adapter for the list of WallComments
 */
public class TopCommentsOfDayRVAdapter extends RecyclerView.Adapter<TopCommentsOfDayRVAdapter.ViewHolder> {

    private List<Comment> wallCommentsList;
    private Context context;

    public TopCommentsOfDayRVAdapter(Context context, List<Comment> WallCommentsList) {
        this.context = context;
        this.wallCommentsList = WallCommentsList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);


        return new ViewHolder(view, viewGroup.getContext());
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.commentText.setText(" " + wallCommentsList.get(i).getText());
        //viewHolder.commentatorName.setText(" " + wallCommentsList.get(i).getUsername());
        viewHolder.likesAmount.setText(" " + wallCommentsList.get(i).getLikes().getCount());
        viewHolder.postDate.setText(" " + UnixConverter.convertToString(wallCommentsList.get(i).getDate()));


        //  loadPhotoToView(viewHolder, i);

                   /* else {

                    for (int j = 0; j < wallCommentsList.get(i).getAttachments().size(); j++)

                        if (wallCommentsList.get(i).getAttachments().get(j).getType().equals("photo")) {
                            DefaultSliderView textSliderView = new DefaultSliderView(context);
                            textSliderView.image(wallCommentsList.get(j).getAttachments().get(j).getPhoto().getPhoto604());
                            viewHolder.sliderLayout.addSlider(textSliderView);

                        }


                    {
                        viewHolder.sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
                        viewHolder.sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        viewHolder.sliderLayout.setCustomAnimation(new DescriptionAnimation());
                        viewHolder.sliderLayout.setDuration(4000);

                    }
                }
            }
            */


    }

    @Override
    public int getItemCount() {
        return wallCommentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name)
        TextView commentatorName;
        @Bind(R.id.tv_textComment)
        TextView commentText;
        @Bind(R.id.likesAmount)
        TextView likesAmount;
        @Bind(R.id.postImage)
        ImageView postImage;
        @Bind(R.id.slider)
        SliderLayout sliderLayout;
        @Bind(R.id.tv_PostDate)
        TextView postDate;

        protected Context context;

        public ViewHolder(View view, Context context) {
            super(view);

            ButterKnife.bind(this, view);
            postImage.setOnClickListener(this);
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.postImage:

                    break;
            }

        }

    }
}