package com.example.oluwatobi.jagos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oluwatobi.jagos.JavaDeveloper;
import com.example.oluwatobi.jagos.R;

import java.util.ArrayList;

import static android.R.attr.resource;
import static com.example.oluwatobi.jagos.R.id.avatar;

/**
 * Created by oluwatobi on 23-Aug-17.
 */

public class JDAdapter extends ArrayAdapter<JavaDeveloper> {
    public JDAdapter(Context context, ArrayList<JavaDeveloper> JDList) {
        super(context,0, JDList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_jd,parent,false);
        }

        JavaDeveloper currentDeveloper=getItem(position);

        ImageView avatar=(ImageView)listItemView.findViewById(R.id.avatar);
        avatar.setImageBitmap(currentDeveloper.getmProfilePicture());

        TextView usernameTV=(TextView)listItemView.findViewById(R.id.user);
        usernameTV.setText(currentDeveloper.getmUsername());

        return listItemView;
    }
}
