package com.rtridz.tp_2015_02_android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.rtridz.tp_2015_02_android.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListFragment extends Fragment implements AbsListView.OnItemClickListener {
    private static final String ABBREV = "ABBREV";
    private static final String LANG = "LANG";

    private HashMap<String, String> langs;

    private OnFragmentInteractionListener mListener;

    public static ListFragment newInstance(ArrayList<String> abbrevs, ArrayList<String> langs) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ABBREV, abbrevs);
        args.putStringArrayList(LANG, langs);
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        langs = new HashMap<>();
        if (getArguments() != null) {
            int i = 0;
            for (String abbrev : getArguments().getStringArrayList(ABBREV)) {
                langs.put(abbrev, getArguments().getStringArrayList(LANG).get(i++));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {

        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
