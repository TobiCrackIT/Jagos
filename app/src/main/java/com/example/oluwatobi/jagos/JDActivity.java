package com.example.oluwatobi.jagos;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.oluwatobi.jagos.R.id.user;

public class JDActivity extends AppCompatActivity {

    private static final String LOG_TAG=JDActivity.class.getSimpleName();
    private static final String JSON_RESPONSE="https://api.github.com/search/users?q=language:java+location:lagos";
    private JDAdapter dArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        JavaDeveloperASyncTask jdas=new JavaDeveloperASyncTask();
        jdas.execute(JSON_RESPONSE);
        /*ArrayList<JavaDeveloper> developersList=new ArrayList<>();
        developersList.add(new JavaDeveloper("Moyheen",R.drawable.before_cookie));
        developersList.add(new JavaDeveloper("crownWealth",R.drawable.facebook_icon));
        developersList.add(new JavaDeveloper("dhtml",R.drawable.facebook_icon));
        developersList.add(new JavaDeveloper("aqio",R.drawable.before_cookie));
        developersList.add(new JavaDeveloper("taiwo",R.drawable.before_cookie));
        developersList.add(new JavaDeveloper("tobizzy",R.drawable.facebook_icon));
        developersList.add(new JavaDeveloper("C#",R.drawable.before_cookie));
        developersList.add(new JavaDeveloper("J_Boss",R.drawable.facebook_icon));
        developersList.add(new JavaDeveloper("ayam_pabiekun",R.drawable.before_cookie));
        developersList.add(new JavaDeveloper("tommy",R.drawable.facebook_icon));
        developersList.add(new JavaDeveloper("darkness",R.drawable.before_cookie));*/

        dArrayAdapter=new JDAdapter(this,new ArrayList<JavaDeveloper>());
        ListView dListView=(ListView)findViewById(R.id.simple_list_item);
        dListView.setAdapter(dArrayAdapter);

        dListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JavaDeveloper thisDeveloper=
                        dArrayAdapter.getItem(position);
                //String user=thisDeveloper.getmUsername();
                Intent iProfile=new Intent(JDActivity.this,ProfileActivity.class);
                iProfile.putExtra("username",thisDeveloper.getmUsername());
                iProfile.putExtra("url",thisDeveloper.getmProfileURL());
                iProfile.putExtra("pics",thisDeveloper.getmProfilePicture());
                startActivity(iProfile);
            }
        });
    }

    private class JavaDeveloperASyncTask extends AsyncTask<String,Void,List<JavaDeveloper>>{
        @Override
        protected List<JavaDeveloper> doInBackground(String... URL) {
            if(URL.length < 1||URL[0]==null){
                return null;
            }

            List<JavaDeveloper> result=QueryUtils.fetchData(URL[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<JavaDeveloper> javaDevelopers) {
            dArrayAdapter.clear();

            if(javaDevelopers!=null&&!javaDevelopers.isEmpty()){
                dArrayAdapter.addAll(javaDevelopers);
            }
        }
    }
}
