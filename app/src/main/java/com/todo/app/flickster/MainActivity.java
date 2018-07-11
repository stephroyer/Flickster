package com.todo.app.flickster;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.todo.app.flickster.Adapter.MovieArrayAdapter;
import com.todo.app.flickster.models.movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ArrayList<movies> Movies;
    MovieArrayAdapter movieArrayAdapter;
    ListView lvitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        lvitem=(ListView) findViewById(R.id.lvvideo);
        Movies= new ArrayList<>();
        movieArrayAdapter= new MovieArrayAdapter(this, Movies);
        lvitem.setAdapter(movieArrayAdapter);
        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
//        String url = " https://api.themoviedb.org/3/movie/209112/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults =null;
                progressDialog.dismiss();

                try {
                    movieJsonResults = response.getJSONArray("results");
                    Movies.addAll(movies.fromJSONArray(movieJsonResults));
                    movieArrayAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", Movies.toString());

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "no internet access", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
