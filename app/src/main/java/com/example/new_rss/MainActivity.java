package com.example.new_rss;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.new_rss.Adapter.FeedAdapter;
import com.example.new_rss.Common.HTTPDataHandler;
import com.example.new_rss.Model.RSSObject;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    //instance variables
    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS link
    private final String rss_link = "https://www.iltalehti.fi/rss/urheilu.xml";
    private final String rss_to_json_api = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining the the toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Sports RSS");
        setSupportActionBar(toolbar);

        //defining the recyclerview
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //loading the rss data
        loadRss();
    }

    //Creating method for loading the Rss-feed using Async Task
    private void loadRss() {

        //Creating the async task
        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {

            //creating a dialog
            ProgressDialog mDialog = new ProgressDialog(MainActivity.this);

            //Runs in the UI before the background thread is called
            @Override
            protected void onPreExecute() {

                //setting the message and showing it
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            //This runs in the background thread
            @Override
            protected String doInBackground(String... params) {
                String result;
                String urlString = params[0];

                //creating a new instance of httpdatahandler and getting the http data stream
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(urlString);

                //Returning the data from the url
                return result;
            }

            //Runs in the UI when the background thread is finished
            @Override
            protected void onPostExecute(String result) {
                mDialog.dismiss();

                //parsing json data to java object using Gson
                rssObject = new Gson().fromJson(result, RSSObject.class);

                //loading a new dataset to the adapter
                FeedAdapter adapter = new FeedAdapter(rssObject, getBaseContext());
                recyclerView.setAdapter(adapter);
                //notify if the dataset is updated
                adapter.notifyDataSetChanged();
            }
        };

        //Using stringbuilder to create the path for the converted rss link
        StringBuilder urlGetData = new StringBuilder(rss_to_json_api);
        urlGetData.append(rss_link);

        //Executing the async task
        loadRSSAsync.execute(urlGetData.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //refresh the feed when refresh icon is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_refresh)
            loadRss();
        return true;
    }
}
