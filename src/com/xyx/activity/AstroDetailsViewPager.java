package com.xyx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;
import com.xyx.util.Utils;

public class AstroDetailsViewPager extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_astro_viewpager);
		Intent intent = getIntent();
		
		initViewPager(intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA),
				      intent.getExtras().getString(Utils.EXTRA_GET_TYPE_OF_ASTRO));
	}
	
	class Myadapter extends FragmentPagerAdapter{
		private int _astroIndex;
		private String _astroName;
		public Myadapter(FragmentManager fm, int astroIndex, String astroName){
			super(fm);
			_astroIndex = astroIndex;
			_astroName = astroName;
		}

		@Override
		public Fragment getItem(int possition) {
			Fragment fragment = null;
			GetTypeOfAstro typeOfAstro = GetTypeOfAstro.values()[possition];
			switch (typeOfAstro) {
			case TODAY:
			case TOMORROW:
			case YEAR:
				fragment = new AstroDetailsFragment();
				break;
			case WEEK:
			case MONTH:
				fragment = new AstroDetailsMoreFragment();
				break;
			default:
				fragment = new AstroDetailsFragment();
				break;
			}
			
			Bundle args = new Bundle();
			args.putInt("no", possition);
			args.putString("type", typeOfAstro.name());
			args.putInt("astroIndex", _astroIndex);
			args.putString("astroName", _astroName);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return GetTypeOfAstro.values().length;
		}
		
	}
	
	private void initViewPager(int astroIndex, String astroname) {
		ViewPager viewPager = (ViewPager)findViewById(R.id.vPager);
		viewPager.setAdapter(new Myadapter(getSupportFragmentManager(), astroIndex, astroname));
	}

}
