package com.xyx.activity;

import java.util.ArrayList;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class AstroDetailsMoreFragment extends Fragment {

	View _view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		_view = inflater.inflate(R.layout.activity_astro_details_more, null);
        final Bundle bundle = getArguments();
		
		//TODO: initialize the view
		DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent()
		{
			 
			@Override
			public void updateUI(ArrayList<String> data) {
				if (data == null)
					return;
				
				//String strVal = Utils.processWeekData(data,"", false);
				//((Activity)view.getContext()).setTitle(strVal);
				
				TextView tvSummary = (TextView)_view.findViewById(R.id.summary);
				RatingBar rb = (RatingBar)_view.findViewById(R.id.ratingbarSummary);
				String strVal = Utils.processWeekData(data,tvSummary.getText().toString(),false);
				rb.setRating(Integer.parseInt(strVal));
				TextView tvSummaryVal = (TextView)_view.findViewById(R.id.summary_val);
				strVal = Utils.processWeekData(data,tvSummary.getText().toString(),true);
				tvSummaryVal.setText(strVal);

				
				//love data
				TextView tvLove = (TextView)_view.findViewById(R.id.love);
				ArrayList<String> arrayList = Utils.processWeekLoveData(data, tvLove.getText().toString());
				
				if(arrayList.size()==4)
				{
					RatingBar rbWithLove = (RatingBar)_view.findViewById(R.id.ratingbarWithLove);
					rbWithLove.setRating(Integer.parseInt(arrayList.get(0)));
					TextView tvLoveVal = (TextView)_view.findViewById(R.id.withlove_val);
					tvLoveVal.setText(arrayList.get(2));
  				    RatingBar rbNoLove = (RatingBar)_view.findViewById(R.id.ratingbarNoLove);
					rbNoLove.setRating(Integer.parseInt(arrayList.get(1)));
					TextView tvNoLoveVal = (TextView)_view.findViewById(R.id.nolove_val);
					tvNoLoveVal.setText(arrayList.get(3));
				}
				
				//health
				TextView tvhealth = (TextView)_view.findViewById(R.id.health);
				rb = (RatingBar)_view.findViewById(R.id.ratingbarHealth);
				strVal = Utils.processWeekData(data,tvhealth.getText().toString(),false);
				rb.setRating(Integer.parseInt(strVal));
				TextView tvHealthVal = (TextView)_view.findViewById(R.id.health_val);
				strVal = Utils.processWeekData(data,tvhealth.getText().toString(),true);
				tvHealthVal.setText(strVal);
				
				//workstudy
				TextView tvworkStudy = (TextView)_view.findViewById(R.id.workStudy);
				rb = (RatingBar)_view.findViewById(R.id.ratingbarWorkStudy);
				strVal = Utils.processWeekData(data,tvworkStudy.getText().toString(),false);
				rb.setRating(Integer.parseInt(strVal));
				TextView tvWorkStudyVal = (TextView)_view.findViewById(R.id.workStudy_val);
				strVal = Utils.processWeekData(data,tvworkStudy.getText().toString(),true);
				tvWorkStudyVal.setText(strVal);
				
				//sex
				TextView tvSex = (TextView)_view.findViewById(R.id.sex);
				rb = (RatingBar)_view.findViewById(R.id.ratingbarSex);
				strVal = Utils.processWeekData(data,tvSex.getText().toString(),false);
				rb.setRating(Integer.parseInt(strVal));
				TextView tvSexVal = (TextView)_view.findViewById(R.id.sex_val);
				strVal = Utils.processWeekData(data,tvSex.getText().toString(),true);
				tvSexVal.setText(strVal);
				
				//luckyday
				TextView tvlucky = (TextView)_view.findViewById(R.id.luckyday);
				TextView tvluckyVal = (TextView)_view.findViewById(R.id.luckyday_val);
				strVal = Utils.processWeekData(data,tvlucky.getText().toString(),true);
				tvluckyVal.setText(strVal);
				
				//badday
				TextView tvbad = (TextView)_view.findViewById(R.id.badday);
				TextView tvbadVal = (TextView)_view.findViewById(R.id.badday_val);
				strVal = Utils.processWeekData(data,tvbad.getText().toString(),true);
				tvbadVal.setText(strVal);
				
				//tips
				TextView tvTips = (TextView)_view.findViewById(R.id.tips);
				TextView tvTipsVal = (TextView)_view.findViewById(R.id.tips_val);
				strVal = Utils.processWeekData(data,tvTips.getText().toString(),true);
				tvTipsVal.setText(strVal);

                //update the title
                Integer noInteger = bundle.getInt("no");
                TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstroMore);
                titleTextView.setText(getResources().getStringArray(R.array.getTypeAstro)[noInteger]);
			}
		});
		
		api.execute(String.valueOf(bundle.getInt("astroIndex")), bundle.getString("type"));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View parent = (View)_view.getParent();
		if (parent != null && parent instanceof ViewGroup){
			((ViewGroup)parent).removeView(_view);
		}
		return _view;
		
	}

}
