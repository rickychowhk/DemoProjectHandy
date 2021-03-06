package com.handy.wing.demoproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wing on 24/9/2017.
 */

public class TabOneFragment extends Fragment implements View.OnClickListener {

    View rootView;
    ListView lv;
    ProgressDialog mProgressDialog;
    JSONObject jsonObject;
    int success;
    ArrayList<HashMap<String, String>> demoArrayList = new ArrayList<HashMap<String, String>>();
    JSONArray demoArray = null;
    DemoListAdapter ap;
    static final String TAG_ID = AppConfig.api_Id;
    static final String TAG_TITLE = AppConfig.api_Title;
    static final String TAG_DESC= AppConfig.api_Desc;
    static final String TAG_IMG= AppConfig.api_Img;
    static final String TAG_TYPE= AppConfig.api_type;
    String LOG_TAG = AppConfig.tabBarNameOne;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater
                .inflate(R.layout.tab_one, container, false);

        ConfigUI();
        new getDemoList().execute();

        return rootView;
    }

    public void ConfigUI() {
        lv = (ListView)rootView.findViewById(R.id.lv);
    }

    @Override
    public void onClick(View view) {

    }

    private class getDemoList
            extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setTitle("");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setMessage(AppConfig.alert_Loading);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
        }

        protected Void doInBackground(Void... params) {

            jsonObject = JSONfunctions
                    .getJSONfromURL(AppConfig.server_domain+AppConfig.api_Path+AppConfig.demo_List);
            try {
                success = jsonObject.getInt(AppConfig.success);
                demoArray = jsonObject.getJSONArray(AppConfig.demo_Array);

                for (int i = 0; i < demoArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();

                    jsonObject = demoArray.getJSONObject(i);
                    map.put(AppConfig.api_Id, jsonObject.getString(AppConfig.api_Id));
                    map.put(AppConfig.api_Title, jsonObject.getString(AppConfig.api_Title));
                    map.put(AppConfig.api_Desc, jsonObject.getString(AppConfig.api_Desc));
                    map.put(AppConfig.api_Img, jsonObject.getString(AppConfig.api_Img));
                    map.put(AppConfig.api_type, jsonObject.getString(AppConfig.api_type));
                    demoArrayList.add(map);
                }

            } catch (JSONException e) {
                Log.e(AppConfig.alert_Errortitle, e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args) {
            mProgressDialog.dismiss();
            if(success == 1){
                if(demoArrayList != null) {
                    ap = new DemoListAdapter(getActivity(), demoArrayList);
                    lv.setAdapter(ap);
                    lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView absListView, int i) {
                            int threshold = 1;
                            int count = lv.getAdapter().getCount();
                            if (i == SCROLL_STATE_IDLE) {
                                if (lv.getLastVisiblePosition() >= count
                                        - threshold) {
                                    new getMoreDemoList().execute();
                                }
                            }
                        }

                        @Override
                        public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                        }
                    });
                }
            }else{
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.setTitle(AppConfig.alert_Errortitle);
                dialog.setMessage("");
                dialog.setButton(AppConfig.alert_Ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        }
    }

    private class getMoreDemoList
            extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setTitle("");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setMessage(AppConfig.alert_Loading);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
        }

        protected Void doInBackground(Void... params) {

            jsonObject = JSONfunctions
                    .getJSONfromURL(AppConfig.server_domain+AppConfig.api_Path+AppConfig.demo_List);
            try {
                success = jsonObject.getInt(AppConfig.success);
                demoArray = jsonObject.getJSONArray(AppConfig.demo_Array);

                for (int i = 0; i < demoArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();

                    jsonObject = demoArray.getJSONObject(i);
                    map.put(AppConfig.api_Id, jsonObject.getString(AppConfig.api_Id));
                    map.put(AppConfig.api_Title, jsonObject.getString(AppConfig.api_Title));
                    map.put(AppConfig.api_Desc, jsonObject.getString(AppConfig.api_Desc));
                    map.put(AppConfig.api_Img, jsonObject.getString(AppConfig.api_Img));
                    map.put(AppConfig.api_type, jsonObject.getString(AppConfig.api_type));
                    demoArrayList.add(map);
                }

            } catch (JSONException e) {
                Log.e(AppConfig.alert_Errortitle, e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args) {
            mProgressDialog.dismiss();
            if(success == 1){

                if(demoArrayList != null) {
                    int lv_position = lv.getLastVisiblePosition();
                    ap = new DemoListAdapter(getActivity(), demoArrayList);
                    lv.setAdapter(ap);
                    lv.setSelectionFromTop(lv_position, 0);
                }
            }else{
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.setTitle(AppConfig.alert_Errortitle);
                dialog.setMessage("");
                dialog.setButton(AppConfig.alert_Ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume".toString());

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause".toString());
        demoArrayList.clear();
        ap.notifyDataSetChanged();
    }
}
