package com.handy.wing.handycodetest;

import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wing on 17/9/2017.
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
    static final String TAG_ID = "id";
    static final String TAG_TITLE = "title";
    static final String TAG_DESC= "desc";
    static final String TAG_IMG= "img";
    String LOG_TAG = "TabOneFragment";
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
                success = jsonObject.getInt("success");
                demoArray = jsonObject.getJSONArray("demo_list");

                for (int i = 0; i < demoArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();

                    jsonObject = demoArray.getJSONObject(i);
                    map.put(AppConfig.api_Id, jsonObject.getString(AppConfig.api_Id));
                    map.put(AppConfig.api_Title, jsonObject.getString(AppConfig.api_Title));
                    map.put(AppConfig.api_Desc, jsonObject.getString(AppConfig.api_Desc));
                    map.put(AppConfig.api_Img, jsonObject.getString(AppConfig.api_Img));
                    demoArrayList.add(map);
                }

            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args) {
            mProgressDialog.dismiss();
            if(success == 1){
                ap = new DemoListAdapter(getActivity(), demoArrayList);
                lv.setAdapter(ap);
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
        new getDemoList().execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause".toString());
        demoArrayList.clear();
    }
}
