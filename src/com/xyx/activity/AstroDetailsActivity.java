package com.xyx.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

public class AstroDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//show activity
		setContentView(R.layout.activity_astro_details);

		final Activity activity = this;
		Intent intent = getIntent();
		Integer index = intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA);
		String gettype = intent.getExtras().getString(Utils.EXTRA_GET_TYPE_OF_ASTRO);
		DataFromMyAPI api = new DataFromMyAPI(getLayoutInflater().inflate(R.layout.activity_astro_details, null), new IDataFromMyAPIEvent() {
			
			@Override
			public void updateUI(ArrayList<String> data, View view) {
				if (data == null)
					return;
				
				String strVal = Utils.processRawData(data,"");
				activity.setTitle(strVal);
				
				TextView tvSummary = (TextView)findViewById(R.id.summary);
				RatingBar rb = (RatingBar)findViewById(R.id.ratingbarSummary);
				strVal = Utils.processRawData(data,tvSummary.getText().toString());
				rb.setRating(Integer.parseInt(strVal));
				
				TextView tvLove = (TextView)findViewById(R.id.love);
				RatingBar rbLove = (RatingBar)findViewById(R.id.ratingbarLove);
				strVal = Utils.processRawData(data,tvLove.getText().toString());
				rbLove.setRating(Integer.parseInt(strVal));
				
				TextView tvWork = (TextView)findViewById(R.id.work);
				RatingBar rbWork = (RatingBar)findViewById(R.id.ratingbarWork);
				strVal = Utils.processRawData(data,tvWork.getText().toString());
				rbWork.setRating(Integer.parseInt(strVal));
				
				TextView tvMoney = (TextView)findViewById(R.id.money);
				RatingBar rbMoney = (RatingBar)findViewById(R.id.ratingbarMoney);
				strVal = Utils.processRawData(data,tvMoney.getText().toString());
				rbMoney.setRating(Integer.parseInt(strVal));
				
				TextView tvHealth = (TextView)findViewById(R.id.health);
				TextView tvHealthVal = (TextView)findViewById(R.id.health_val);
				tvHealthVal.setText(Utils.processRawData(data,tvHealth.getText().toString()));
				
				TextView tvBusiness = (TextView)findViewById(R.id.business);
				TextView tvBusinessVal = (TextView)findViewById(R.id.business_val);
				tvBusinessVal.setText(Utils.processRawData(data,tvBusiness.getText().toString()));
				
				TextView tvLuckyColor = (TextView)findViewById(R.id.lucky_color);
				TextView tvLuckyColorVal = (TextView)findViewById(R.id.lucky_color_val);
				tvLuckyColorVal.setText(Utils.processRawData(data,tvLuckyColor.getText().toString()));
				
				TextView tvLuckyNum = (TextView)findViewById(R.id.lucky_num);
				TextView tvLuckyNumVal = (TextView)findViewById(R.id.lucky_num_val);
				tvLuckyNumVal.setText(Utils.processRawData(data,tvLuckyNum.getText().toString()));
				
				TextView tvFitAstro = (TextView)findViewById(R.id.fit_astro);
				TextView tvFitAstroVal = (TextView)findViewById(R.id.fit_astro_val);
				tvFitAstroVal.setText(Utils.processRawData(data,tvFitAstro.getText().toString()));
				
				TextView tvSummaryText = (TextView)findViewById(R.id.summary_text);
				TextView tvSummaryTextVal = (TextView)findViewById(R.id.summary_text_val);
				strVal = Utils.processRawData(data,tvSummaryText.getText().toString());
				tvSummaryTextVal.setText(strVal);
			
			}
		});
		api.execute(String.valueOf(index), gettype);			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details, menu);
		return true;
	}
	
}
