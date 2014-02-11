package com.xyx.server.hprose;

import hprose.client.HproseHttpClient;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xyx.enums.GetTypeOfAstro;
import com.xyx.interfaces.IDataFromMyAPIEvent;

public class DataFromMyAPI extends AsyncTask<String, Void, ArrayList<String>>{
	
	public DataFromMyAPI(IDataFromMyAPIEvent event){
		_event = event;
	}
	
	@Override
	protected ArrayList<String> doInBackground(String... arg0) {
		ArrayList<String> data = null;
		IData idata = null;
		if (_client == null){			
			_client = new HproseHttpClient("http://api.uihoo.com/astro/astro.hprose.php");
			idata = (IData)_client.useService(IData.class);
		}
		if (_client != null){
			try {
				GetTypeOfAstro getTypeOfAstro = GetTypeOfAstro.valueOf(arg0[1]);
				switch (getTypeOfAstro) {
				case TODAY:
					data = idata.MYAPI_astro_day(Integer.valueOf(arg0[0]), "UTF-8");	
					break;
				case TOMORROW:
					data = idata.MYAPI_astro_tomorrow(Integer.valueOf(arg0[0]), "UTF-8");
					break;
				case WEEK:
					data = idata.MYAPI_astro_week(Integer.valueOf(arg0[0]), "UTF-8");
					break;
				case MONTH:
					data = idata.MYAPI_astro_month(Integer.valueOf(arg0[0]), "UTF-8");
					break;
				case YEAR:
					data = idata.MYAPI_astro_year(Integer.valueOf(arg0[0]), "UTF-8");
					break;
				default:
					break;
				}
				
			} catch (Exception e) {
				Log.v("wifi is really connected?", "nothing");
			}
		}
		
		return data;
	}
	
	

	//private static final DataFromMyAPI Default = new DataFromMyAPI();
	
	@Override
	protected void onPostExecute(ArrayList<String> result) {
		
		if (_event != null){
			_event.updateUI(result);
		}
		super.onPostExecute(result);
		
	}

	//
	private IDataFromMyAPIEvent _event;
	private HproseHttpClient _client;
		
	interface IData {
		ArrayList<String> MYAPI_astro_day(int id, String chartSet);
		ArrayList<String> MYAPI_astro_tomorrow(int id, String chartSet);
		ArrayList<String> MYAPI_astro_week(int id, String chartSet);
		ArrayList<String> MYAPI_astro_month(int id, String chartSet);
		ArrayList<String> MYAPI_astro_year(int id, String chartSet);
	}
}

