package com.xyx.server.hprose;

import java.io.IOException;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import hprose.client.HproseHttpClient;

public class DataFromMyAPI extends AsyncTask<String, Void, ArrayList<String>>{
	
	@Override
	protected ArrayList<String> doInBackground(String... arg0) {
		ArrayList<String> data = null;
		IData idata = null;
		if (_client == null){
			//_client = new HproseHttpClient();
			//_client.useService("http://api.uihoo.com/astro/astro.hprose.php");
			
			_client = new HproseHttpClient("http://api.uihoo.com/astro/astro.hprose.php");
			idata = (IData)_client.useService(IData.class);
		}
		if (_client != null){
			try {
				data = idata.MYAPI_astro_day(Integer.valueOf(arg0[0]), "UTF-8");
			} catch (Exception e) {
				Log.v("wifi is really connected?", "nothing");
			}
			//data = (ArrayList<String>)_client.invoke(arg0[0], new Object[] {8, "UTF-8"});
			
		}
		
		return data;
	}
	
	

	//private static final DataFromMyAPI Default = new DataFromMyAPI();
	
	@Override
	protected void onPostExecute(ArrayList<String> result) {
		
		cancel(true);
		
		super.onPostExecute(result);
		
	}



	private HproseHttpClient _client;
	
		
	interface IData {
		ArrayList<String> MYAPI_astro_day(int id, String chartSet);
	}
}
