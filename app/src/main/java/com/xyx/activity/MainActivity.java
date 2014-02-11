package com.xyx.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.xyx.R;
import com.xyx.enums.GetTypeOfAstro;
import com.xyx.receiver.ConnectionReceiver;
import com.xyx.util.SystemUiHider;
import com.xyx.util.Utils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;
    
    private ConnectionReceiver _connectionReceiver;
    
    private GetTypeOfAstro _getGetTypeOfAstro = GetTypeOfAstro.TODAY;

    private int countKeyBack = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register connection receiver
        //
        _connectionReceiver = new ConnectionReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(_connectionReceiver, filter);

        //inintial current time
        Utils.CURRENT_TIME = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        setContentView(R.layout.activity_main);

        //final View controlsView = findViewById(R.id.fullscreen_content_controls);
        //final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
//        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
//        mSystemUiHider.setup();
//        mSystemUiHider
//                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
//                    // Cached values.
//                    int mControlsHeight;
//                    int mShortAnimTime;
//
//                    @Override
//                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//                    public void onVisibilityChange(boolean visible) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//                            // If the ViewPropertyAnimator API is available
//                            // (Honeycomb MR2 and later), use it to animate the
//                            // in-layout UI controls at the bottom of the
//                            // screen.
//                            if (mControlsHeight == 0) {
//                                mControlsHeight = controlsView.getHeight();
//                            }
//                            if (mShortAnimTime == 0) {
//                                mShortAnimTime = getResources().getInteger(
//                                        android.R.integer.config_shortAnimTime);
//                            }
//                            controlsView.animate()
//                                    .translationY(visible ? 0 : mControlsHeight)
//                                    .setDuration(mShortAnimTime);
//                        } else {
//                            // If the ViewPropertyAnimator APIs aren't
//                            // available, simply show or hide the in-layout UI
//                            // controls.
//                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
//                        }
//
//                        if (visible && AUTO_HIDE) {
//                            // Schedule a hide().
//                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
//                        }
//                    }
//                });



//        // Set up the user interaction to manually show or hide the system UI.
//        contentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TOGGLE_ON_CLICK) {
//                    mSystemUiHider.toggle();
//                } else {
//                    mSystemUiHider.show();
//                }
//            }
//        });

        final GridView contentView = (GridView)findViewById(R.id.astro_main_content);

        Class<com.xyx.R.drawable> cls = R.drawable.class;
        ArrayList<HashMap<String, Object>> ltImgItems = new ArrayList<HashMap<String,Object>>();
        String[] astros = getResources().getStringArray(R.array.astros);
        String[] astroEns = getResources().getStringArray(R.array.astros_en);

        for (int index = 0; index < astros.length; index++){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("ItemText", astros[index]);
            try {
				int value = cls.getDeclaredField(astroEns[index].toLowerCase()).getInt(null);
				item.put("ItemImage", value);
                item.put("ItemTextEn", astroEns[index]);
			} catch (Exception e) {
				e.printStackTrace();
			}
            ltImgItems.add(item);
        }

        SimpleAdapter sadp = new SimpleAdapter(this,
        		ltImgItems,
        		R.layout.astro_gridviewitem,
        		new String[]{"ItemImage", "ItemText", "ItemTextEn"},
        		new int[] {R.id.itemImage, R.id.itemText, R.id.itemTextEn});

        contentView.setAdapter(sadp);

        contentView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                    View arg1,//The view within the AdapterView that was clicked
                    int arg2,//The position of the view in the adapter
                    long arg3//The row id of the item that was clicked
                    ) {
        				Context context = arg0.getContext();
        				HashMap hashMap = ((HashMap)arg0.getAdapter().getItem(arg2));

        				if (Utils.getIsConnected()){
        					Intent intent = new Intent(context, AstroDetailsViewPager.class);
            				intent.putExtra(Utils.EXTRA_ASTRO_DATA, arg2);
            				intent.putExtra(Utils.EXTRA_GET_TYPE_OF_ASTRO, hashMap.get("ItemTextEn").toString());
            				startActivity(intent);
	        			} else {
	        				Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
	        			}
        			  }
		});

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
//    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (AUTO_HIDE) {
//                delayedHide(AUTO_HIDE_DELAY_MILLIS);
//            }
//            return false;
//        }
//    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            //mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

	@Override
	protected void onDestroy() {
		if (_connectionReceiver != null) {
			unregisterReceiver(_connectionReceiver);
		}
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			countKeyBack += 1;
			if (countKeyBack < 2){
				final Toast toast = Toast.makeText(this.getBaseContext(), getResources().getString(R.string.quitMessage), Toast.LENGTH_SHORT);
				toast.show();
				
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						toast.cancel();
					}
				}, 1000);
				
			} else {
				finish();
			}
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		//if this activity is not active, then clear the count 
		// keyback number
		countKeyBack = 0;
	}
	
	
    
}
