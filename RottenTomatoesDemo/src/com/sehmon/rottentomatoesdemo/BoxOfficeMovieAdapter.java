package com.sehmon.rottentomatoesdemo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BoxOfficeMovieAdapter extends ArrayAdapter<BoxOfficeMovie> {
	
	public BoxOfficeMovieAdapter(Context context, ArrayList<BoxOfficeMovie> aMovies){
		super(context, 0, aMovies);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		BoxOfficeMovie movie = getItem(position);
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_box_office_movie, null);
			
		}
		
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
		TextView tvCast = (TextView) convertView.findViewById(R.id.tvCast);
		ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
		
		tvTitle.setText(movie.getTitle());
		tvCriticsScore.setText("Score: " + movie.getCriticsScore() +"%" );
		tvCast.setText(movie.getCastList());
		Picasso.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
		
		return convertView;
		
	}
}
