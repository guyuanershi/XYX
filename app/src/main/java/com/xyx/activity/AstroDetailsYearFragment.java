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
import android.widget.RatingBar;
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
                //summary
                TextView tvSummary = (TextView)_view.findViewById(R.id.summary);
                RatingBar rb = (RatingBar)_view.findViewById(R.id.ratingbarSummary);
                String strVal = Utils.processYearAstroData(data, tvSummary.getText().toString(), false);
                rb.setRating(Integer.parseInt(strVal));
                TextView tvSummaryVal = (TextView)_view.findViewById(R.id.summary_val);
                strVal = Utils.processYearAstroData(data, tvSummary.getText().toString(), true);
                tvSummaryVal.setText(strVal);

                //study
                TextView tvStudy = (TextView)_view.findViewById(R.id.study);
                rb = (RatingBar)_view.findViewById(R.id.ratingbarStudy);
                strVal = Utils.processYearAstroData(data,tvStudy.getText().toString(),false);
                rb.setRating(Integer.parseInt(strVal));
                TextView tvStudyVal = (TextView)_view.findViewById(R.id.study_val);
                strVal = Utils.processYearAstroData(data, tvStudy.getText().toString(), true);
                tvStudyVal.setText(strVal);

                //work
                TextView tvWork = (TextView)_view.findViewById(R.id.work);
                rb = (RatingBar)_view.findViewById(R.id.ratingbarWork);
                strVal = Utils.processYearAstroData(data,tvWork.getText().toString(),false);
                rb.setRating(Integer.parseInt(strVal));
                TextView tvWorkVal = (TextView)_view.findViewById(R.id.work_val);
                strVal = Utils.processYearAstroData(data, tvWork.getText().toString(), true);
                tvWorkVal.setText(strVal);

                //money
                TextView tvMoney = (TextView)_view.findViewById(R.id.money);
                rb = (RatingBar)_view.findViewById(R.id.ratingbarMoney);
                strVal = Utils.processYearAstroData(data, tvMoney.getText().toString(), false);
                rb.setRating(Integer.parseInt(strVal));
                TextView tvMoneyVal = (TextView)_view.findViewById(R.id.money_val);
                strVal = Utils.processYearAstroData(data, tvMoney.getText().toString(), true);
                tvMoneyVal.setText(strVal);

                //love
                TextView tvLove = (TextView)_view.findViewById(R.id.love);
                rb = (RatingBar)_view.findViewById(R.id.ratingbarLove);
                strVal = Utils.processYearAstroData(data,tvLove.getText().toString(),false);
                rb.setRating(Integer.parseInt(strVal));
                TextView tvLoveVal = (TextView)_view.findViewById(R.id.love_val);
                strVal = Utils.processYearAstroData(data,tvLove.getText().toString(),true);
                tvLoveVal.setText(strVal);

                 //tips
                TextView tvTips = (TextView)_view.findViewById(R.id.tips);
                TextView tvTipsVal = (TextView)_view.findViewById(R.id.tips_val);
                strVal = Utils.processYearAstroData(data, tvTips.getText().toString(), true);
                tvTipsVal.setText(strVal);
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
