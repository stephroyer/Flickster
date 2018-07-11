package com.todo.app.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class movies {

    String posterPath;
    String originalTitle;
    String overview;
    String backdrop;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
    public String getBackdrop() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop);
    }


    public movies(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.backdrop = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview =jsonObject.getString("overview");
    }

    public static ArrayList<movies> fromJSONArray(JSONArray array)
    {
        ArrayList<movies> results =new ArrayList<>();

        for (int x =0; x<array.length(); x++){
            try {
                results.add( new movies(array.getJSONObject(x)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }


}
