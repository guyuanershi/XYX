package com.xyx.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

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

public class AstroDetailsMonthFragment extends Fragment {

    private View _view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);
        _view = inflater.inflate(R.layout.activity_astro_details_month, null);
        final SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        final String name = getArguments().getString("astroName");

        //TODO: initialize the view
        DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent()
        {
            @Override
            public void updateUI(ArrayList<String> data) {
                _updateUI(data, null);
            }

            @Override
            public void SavingData(ArrayList<String> data) {
                //save data into preference
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(name + Utils.PREFERENCE_MONTH_TIME, Utils.CURRENT_TIME);
                editor.putStringSet(name + Utils.PREFERENCE_MONTH, new HashSet<String>(data));
                editor.commit();
            }
        });

        Integer current_time = sharedPref.getInt(name + Utils.PREFERENCE_MONTH_TIME, -1);
        if (current_time == -1 || current_time != Utils.CURRENT_TIME){
            api.execute(String.valueOf(getArguments().getInt("astroIndex")), getArguments().getString("type"));
        } else {
            Set<String> set = sharedPref.getStringSet(name + Utils.PREFERENCE_MONTH, null);
            if (set != null){
                _updateUI(new ArrayList<String>(set), null);
            }
        }
        //api.execute(String.valueOf(getArguments().getInt("astroIndex")), getArguments().getString("type"));
    }


    private void _updateUI(ArrayList<String> data, Bundle bundle){
        //summary
        TextView tvSummary = (TextView)_view.findViewById(R.id.summary);
        RatingBar rb = (RatingBar)_view.findViewById(R.id.ratingbarSummary);
        String strVal = Utils.processMonthData(data,tvSummary.getText().toString(),false);
        rb.setRating(Integer.parseInt(strVal));
        TextView tvSummaryVal = (TextView)_view.findViewById(R.id.summary_val);
        strVal = Utils.processMonthData(data,tvSummary.getText().toString(),true);
        tvSummaryVal.setText(strVal);

        //love
        TextView tvLove = (TextView)_view.findViewById(R.id.love);
        rb = (RatingBar)_view.findViewById(R.id.ratingbarLove);
        strVal = Utils.processMonthData(data,tvLove.getText().toString(),false);
        rb.setRating(Integer.parseInt(strVal));
        TextView tvLoveVal = (TextView)_view.findViewById(R.id.love_val);
        strVal = Utils.processMonthData(data,tvLove.getText().toString(),true);
        tvLoveVal.setText(strVal);

        //money
        TextView tvMoney = (TextView)_view.findViewById(R.id.Money);
        rb = (RatingBar)_view.findViewById(R.id.ratingbarMoney);
        strVal = Utils.processMonthData(data,tvMoney.getText().toString(),false);
        rb.setRating(Integer.parseInt(strVal));
        TextView tvMoneyVal = (TextView)_view.findViewById(R.id.Money_val);
        strVal = Utils.processMonthData(data,tvMoney.getText().toString(),true);
        tvMoneyVal.setText(strVal);

        //release way
        TextView tvRelease = (TextView)_view.findViewById(R.id.release);
        TextView tvReleaseVal = (TextView)_view.findViewById(R.id.releaseWay_val);
        strVal = Utils.processMonthData(data,tvRelease.getText().toString(),true);
        tvReleaseVal.setText(strVal);

        //tips
        TextView tvTips = (TextView)_view.findViewById(R.id.luckyTips);
        TextView tvTipsVal = (TextView)_view.findViewById(R.id.luckyTips_val);
        strVal = Utils.processMonthData(data,tvTips.getText().toString(),true);
        tvTipsVal.setText(strVal);

        //lucky thing
        TextView tvThing = (TextView)_view.findViewById(R.id.luckyThing);
        TextView tvThingVal = (TextView)_view.findViewById(R.id.luckyThing_val);
        strVal = Utils.processMonthData(data,tvThing.getText().toString(),true);
        tvThingVal.setText(strVal);

        Integer noInteger = getArguments().getInt("no");

        //update the title
        TextView titleTextView = (TextView)_view.findViewById(R.id.titleAstroMore);
        titleTextView.setText(getResources().getStringArray(R.array.getTypeAstro)[noInteger]);
        //titleTextView.setTextColor(Utils.randomColor(Color.BLACK));
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
