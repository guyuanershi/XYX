package com.xyx.activity;

import com.xyx.R;
import com.xyx.R.layout;
import com.xyx.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AstroDetailsMoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_astro_details_more);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.astro_details_more, menu);
		return true;
	}

}
