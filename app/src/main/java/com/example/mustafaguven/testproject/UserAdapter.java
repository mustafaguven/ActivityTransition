package com.example.mustafaguven.testproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MustafaGuven on 16.2.2015.
 */
public class UserAdapter extends BaseAdapter {

    Context mContext;
    List<User> mUserList;

    public UserAdapter(Context context, List<User> list) {
        this.mContext = context;
        this.mUserList = list;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public User getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mUserList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.user_adapter_item, parent, false);
            ImageView imgView = (ImageView) view.findViewById(R.id.imgUser);
            TextView lblFullname = (TextView) view.findViewById(R.id.lblFullname);
            TextView lblId = (TextView) view.findViewById(R.id.lblId);

            view.setBackgroundColor(getItem(position).getBackgroundColor());
            imgView.setImageDrawable(mContext.getResources().getDrawable(getItem(position).getDrawableId()));
            lblId.setText(String.valueOf(getItem(position).getId()));
            lblFullname.setText(getItem(position).getFullName());
        }

        return view;
    }
}
