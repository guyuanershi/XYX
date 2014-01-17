package com.xyx.activity;

import java.util.ArrayList;

import com.xyx.R;
import com.xyx.interfaces.IDataFromMyAPIEvent;
import com.xyx.server.hprose.DataFromMyAPI;
import com.xyx.util.Utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class AstroDetailsMonthFragment extends Fragment {

    View _view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);
        _view = inflater.inflate(R.layout.activity_astro_details_month, null);

        //TODO: initialize the view
        DataFromMyAPI api = new DataFromMyAPI(new IDataFromMyAPIEvent()
        {
            @Override
            public void updateUI(ArrayList<String> data) {

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
                titleTextView.setTextColor(Utils.randomColor(Color.BLACK));
            }
        });

        api.execute(String.valueOf(getArguments().getInt("astroIndex")), getArguments().getString("type"));
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
