package com.anyer.demo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyer.demo.R;
import com.anyer.demo.model.Item;
import com.anyer.demo.model.ListItemViewHolder;

import java.util.ArrayList;

/**
 * Created by anyer on 8/16/15.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private ArrayList<Item> mItems;
    private Context mContext;

    public ListRecyclerViewAdapter(Context context, ArrayList<Item> items) {
        mItems = items;
        mContext = context;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_item,
                parent, false);

        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListItemViewHolder holder, final int position) {
        final Item item = mItems.get(position);

        if (item.isDone()) {
            holder.getImageView().setVisibility(View.VISIBLE);
        } else {
            holder.getImageView().setVisibility(View.GONE);
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setIsDone(!mItems.get(position).isDone());
                notifyItemChanged(position);
            }
        };

        holder.getLayout().setOnClickListener(clickListener);
        holder.getImageView().setOnClickListener(clickListener);
        holder.getTextView().setOnClickListener(clickListener);

        holder.getTextView().setText(mItems.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(Item item) {
        mItems.add(item);
    }

    public ArrayList<Item> getList() {
        return mItems;
    }
}
