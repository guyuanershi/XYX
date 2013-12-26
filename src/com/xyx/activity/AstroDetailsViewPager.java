package com.xyx.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;

public class AstroDetailsViewPager extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_astro_viewpager);
		initViewPager();
	}
	
	class Myadapter extends FragmentPagerAdapter{
		
		public Myadapter(FragmentManager fm){
			super(fm);
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
			case MOUNTH:
				fragment = new AstroDetailsMoreFragment();
			default:
				fragment = new AstroDetailsFragment();
				break;
			}
			
			Bundle args = new Bundle();
			args.putInt("no", possition);
			args.putString("type", typeOfAstro.name());
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return GetTypeOfAstro.values().length;
		}
		
		
	}
	
	private void initViewPager() {
		ViewPager viewPager = (ViewPager)findViewById(R.id.vPager);
		viewPager.setAdapter(new Myadapter(getSupportFragmentManager()));
	}

}
