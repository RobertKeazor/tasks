package com.anyer.demo.view;

import android.os.Bundle;

import com.anyer.demo.activity.ItemFormFragment;
import com.anyer.demo.activity.ListFragment;

/**
 * Created by anyer on 8/16/15.
 */
public interface MainActivityView {
    void onSetupFragmentList(ListFragment fragment);
    void onSetupFragmentItemForm(ItemFormFragment mItemFormFragment);
}
