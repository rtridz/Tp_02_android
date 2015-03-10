package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.rtridz.tp_2015_02_android.R;

public class HeaderFragment extends Fragment implements View.OnClickListener, Header {
    private static final String AUTO_TRANS = HeaderFragment.class.getName() + "_auto_trans";
    private boolean isAutoTrans = false;
    private Listener activity;
    static Context ctx;

    public interface Listener {
        void onClickFromLang();

        void onClickToLang();

        void onClickTranslate(String fromLang, String toLang);
    }

    public static HeaderFragment newInstance(Context context, Boolean isAutoTrans) {
        ctx = context;
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

        ImageButton translate = (ImageButton) baseView.findViewById(R.id.button_translate);
        translate.setOnClickListener(this);

        String[] data = {"en", "ru", "fr"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, data);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = (Spinner) baseView.findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(2);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, data);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) baseView.findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(2);

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
        View view;
        switch (v.getId()) {
            case R.id.button_translate :
                if ((view = getView()) != null){
                    Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
                    if (isAutoTrans) {
                        activity.onClickTranslate(null, spinner2.getSelectedItem().toString());
                    } else {
                        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
                        activity.onClickTranslate(spinner1.getSelectedItem().toString(), spinner2.getSelectedItem().toString());
                    }
                }
                break;
        }
    }

    @Override
    public void setFromLangAbbrev(String abbrev) {
        View view;
        if ((view = getView()) != null){
/*
            Button fromLang = (Button) view.findViewById(R.id.button_from_lang);
            fromLang.setText(abbrev);
*/
        }
    }

    @Override
    public void setToLangAbbrev(String abbrev) {
        View view;
        if ((view = getView()) != null){
/*
            Button toLang = (Button) view.findViewById(R.id.button_to_lang);
            toLang.setText(abbrev);
*/
        }
    }

    @Override
    public String getFromLangAbbrev() {
        View view;
        if ((view = getView()) != null){
/*
            Button toLang = (Button) view.findViewById(R.id.button_from_lang);
            return toLang.getText().toString();
*/
        }
        return null;
    }

    @Override
    public String getToLangAbbrev() {
        View view;
        if ((view = getView()) != null){
/*
            Button toLang = (Button) view.findViewById(R.id.button_to_lang);
            return toLang.getText().toString();
*/
        }
        return null;
    }
}
