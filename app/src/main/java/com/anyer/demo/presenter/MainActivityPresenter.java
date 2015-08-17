package com.anyer.demo.presenter;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.anyer.demo.activity.ListFragmentListener;

/**
 * Created by anyer on 8/16/15.
 */
public interface MainActivityPresenter {
    void onResume();

    void onNewItem(String text);

    void onListFragmentViewCreated();

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle savedInstanceState);
}
