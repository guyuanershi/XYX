package com.xyx.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

public class AstroDetailsMoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_astro_details_more);
		
		Intent intent = getIntent();
		Integer index = intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA);
		String gettype = intent.getExtras().getString(Utils.EXTRA_GET_TYPE_OF_ASTRO);
		
		DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent() {
			
			@Override
			public void updateUI(ArrayList<String> data) {
				TableLayout tableLayout = (TableLayout)findViewById(R.id.astroMoreDetails);
				
			}
		});
		
		api.execute(String.valueOf(index), gettype);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details_more, menu);
		return true;
	}

}
