package com.handy.wing.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wing on 24/9/2017.
 */

public class DemoListAdapter extends BaseAdapter {
    TextView title_lbl,desc_lbl;
    ImageView img;
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imagesLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
    private String LOG_TAG = "DemoListAdapter";
    LinearLayout imgLinear, txtLinear;

    public DemoListAdapter(Context context,
                           ArrayList<HashMap<String, String>> demoArrayList) {
        this.context = context;
        data = demoArrayList;
        imagesLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.tab_one_item, parent, false);
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);

        imgLinear = (LinearLayout) itemView.findViewById(R.id.imgLinear);
        txtLinear = (LinearLayout) itemView.findViewById(R.id.txtLinear);

        if(resultp.get(TabOneFragment.TAG_TYPE).toString().equals("img")){
            txtLinear.setVisibility(LinearLayout.GONE);
        }

        title_lbl = (TextView)itemView.findViewById(R.id.title);
        title_lbl.setText(resultp.get(TabOneFragment.TAG_TITLE));
        desc_lbl = (TextView)itemView.findViewById(R.id.desc);
        desc_lbl.setText(resultp.get(TabOneFragment.TAG_DESC));
        img = (ImageView)itemView.findViewById(R.id.img);
        imagesLoader.DisplayImage(resultp.get(TabOneFragment.TAG_IMG), img);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                itemOnClick Handling
//                HashMap<String, String> resultp = new HashMap<String, String>();
//                resultp = data.get(position);

            }
        });
        return itemView;
    }

}
