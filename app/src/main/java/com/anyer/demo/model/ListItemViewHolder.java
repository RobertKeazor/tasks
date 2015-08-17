package com.anyer.demo.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyer.demo.R;

/**
 * Created by anyer on 8/16/15.
 */
public class ListItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mText;
    private final View mLayout;
    private ImageView mImageView;

    public ListItemViewHolder(View view) {
        super(view);

        mText = (TextView) view.findViewById(R.id.cardview_list_item_text);
        mImageView = (ImageView) view.findViewById(R.id.cardview_list_item_image);
        mLayout = view.findViewById(R.id.cardview_list_item_layout);
    }

    public TextView getTextView() {
        return mText;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public View getLayout() {
        return mLayout;
    }
}
