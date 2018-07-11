package com.todo.app.flickster.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todo.app.flickster.R;
import com.todo.app.flickster.models.movies;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<movies> {

    private  static  class ViewHolder{
        ImageView  ivimage;
        TextView tvTitle;
        TextView tvOverview;


    }

    public MovieArrayAdapter(Context context, List<movies> Movies){
        super(context, android.R.layout.simple_list_item_1, Movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        movies movie = getItem(position);
        ViewHolder viewHolder = new ViewHolder();


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.movies_item, parent,false);

        viewHolder.ivimage=(ImageView) convertView.findViewById(R.id.ivMovieImage);
//        viewHolder.ivimage.setImageResource(0);

        viewHolder.tvTitle=(TextView)convertView.findViewById(R.id.tvTitle);
       viewHolder.tvOverview=(TextView) convertView.findViewById(R.id.tvOverview);

            convertView.setTag(viewHolder);

//        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivimage);


    }else {
        viewHolder = (ViewHolder) convertView.getTag();
    }

        viewHolder.ivimage.setImageResource(0);

    int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(viewHolder.ivimage);
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Picasso.with(getContext()).load(movie.getBackdrop()).fit().centerCrop()
                .placeholder(R.drawable.asicon).into(viewHolder.ivimage);

    }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        return convertView;
    }
}
