package com.lentach.ui.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Andre Macareno on 04.11.2016.
 */

public class FlowLayout extends ViewGroup {
    static final String TAG = "FlowLayout";
    final int[] widths = new int[4];
    final float[] aspectRatios = new float[4];
    final int horizontalPadding, verticalPadding;
    public FlowLayout(Context context) {
        super(context);
        horizontalPadding = dp(2);
        verticalPadding = dp(4);
    }
    @Override
    protected void onMeasure(int measureWidthSpec, int measureHeightSpec)
    {
        //TODO horizontalPadding
        int expectedWidth = 0, expectedHeight = 0;
        if(MeasureSpec.getMode(measureWidthSpec) == MeasureSpec.UNSPECIFIED)
            expectedWidth = ((ViewGroup) getParent()).getMeasuredWidth();
        else
            expectedWidth = MeasureSpec.getSize(measureWidthSpec);
        float viewAspectRatio;
        if(getChildCount() == 1)
        {
            if(!(getChildAt(0) instanceof ImageView))
                throw new UnsupportedOperationException("This FlowLayout does work only with ImageViews.");
            final ImageView child = (ImageView) getChildAt(0);
            viewAspectRatio = (float) child.getDrawable().getIntrinsicWidth() / child.getDrawable().getIntrinsicHeight();
            expectedHeight = (int) ((float) expectedWidth / viewAspectRatio);
            getChildAt(0).measure(MeasureSpec.makeMeasureSpec(expectedWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(expectedHeight, MeasureSpec.EXACTLY));
        }
        else
        {
            int line = 0;
            int viewsOnThisLine = 0;
            int viewsOnLineDrawn = 0;
            boolean calculateViewsCount = true;
            int lineHeight = 0;
            for(int i = 0; i < getChildCount(); i++)
            {
                final View child = getChildAt(i);
                if(!(child instanceof ImageView))
                    throw new UnsupportedOperationException("This FlowLayout does work only with ImageViews.");
                int remainingChildren = getChildCount() - i;
                if(calculateViewsCount)
                {
                    if(line == 0) {
                        viewsOnThisLine = getChildCount() > 4 ? 2 : 1;
                    }
                    else {
                        viewsOnThisLine = remainingChildren > 4 ? 4 : remainingChildren;
                    }
                    int totalWidth = 0;
                    lineHeight = viewsOnThisLine == 1 ? dp(300) : dp(100);
                    for(int j = 0; j < viewsOnThisLine; j++)
                    {
                        ImageView nextChild = (ImageView) getChildAt(i + j);
                        viewAspectRatio = ((float) nextChild.getDrawable().getIntrinsicWidth() / nextChild.getDrawable().getIntrinsicHeight());
                        aspectRatios[j] = viewAspectRatio;
                        widths[j] = (int) ((float) lineHeight * viewAspectRatio);
                        //Log.d(TAG, String.format("pic width: %d; reduced: %d; aspect ratio: %f", nextChild.getDrawable().getIntrinsicWidth(), widths[j], viewAspectRatio));
                        totalWidth += widths[j] + (j == viewsOnThisLine - 1 ? 0 : horizontalPadding);
                    }
                    while(totalWidth > expectedWidth && viewsOnThisLine > 1)
                    {
                        totalWidth -= widths[viewsOnThisLine - 1] + horizontalPadding;
                        viewsOnThisLine--;
                    }
                    if(totalWidth != expectedWidth)
                    {
                        //Log.d(TAG, String.format("total width: %d; available width: %d", totalWidth, expectedWidth));
                        int conversionWidth = 0;
                        float multiplyWidth = (float) (expectedWidth - horizontalPadding *(viewsOnThisLine-1)) / totalWidth;
                        for(int j = 0; j < viewsOnThisLine; j++)
                            conversionWidth += (int) ((float) widths[j] * multiplyWidth);
                        int conversionDelta = expectedWidth - conversionWidth - horizontalPadding *(viewsOnThisLine);
                        for(int j = 0; j < viewsOnThisLine; j++)
                        {
                            widths[j] = (int) ((float) widths[j] * multiplyWidth + ((float) conversionDelta / viewsOnThisLine));
                            int nextLineHeight = (int) ((float) widths[j] / aspectRatios[j]);
                            if(j == 0 || nextLineHeight < lineHeight)
                                lineHeight = nextLineHeight;
                        }
                        //widths[viewsOnThisLine - 1] += (totalWidth - conversionWidth);
                    }
                    expectedHeight += lineHeight + (i + viewsOnThisLine == getChildCount() - 1 ? 0 : verticalPadding);
                    calculateViewsCount = false;
                }
                child.measure(MeasureSpec.makeMeasureSpec(widths[viewsOnLineDrawn], MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(lineHeight, MeasureSpec.EXACTLY));
                viewsOnLineDrawn++;
                if(viewsOnLineDrawn == viewsOnThisLine)
                {
                    viewsOnLineDrawn = 0;
                    line++;
                    calculateViewsCount = true;
                }
            }
        }
        LayoutParams lp=getLayoutParams();
        if(lp instanceof MarginLayoutParams){
            MarginLayoutParams mlp=(MarginLayoutParams) lp;
            expectedWidth -= mlp.rightMargin + mlp.leftMargin;
            expectedHeight -= mlp.topMargin + mlp.bottomMargin;
        }
        //Log.d(TAG, String.format("layout size: %d x %d", expectedWidth, expectedHeight));
        setMeasuredDimension(expectedWidth, expectedHeight);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutX = 0, layoutY = 0;
        for(int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (!(child instanceof ImageView))
                throw new UnsupportedOperationException("This FlowLayout does work only with ImageViews.");
            if(layoutX + child.getMeasuredWidth() > getMeasuredWidth())
            {
                layoutX = 0;
                layoutY += verticalPadding + (i == 0 ? 0 : getChildAt(i - 1).getMeasuredHeight());
            }
            //Log.d(TAG, String.format("layoutX: %d; layoutY: %d; measured width = %d; child measured width = %d; child measured height = %d", layoutX, layoutY, getMeasuredWidth(), child.getMeasuredWidth(), child.getMeasuredHeight()));
            child.layout(layoutX, layoutY, layoutX + child.getMeasuredWidth(), layoutY + child.getMeasuredHeight());
            layoutX += child.getMeasuredWidth() + horizontalPadding;
            View nextChild = i == getChildCount() - 1 ? null : getChildAt(i + 1);
            if(nextChild != null && layoutX + nextChild.getMeasuredWidth() > getMeasuredWidth())
                layoutX -= horizontalPadding;
        }
    }

    public float density = getResources().getDisplayMetrics().density;
    public int dp(int pixels)
    {
        if (pixels == 0) {
            return 0;
        }
        return (int)Math.ceil(density * pixels);
    }
}