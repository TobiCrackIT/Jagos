package com.example.oluwatobi.jagos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.data;
import static android.R.attr.progressBarStyleInverse;
import static com.example.oluwatobi.jagos.R.id.url;

public class ProfileActivity extends AppCompatActivity{

    private static final String LOG_TAG=ProfileActivity.class.getSimpleName();
    private static final String JSON_RESPONSE="https://api.github.com/search/users?q=language:java+location:lagos";
    private ProfileAdapter PAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String url=getIntent().getStringExtra("url");
        String username=getIntent().getStringExtra("username");

        TextView usernameTV=(TextView)findViewById(R.id.anyName);
        usernameTV.setText(username);

        TextView urlTV=(TextView)findViewById(R.id.url);
        urlTV.setText(url);

        Intent gTent=getIntent();
        Bitmap profileImage=gTent.getParcelableExtra("pics");
        ImageView profileIV=(ImageView)findViewById(R.id.profilePic);
        profileIV.setImageBitmap(profileImage);
        /*/JavaDeveloperASyncTask JSyncTask=new JavaDeveloperASyncTask();
        //JSyncTask.execute(JSON_RESPONSE);*/
    }

    public void visitURL(View v){
        Uri profilePage=Uri.parse(getIntent().getStringExtra("url"));
        Intent browserIntent=new Intent(Intent.ACTION_VIEW,profilePage);
        startActivity(browserIntent);
    }



    public void share(View v){
        String shareURL=getIntent().getStringExtra("url");
        String shareUsername=getIntent().getStringExtra("username");
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Checkout this awesome developer @<"+shareUsername+">, <"+shareURL+">."));
    }
}
