package com.anyer.demo.presenter;

import android.os.Bundle;

import com.anyer.demo.activity.ItemFormFragment;
import com.anyer.demo.activity.ListFragment;
import com.anyer.demo.model.Item;
import com.anyer.demo.view.MainActivityView;

import java.util.ArrayList;

/**
 * Created by anyer on 8/16/15.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private MainActivityView mMainActivityView;

    private ListFragment mListFragment;
    private ItemFormFragment mItemFormFragment;

    private boolean mIsListFragmentViewCreated;


    public MainActivityPresenterImpl(MainActivityView mainActivityView) {
        mMainActivityView = mainActivityView;

        mIsListFragmentViewCreated = false;
    }

    @Override
    public void onResume() {
        setupFragmentItemForm();
        setupFragmentList(null);
    }

    @Override
    public void onNewItem(String text) {
        if (mIsListFragmentViewCreated) {
            mListFragment.addListItem(new Item(text));
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(text);

            Bundle arguments = new Bundle();
            arguments.putStringArrayList(ListFragment.ARG_LIST, list);

            setupFragmentList(arguments);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (mIsListFragmentViewCreated) {
            savedInstanceState.putParcelableArrayList(ListFragment.ARG_LIST, mListFragment
                    .getItemList());
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(ListFragment.ARG_LIST, savedInstanceState
                .getParcelableArrayList(ListFragment.ARG_LIST));

        setupFragmentList(arguments);
    }

    @Override
    public void onListFragmentViewCreated() {
        mIsListFragmentViewCreated = true;
    }

    private void setupFragmentItemForm() {
        mItemFormFragment = new ItemFormFragment();

        mMainActivityView.onSetupFragmentItemForm(mItemFormFragment);
    }

    private void setupFragmentList(Bundle arguments) {
        if (mListFragment == null) {
            mListFragment = new ListFragment();

            if (arguments != null) {
                mListFragment.setArguments(arguments);
            }

            mMainActivityView.onSetupFragmentList(mListFragment);
        }
    }
}
