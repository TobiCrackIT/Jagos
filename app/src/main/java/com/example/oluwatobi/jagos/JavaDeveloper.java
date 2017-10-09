package com.example.oluwatobi.jagos;

import android.graphics.Bitmap;

/**
 * Created by oluwatobi on 23-Aug-17.
 */

public class JavaDeveloper {
    private String mUsername;
    private Bitmap mProfilePicture;
    private String mProfileURL;

    public JavaDeveloper(String username,Bitmap profilePicture,String profileURL){
        mUsername=username;
        mProfilePicture=profilePicture;
        mProfileURL=profileURL;
    }

    public JavaDeveloper(String username,String profileURL){
        mUsername=username;
        mProfileURL=profileURL;
    }

    public String getmUsername() {
        return mUsername;
    }

    public Bitmap getmProfilePicture() {
        return mProfilePicture;
    }

    public String getmProfileURL(){
        return mProfileURL;
    }
}
