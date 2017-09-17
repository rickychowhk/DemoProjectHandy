package com.handy.wing.handycodetest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by wing on 17/9/2017.
 */

public class TabOneFragment extends Fragment implements View.OnClickListener {

    View rootView;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater
                .inflate(R.layout.tab_one, container, false);

        ConfigUI();

        return rootView;
    }

    public void ConfigUI() {
        lv = (ListView)rootView.findViewById(R.id.lv);
    }

    @Override
    public void onClick(View view) {

    }
}
