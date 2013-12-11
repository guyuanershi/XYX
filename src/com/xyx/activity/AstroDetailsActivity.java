package com.xyx.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
		
		Intent intent = getIntent();
		Integer index = intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA);
		String gettype = intent.getExtras().getString(Utils.EXTRA_GET_TYPE_OF_ASTRO);
		DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent() {
			
			@Override
			public void updateUI(ArrayList<String> data) {
				if (data == null)
					return;
				
				TextView tv = (TextView)findViewById(R.id.astroDetails);
				tv.setText(Utils.processRawData(data));
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
	
	
	
<<<<<<< HEAD
	class DataFormAPIEvent implements IDataFromMyAPIEvent {

		@Override
		public void loadData() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateUI(ArrayList<String> data) {
			if (data == null)
				return;
			
			TextView tvSummary = (TextView)findViewById(R.id.summary);
			TextView tvSummaryVal = (TextView)findViewById(R.id.summary_val);
			String strVal = processRawData(data,tvSummary.getText().toString());
			tvSummaryVal.setText(strVal);
			
			TextView tvLove = (TextView)findViewById(R.id.love);
			TextView tvLoveVal = (TextView)findViewById(R.id.love_val);
			tvLoveVal.setText(processRawData(data,tvLove.getText().toString()));
			
			TextView tvWork = (TextView)findViewById(R.id.work);
			TextView tvWorkVal = (TextView)findViewById(R.id.work_val);
			tvWorkVal.setText(processRawData(data,tvWork.getText().toString()));
			
			TextView tvMoney = (TextView)findViewById(R.id.money);
			TextView tvMoneyVal = (TextView)findViewById(R.id.money_val);
			tvMoneyVal.setText(processRawData(data,tvMoney.getText().toString()));
			
			TextView tvHealth = (TextView)findViewById(R.id.health);
			TextView tvHealthVal = (TextView)findViewById(R.id.health_val);
			tvHealthVal.setText(processRawData(data,tvHealth.getText().toString()));
			
			TextView tvBusiness = (TextView)findViewById(R.id.business);
			TextView tvBusinessVal = (TextView)findViewById(R.id.business_val);
			tvBusinessVal.setText(processRawData(data,tvBusiness.getText().toString()));
			
			TextView tvLuckyColor = (TextView)findViewById(R.id.lucky_color);
			TextView tvLuckyColorVal = (TextView)findViewById(R.id.lucky_color_val);
			tvLuckyColorVal.setText(processRawData(data,tvLuckyColor.getText().toString()));
			
			TextView tvLuckyNum = (TextView)findViewById(R.id.lucky_num);
			TextView tvLuckyNumVal = (TextView)findViewById(R.id.lucky_num_val);
			tvLuckyNumVal.setText(processRawData(data,tvLuckyNum.getText().toString()));
			
			TextView tvFitAstro = (TextView)findViewById(R.id.fit_astro);
			TextView tvFitAstroVal = (TextView)findViewById(R.id.fit_astro_val);
			tvFitAstroVal.setText(processRawData(data,tvFitAstro.getText().toString()));
			
			TextView tvSummaryText = (TextView)findViewById(R.id.summary_text);
			TextView tvSummaryTextVal = (TextView)findViewById(R.id.summary_text_val);
			strVal = processRawData(data,tvSummaryText.getText().toString());
			tvSummaryTextVal.setText(strVal);
		}
		
		
		private String processRawData(ArrayList<String> rawData, String dataType)
		{
			String sb = "";
			for (String s : rawData){
				s = s.replaceAll("\\{|\\}", "");
				String[] ss = s.split("title=");
				if(ss.length == 2)
				{
					String s2 = ss[0].replaceAll("value=", "      ").replaceAll(", rank=0,|, rank=", "");
					if(ss[1].equals(dataType))
					{
						sb = s2.trim();
						break;
					}
					//sb = ss[1] + s2 +  "\n" + sb;
				}
				else
				{
					sb= ss[0] + "\n" + sb;
				}
			}
			return sb;
		}
		
		private String getData(String dataType)
		{
			String strval = "";
			
			return strval;
		}
		
	}
=======
	
>>>>>>> upstream/master

}
