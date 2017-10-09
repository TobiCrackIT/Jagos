package com.example.oluwatobi.jagos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.oluwatobi.jagos.R.id.profilePic;

/**
 * Created by oluwatobi on 24-Aug-17.
 */

public final class QueryUtils {
    private static final String LOG_TAG=QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }

    private static List<JavaDeveloper> extractFromJSON(String javaDeveloperJSON){
        if(TextUtils.isEmpty(javaDeveloperJSON)){
            return null;
        }

        List<JavaDeveloper> javaDevelopers=new ArrayList<>();

        try{
            JSONObject root=new JSONObject(javaDeveloperJSON);
            JSONArray items=root.getJSONArray("items");
            for(int i=0;i<=items.length();i++){
                JSONObject array=items.getJSONObject(i);
                String username=array.getString("login");
                String profileURL=array.getString("html_url");
                /////
                String imageURL=array.getString("avatar_url");
                Bitmap profilePic=getBitmapFromURL(imageURL);

                JavaDeveloper thisJavaDeveloper=new JavaDeveloper(username,profilePic,profileURL);
                javaDevelopers.add(thisJavaDeveloper);
            }
        }
        catch(JSONException j){
            Log.e("QueryUtils","Problem parsing the JavaDeveloper JSON Response.",j);
        }

        return javaDevelopers;
    }

    //CREATING THE URL
    private static URL createUrl(String StringUrl){
        URL url=null;
        try{
            url=new URL(StringUrl);
        }catch(MalformedURLException e){
            Log.e(LOG_TAG,"Error creating URL object.",e);
        }
        return url;
    }


    //SENDING THE REQUEST
    private static String makeHttpRequest(URL url)throws IOException{
        String JSONResponse="";
        if(url==null){
            return JSONResponse;
        }

        HttpURLConnection URLConnection=null;
        InputStream iStream=null;
        try{
            URLConnection=(HttpURLConnection) url.openConnection();
            URLConnection.setReadTimeout(10000);
            URLConnection.setConnectTimeout(15000);
            URLConnection.setRequestMethod("GET");
            URLConnection.connect();

            if(URLConnection.getResponseCode()==200){
                iStream=URLConnection.getInputStream();
                JSONResponse=readFromStream(iStream);
            }else{
                Log.e(LOG_TAG,"Error Response Code-"+URLConnection.getResponseCode());
            }
        }catch (IOException i){
            Log.e(LOG_TAG,"Problem getting JavaDeveloper JSON responses",i);
        }finally {
            if(URLConnection!=null)
                URLConnection.disconnect();

            if(iStream!=null)
                iStream.close();
        }

        return JSONResponse;
    }

    private static String readFromStream(InputStream iStream)throws IOException{
        StringBuilder displayedText=new StringBuilder();
        if(iStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(iStream, Charset.forName("UTF-8"));
            BufferedReader bfrReader=new BufferedReader(inputStreamReader);
            String line=bfrReader.readLine();
            while (line!=null){
                displayedText.append(line);
                line=bfrReader.readLine();
            }
        }
        return displayedText.toString();
    }

    private static Bitmap getBitmapFromURL(String link){
        try{
            URL url= new URL(link);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream input=urlConnection.getInputStream();
            Bitmap image=BitmapFactory.decodeStream(input);
            return image;
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem getting image",e);
            return null;
        }
    }

    public static List<JavaDeveloper> fetchData(String targetUrl){
        URL url=createUrl(targetUrl);
        String JSONResponse=null;
        try{
            JSONResponse=makeHttpRequest(url);
        }catch(IOException i){
            Log.e(LOG_TAG,"Problem making HTTP request.",i);
        }

        List<JavaDeveloper> javaDeveloperList=extractFromJSON(JSONResponse);
        return javaDeveloperList;
    }
}
