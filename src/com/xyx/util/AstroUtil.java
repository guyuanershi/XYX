package com.xyx.util;

import java.util.ArrayList;

import android.content.Context;

import com.xyx.R;
import com.xyx.R.string;

public final class AstroUtil {
	private static ArrayList<String> _astroNames = null;
	
	public static ArrayList<String> Astros(Context context) {
		if (context == null) throw new NullPointerException();
		String _astros = context.getString(R.string.astros);
		
		if (_astroNames == null){
			_astroNames = new ArrayList<String>();
			for (String astro_name : _astros.split(";")) {
				_astroNames.add(astro_name);
			}
		}
		
		return _astroNames;
	}
}
