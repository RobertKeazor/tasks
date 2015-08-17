package com.anyer.demo.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.anyer.demo.R;

/**
 * Created by anyer on 8/16/15.
 */
public class ItemFormFragment extends Fragment implements View.OnClickListener {
    private ItemFormFragmentListener mListener;
    private EditText mEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_form, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = (EditText) view.findViewById(R.id.fragment_item_form_text);

        view.findViewById(R.id.fragment_item_form_button).setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (ItemFormFragmentListener) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.getLocalClassName() + "' must implement " +
                    ItemFormFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onClick(View v) {
        String text = mEditText.getText().toString();
        mListener.onItemFormFragmentNewItem(text);
    }
}
