package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rtridz.tp_2015_02_android.R;

public class TextFragment extends Fragment implements TextFields {
    private static final String AUTO_TRANS = HeaderFragment.class.getName() + "_auto_trans";
    private boolean isAutoTrans = false;
    private Listener activity;

    public interface Listener {
        void onEnterTranslate(String text);

        boolean onDelKeyEvent();
    }

    public static TextFragment newInstance(Boolean isAutoTrans) {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        args.putString(AUTO_TRANS, isAutoTrans.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isAutoTrans = Boolean.valueOf(getArguments().getString(AUTO_TRANS));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_text, container, false);
        final EditText editText = (EditText) baseView.findViewById(R.id.edit_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                        || (event.getAction() == KeyEvent.ACTION_DOWN))){
                    activity.onEnterTranslate(v.getText().toString());
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        if (isAutoTrans) {
            final TextView textView = (TextView)baseView.findViewById(R.id.trans_text);
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((keyCode == KeyEvent.KEYCODE_DEL) || (keyCode == KeyEvent.KEYCODE_BACK)) {
                        if (activity.onDelKeyEvent()) {
                            editText.setText("");
                            textView.setText("");
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
        ProgressBar progressBar = (ProgressBar) baseView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        return baseView;
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
    public void setText(String text, boolean isVisibleBar) {
        View view;
        if ((view = getView()) != null){
            TextView textView = (TextView) view.findViewById(R.id.trans_text);
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            if (isVisibleBar) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
            }
            textView.setText(text);
        }
    }
}
