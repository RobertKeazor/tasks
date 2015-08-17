package com.anyer.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anyer.demo.R;
import com.anyer.demo.presenter.MainActivityPresenter;
import com.anyer.demo.presenter.MainActivityPresenterImpl;
import com.anyer.demo.view.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView,
        ListFragmentListener, ItemFormFragmentListener {

    private MainActivityPresenter mActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityPresenter = new MainActivityPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mActivityPresenter.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        mActivityPresenter.onSaveInstanceState(savedInstanceState);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mActivityPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSetupFragmentItemForm(ItemFormFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_top_container,
                fragment).commit();
    }

    @Override
    public void onSetupFragmentList(ListFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_list_container,
                fragment).commit();
    }

    @Override
    public void onListFragmentViewCreated() {
        mActivityPresenter.onListFragmentViewCreated();
    }

    @Override
    public void onItemFormFragmentNewItem(String text) {
        mActivityPresenter.onNewItem(text);
    }
}
