package com.xyx.util;

import java.util.ArrayList;

public class Utils {
    public final static String EXTRA_ASTRO_DATA = "com.xyx.extra.astro.MESSAGE";
    
    public static String[] trans(ArrayList<String> arrayList){
    	if (arrayList.size() == 0) return new String[]{};
    	
    	String[] strings = new String[arrayList.size()];
    	arrayList.toArray(strings);
    	return strings;
    }
}
