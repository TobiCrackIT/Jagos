package com.example.oluwatobi.jagos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oluwatobi on 07-Oct-17.
 */

public class ProfileAdapter extends ArrayAdapter<JavaDeveloper> {
    public ProfileAdapter(Context context, ArrayList<JavaDeveloper> JDList){
        super(context,0,JDList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_profile,parent,false);
        }

        JavaDeveloper currentDeveloper=getItem(position);

        ImageView avatar=(ImageView)listItemView.findViewById(R.id.profilePic);
        avatar.setImageBitmap(currentDeveloper.getmProfilePicture());

        TextView usernameTV=(TextView)listItemView.findViewById(R.id.anyName);
        usernameTV.setText(currentDeveloper.getmUsername());

        TextView urlTV=(TextView)listItemView.findViewById(R.id.url);
        urlTV.setText(currentDeveloper.getmProfileURL());

        return listItemView;
    }
}
