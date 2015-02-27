package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rtridz.tp_2015_02_android.R;

public class HeaderFragment extends Fragment {
    private static final String AUTO_TRANS = HeaderFragment.class.getName() + "_auto_trans";

    private boolean isAutoTrans = false;

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
        if (isAutoTrans) {
            Button fromLang = (Button) baseView.findViewById(R.id.button_from_lang);
            fromLang.setText("auto");
        }
        return baseView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
