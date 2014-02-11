package com.xyx.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

public class AstroDetailsFragment extends Fragment {

	private View _view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater(savedInstanceState);
        _view = inflater.inflate(R.layout.activity_astro_details, null);
        final Bundle bundle = getArguments();

        final SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

		DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent() {
			@Override
			public void updateUI(ArrayList<String> data) {
                _updateUI(data, bundle);
			}

            @Override
            public void SavingData(ArrayList<String> data) {
                //save data into preference
                String type = bundle.getString("type");
                SharedPreferences.Editor editor = sharedPref.edit();
                if (type == GetTypeOfAstro.TODAY.name()){
                    editor.putInt(Utils.PREFERENCE_TODAY_TIME, Utils.CURRENT_TIME);
                    editor.putStringSet(Utils.PREFERENCE_TODAY, new HashSet<String>(data));
                } else if (type == GetTypeOfAstro.TOMORROW.name()){
                    editor.putStringSet(Utils.PREFERENCE_TOMMOROW, new HashSet<String>(data));
                }
                editor.commit();
            }
        });

        Integer current_time = -1;
        String typeString = "";
        String type = bundle.getString("type");
        if (type == GetTypeOfAstro.TODAY.name()){
            current_time = sharedPref.getInt(Utils.PREFERENCE_TODAY_TIME, -1);
            typeString = Utils.PREFERENCE_TODAY;
        } else if (type == GetTypeOfAstro.TOMORROW.name()){
            current_time = sharedPref.getInt(Utils.PREFERENCE_TOMMOROW_TIME, -1);
            typeString = Utils.PREFERENCE_TOMMOROW;
        }

        if (current_time == -1 || current_time != Utils.CURRENT_TIME){
		    api.execute(String.valueOf(bundle.getInt("astroIndex")), bundle.getString("type"));
        } else {
            Set<String> set = sharedPref.getStringSet(typeString, null);
            if (set != null){
                _updateUI(new ArrayList<String>(set), bundle);
            }
        }
	}

    private void _updateUI(ArrayList<String> data, Bundle bundle){
        if (data == null)
            return;

        String strVal = Utils.processRawData(data,"");
        ((Activity)_view.getContext()).setTitle(strVal);

        TextView tvSummary = (TextView)_view.findViewById(R.id.summary);
        RatingBar rb = (RatingBar)_view.findViewById(R.id.ratingbarSummary);
        strVal = Utils.processRawData(data,tvSummary.getText().toString());
        rb.setRating(Integer.parseInt(strVal));

        TextView tvLove = (TextView)_view.findViewById(R.id.love);
        RatingBar rbLove = (RatingBar)_view.findViewById(R.id.ratingbarLove);
        strVal = Utils.processRawData(data,tvLove.getText().toString());
        rbLove.setRating(Integer.parseInt(strVal));

        TextView tvWork = (TextView)_view.findViewById(R.id.work);
        RatingBar rbWork = (RatingBar)_view.findViewById(R.id.ratingbarWork);
        strVal = Utils.processRawData(data,tvWork.getText().toString());
        rbWork.setRating(Integer.parseInt(strVal));

        TextView tvMoney = (TextView)_view.findViewById(R.id.money);
        RatingBar rbMoney = (RatingBar)_view.findViewById(R.id.ratingbarMoney);
        strVal = Utils.processRawData(data,tvMoney.getText().toString());
        rbMoney.setRating(Integer.parseInt(strVal));

        TextView tvHealth = (TextView)_view.findViewById(R.id.health);
        TextView tvHealthVal = (TextView)_view.findViewById(R.id.health_val);
        tvHealthVal.setText(Utils.processRawData(data,tvHealth.getText().toString()));

        TextView tvBusiness = (TextView)_view.findViewById(R.id.business);
        TextView tvBusinessVal = (TextView)_view.findViewById(R.id.business_val);
        tvBusinessVal.setText(Utils.processRawData(data,tvBusiness.getText().toString()));

        TextView tvLuckyColor = (TextView)_view.findViewById(R.id.lucky_color);
        TextView tvLuckyColorVal = (TextView)_view.findViewById(R.id.lucky_color_val);
        tvLuckyColorVal.setText(Utils.processRawData(data,tvLuckyColor.getText().toString()));

        TextView tvLuckyNum = (TextView)_view.findViewById(R.id.lucky_num);
        TextView tvLuckyNumVal = (TextView)_view.findViewById(R.id.lucky_num_val);
        tvLuckyNumVal.setText(Utils.processRawData(data,tvLuckyNum.getText().toString()));

        TextView tvFitAstro = (TextView)_view.findViewById(R.id.fit_astro);
        TextView tvFitAstroVal = (TextView)_view.findViewById(R.id.fit_astro_val);
        tvFitAstroVal.setText(Utils.processRawData(data,tvFitAstro.getText().toString()));

        TextView tvSummaryText = (TextView)_view.findViewById(R.id.summary_text);
        TextView tvSummaryTextVal = (TextView)_view.findViewById(R.id.summary_text_val);
        strVal = Utils.processRawData(data,tvSummaryText.getText().toString());
        tvSummaryTextVal.setText(strVal);

        Integer noInteger = bundle.getInt("no");
        String astroName = bundle.getString("astroName");

        //update the title
        //not work?
        TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstro);
        int id = Utils.getResourceByName(R.drawable.class, astroName);
        if (id != 0){
            //ImageSpan imageSpan = new ImageSpan(_view.getResources().getDrawable(id));
            //SpannableString spannableString = new SpannableString(getResources().getStringArray(R.array.getTypeAstro)[noInteger]);
            //spannableString.setSpan(imageSpan, spannableString.length(), spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            titleTextView.setText(getResources().getStringArray(R.array.getTypeAstro)[noInteger]);
            //titleTextView.setTextColor(Utils.randomColor(Color.BLACK));
        }
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
