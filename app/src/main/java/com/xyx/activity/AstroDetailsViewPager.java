package com.xyx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;
import com.xyx.util.Utils;

public class AstroDetailsViewPager extends FragmentActivity {
	
	private ImageView[] pointViews = new ImageView[5];
	private Integer currentIndex = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_astro_viewpager);
		Intent intent = getIntent();
		
		initViewPager(intent.getExtras().getInt(Utils.EXTRA_ASTRO_DATA),
				      intent.getExtras().getString(Utils.EXTRA_GET_TYPE_OF_ASTRO));
		initPoints();
	}
	
	class Myadapter extends FragmentPagerAdapter{
		private int _astroIndex;
		private String _astroName;
        private GetTypeOfAstro[] _astroTypes;
		public Myadapter(FragmentManager fm, int astroIndex, String astroName){
			super(fm);
			_astroIndex = astroIndex;
			_astroName = astroName;
            _astroTypes = GetTypeOfAstro.values();
		}

		@Override
		public Fragment getItem(int possition) {
			Fragment fragment = null;
			GetTypeOfAstro typeOfAstro = _astroTypes[possition];
            switch (typeOfAstro) {
                case TODAY:
                case TOMORROW:
                    fragment = new AstroDetailsFragment();
                    break;
                case WEEK:
                    fragment = new AstroDetailsMoreFragment();
                    break;
                case MONTH:
                    fragment = new AstroDetailsMonthFragment();
                    break;
                case YEAR:
                    fragment = new AstroDetailsYearFragment();
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
		viewPager.setOnPageChangeListener(new myPagerListener());
		viewPager.setAdapter(new Myadapter(getSupportFragmentManager(), astroIndex, astroname));
	}

	private void initPoints(){
		LinearLayout llLayout = (LinearLayout)findViewById(R.id.pps);
		
		for (int i = 0; i < pointViews.length ; i++) {
			pointViews[i] = (ImageView)llLayout.getChildAt(i);
			pointViews[i].setEnabled(false);
			pointViews[i].setTag(i);
		}
		
		currentIndex = 0;
		pointViews[currentIndex].setEnabled(true);
	}
	
	private class myPagerListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			setCurDot(arg0);
		}
	}
	
    private void setCurDot(int positon){ 
        if (positon < 0 || positon > pointViews.length - 1 || currentIndex == positon) { 
            return; 
        } 
        pointViews[positon].setEnabled(true); 
        pointViews[currentIndex].setEnabled(false); 
        currentIndex = positon; 
    } 
}
