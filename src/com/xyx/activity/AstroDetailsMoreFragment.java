package com.xyx.activity;

import com.xyx.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AstroDetailsMoreFragment extends Fragment {

	View _view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		_view = inflater.inflate(R.layout.activity_astro_details_more, null);
		
		//TODO: initialize the view
		
		
		Integer noInteger = getArguments().getInt("no");
		String type = getArguments().getString("type");
		//update the title
		TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstroMore);
		titleTextView.setText(type);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View parent = (View)_view.getParent();
		if (parent != null && parent instanceof ViewGroup){
			((ViewGroup)parent).removeView(_view);
		}
		return _view;
		
	}

}
