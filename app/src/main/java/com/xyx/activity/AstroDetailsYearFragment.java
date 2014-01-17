package com.xyx.activity;

import java.util.ArrayList;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AstroDetailsYearFragment extends Fragment {
	
	View _view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		_view = inflater.inflate(R.layout.activity_astro_details_year, null);
		
		//TODO: initialize the view
		DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent()
		{
			@Override
			public void updateUI(ArrayList<String> data) {
				// TODO Auto-generated method stub


                Integer noInteger = getArguments().getInt("no");
                //update the title
                TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstroMore);
                titleTextView.setText(getResources().getStringArray(R.array.getTypeAstro)[noInteger]);
                titleTextView.setTextColor(Utils.randomColor(Color.BLACK));
			}
		});

        api.execute(String.valueOf(getArguments().getInt("astroIndex")), getArguments().getString("type"));
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
