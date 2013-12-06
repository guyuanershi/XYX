package com.xyx.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.xyx.R;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.TextureView;
import android.widget.TextView;

public class AstroDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_astro_details);
		
		Intent intent = getIntent();
		//String[] data = intent.getStringArrayExtra(Utils.EXTRA_ASTRO_DATA);
		Integer index = intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA);
		
		DataFromMyAPI api = new DataFromMyAPI();
		ArrayList<String> data;
		try {
			//data = api.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, String.valueOf(index)).get();
			data = api.execute(String.valueOf(index)).get();
			TextView tv = (TextView)findViewById(R.id.astroDetails);
			StringBuilder sb = new StringBuilder();
			for (String s : data){
				sb.append(s + "\n");
			}
			tv.setText(sb.toString());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details, menu);
		return true;
	}

}
