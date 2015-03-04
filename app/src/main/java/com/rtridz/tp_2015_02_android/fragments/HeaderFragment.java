package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.rtridz.tp_2015_02_android.R;

public class HeaderFragment extends Fragment implements View.OnClickListener, Header {
    private static final String AUTO_TRANS = HeaderFragment.class.getName() + "_auto_trans";
    private boolean isAutoTrans = false;
    private Listener activity;

    public interface Listener {
        void onClickFromLang();

        void onClickToLang();

        void onClickTranslate(String fromLang, String toLang);
    }

    public static HeaderFragment newInstance(Boolean isAutoTrans) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        args.putString(AUTO_TRANS, isAutoTrans.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public HeaderFragment() {
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
        View baseView = inflater.inflate(R.layout.fragment_header, container, false);
        Button fromLang = (Button) baseView.findViewById(R.id.button_from_lang);
        if (isAutoTrans) {
            fromLang.setText("auto");
        } else {
            fromLang.setOnClickListener(this);
        }
        Button toLang = (Button) baseView.findViewById(R.id.button_to_lang);
        toLang.setOnClickListener(this);
        ImageButton translate = (ImageButton) baseView.findViewById(R.id.button_translate);
        translate.setOnClickListener(this);
        return baseView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Listener) {
            this.activity = (Listener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement HeaderFragment.Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_from_lang :
                activity.onClickFromLang();
                break;
            case R.id.button_to_lang :
                activity.onClickToLang();
                break;
            case R.id.button_translate :
                View view;
                if ((view = getView()) != null){
                    Button toLang = (Button) view.findViewById(R.id.button_to_lang);
                    if (isAutoTrans) {
                        activity.onClickTranslate(null, toLang.getText().toString());
                    } else {
                        Button fromLang = (Button) view.findViewById(R.id.button_from_lang);
                        activity.onClickTranslate(fromLang.getText().toString(), toLang.getText().toString());
                    }
                }
                break;
        }
    }

    @Override
    public void setFromLangAbbrev(String abbrev) {
        View view;
        if ((view = getView()) != null){
            Button fromLang = (Button) view.findViewById(R.id.button_from_lang);
            fromLang.setText(abbrev);
        }
    }

    @Override
    public void setToLangAbbrev(String abbrev) {
        View view;
        if ((view = getView()) != null){
            Button toLang = (Button) view.findViewById(R.id.button_to_lang);
            toLang.setText(abbrev);
        }
    }

    @Override
    public String getFromLangAbbrev() {
        View view;
        if ((view = getView()) != null){
            Button toLang = (Button) view.findViewById(R.id.button_from_lang);
            return toLang.getText().toString();
        }
        return null;
    }

    @Override
    public String getToLangAbbrev() {
        View view;
        if ((view = getView()) != null){
            Button toLang = (Button) view.findViewById(R.id.button_to_lang);
            return toLang.getText().toString();
        }
        return null;
    }
}
