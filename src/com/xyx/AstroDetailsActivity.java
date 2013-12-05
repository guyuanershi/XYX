package com.xyx;

import java.util.List;

import com.xyx.util.Utils;

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
		String[] data = intent.getStringArrayExtra(Utils.EXTRA_ASTRO_DATA);
		TextView tv = (TextView)findViewById(R.id.astroDetails);
		StringBuilder sb = new StringBuilder();
		for (String s : data){
			sb.append(s + "\n");
		}
		tv.setText(sb.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details, menu);
		return true;
	}

}
