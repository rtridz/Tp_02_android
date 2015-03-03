package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rtridz.tp_2015_02_android.R;

public class TextFragment extends Fragment implements TextFields {
    private Listener activity;

    public interface Listener {
        void onEnterTranslate(String text);
    }

    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Listener) {
            this.activity = (Listener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement TextFragment.Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getEditText() {
        View view;
        if ((view = getView()) != null){
            TextView textView = (TextView) view.findViewById(R.id.edit_text);
            return textView.getText().toString();
        }
        return null;
    }

    @Override
    public void setText(String text) {
        View view;
        if ((view = getView()) != null){
            TextView textView = (TextView) view.findViewById(R.id.trans_text);
            textView.setText(text);
        }
    }
}
