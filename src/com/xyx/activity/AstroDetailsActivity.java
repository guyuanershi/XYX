package com.xyx.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;
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
//		GetTypeOfAstro typeOfAstro = GetTypeOfAstro.valueOf(gettype);
		DataFromMyAPI api = new DataFromMyAPI(new DataFormAPIEvent());
		api.execute(String.valueOf(index), gettype);			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details, menu);
		return true;
	}
	
	
	
	class DataFormAPIEvent implements IDataFromMyAPIEvent {

		@Override
		public void loadData() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateUI(ArrayList<String> data) {
			if (data == null)
				return;
			
			TextView tv = (TextView)findViewById(R.id.astroDetails);
			tv.setText(processRawData(data));
		}
		
		
		private String processRawData(ArrayList<String> data)
		{
			String sb = "";
			for (String s : data){
				s = s.replaceAll("\\{|\\}", "");
				String[] ss = s.split("title=");
				if(ss.length == 2)
				{
					String s2 = ss[0].replaceAll("value=", "      ").replaceAll(", rank=0,|, rank=", "");
					sb = ss[1] + s2 +  "\n" + sb;
				}
				else
				{
					sb= ss[0] + "\n" + sb;
				}
			}
			return sb;
		}
		
	}

}
