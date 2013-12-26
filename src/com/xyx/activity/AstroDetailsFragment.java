package com.xyx.activity;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

public class AstroDetailsFragment extends Fragment {
	
	private View _view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	    LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		_view = inflater.inflate(R.layout.activity_astro_details, null);
		DataFromMyAPI api = new DataFromMyAPI(_view, new IDataFromMyAPIEvent() {
			 
			@Override
			public void updateUI(ArrayList<String> data, View view) {
				if (data == null)
					return;
				
				TextView tvSummary = (TextView)view.findViewById(R.id.summary);
				TextView tvSummaryVal = (TextView)view.findViewById(R.id.summary_val);
				String strVal = Utils.processRawData(data,tvSummary.getText().toString());
				tvSummaryVal.setText(strVal);
				
				TextView tvLove = (TextView)view.findViewById(R.id.love);
				TextView tvLoveVal = (TextView)view.findViewById(R.id.love_val);
				tvLoveVal.setText(Utils.processRawData(data,tvLove.getText().toString()));
				
				TextView tvWork = (TextView)view.findViewById(R.id.work);
				TextView tvWorkVal = (TextView)view.findViewById(R.id.work_val);
				tvWorkVal.setText(Utils.processRawData(data,tvWork.getText().toString()));
				
				TextView tvMoney = (TextView)view.findViewById(R.id.money);
				TextView tvMoneyVal = (TextView)view.findViewById(R.id.money_val);
				tvMoneyVal.setText(Utils.processRawData(data,tvMoney.getText().toString()));
				
				TextView tvHealth = (TextView)view.findViewById(R.id.health);
				TextView tvHealthVal = (TextView)view.findViewById(R.id.health_val);
				tvHealthVal.setText(Utils.processRawData(data,tvHealth.getText().toString()));
				
				TextView tvBusiness = (TextView)view.findViewById(R.id.business);
				TextView tvBusinessVal = (TextView)view.findViewById(R.id.business_val);
				tvBusinessVal.setText(Utils.processRawData(data,tvBusiness.getText().toString()));
				
				TextView tvLuckyColor = (TextView)view.findViewById(R.id.lucky_color);
				TextView tvLuckyColorVal = (TextView)view.findViewById(R.id.lucky_color_val);
				tvLuckyColorVal.setText(Utils.processRawData(data,tvLuckyColor.getText().toString()));
				
				TextView tvLuckyNum = (TextView)view.findViewById(R.id.lucky_num);
				TextView tvLuckyNumVal = (TextView)view.findViewById(R.id.lucky_num_val);
				tvLuckyNumVal.setText(Utils.processRawData(data,tvLuckyNum.getText().toString()));
				
				TextView tvFitAstro = (TextView)view.findViewById(R.id.fit_astro);
				TextView tvFitAstroVal = (TextView)view.findViewById(R.id.fit_astro_val);
				tvFitAstroVal.setText(Utils.processRawData(data,tvFitAstro.getText().toString()));
				
				TextView tvSummaryText = (TextView)view.findViewById(R.id.summary_text);
				TextView tvSummaryTextVal = (TextView)view.findViewById(R.id.summary_text_val);
				strVal = Utils.processRawData(data,tvSummaryText.getText().toString());
				tvSummaryTextVal.setText(strVal);
			
			}
		});
		Integer noInteger = getArguments().getInt("no");
		String type = getArguments().getString("type");
		
		//update the title
		TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstro);
		titleTextView.setText(type);
		
		api.execute(String.valueOf(noInteger), type);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return _view;
		
	}

}
