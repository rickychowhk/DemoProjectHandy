package com.handy.wing.demoproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wing on 24/9/2017.
 */

public class TabTwoFragment extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater
                .inflate(R.layout.tab_two, container, false);

        return rootView;
    }
}
