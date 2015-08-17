package com.anyer.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyer.demo.R;
import com.anyer.demo.model.Item;
import com.anyer.demo.view.ListRecyclerViewAdapter;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    public static final String ARG_LIST = "list";

    private ListFragmentListener mListener;
    private RecyclerView mRecyclerView;
    private ListRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Item> list = new ArrayList<Item>();
        if (getArguments() != null) {
            ArrayList<Item> tmpList = getArguments().getParcelableArrayList(ListFragment.ARG_LIST);

            if (tmpList != null) {
                list = tmpList;
            }
        }

        mAdapter = new ListRecyclerViewAdapter(getActivity().getApplicationContext(), list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity()
                .getApplicationContext());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_recyclerview);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(mAdapter);

        mListener.onListFragmentViewCreated();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (ListFragmentListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.getLocalClassName() + "' must implement " +
                    ListFragmentListener.class.getSimpleName());
        }
    }

    public void addListItem(Item item) {
        mAdapter.addItem(item);
        mAdapter.notifyDataSetChanged();
    }

    public ArrayList<Item> getItemList() {
        return mAdapter.getList();
    }
}
