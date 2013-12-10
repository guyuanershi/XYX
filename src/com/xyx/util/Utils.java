package com.xyx.util;

import java.util.ArrayList;

public class Utils {
    public final static String EXTRA_ASTRO_DATA = "com.xyx.extra.astro.MESSAGE";
    public final static String EXTRA_GET_TYPE_OF_ASTRO = "com.xyx.extra.astro.GETTYPEOFASTRO";
    
    public static String[] trans(ArrayList<String> arrayList){
    	if (arrayList.size() == 0) return new String[]{};
    	
    	String[] strings = new String[arrayList.size()];
    	arrayList.toArray(strings);
    	return strings;
    }
    
    
    
    private static Boolean isConnected;

	public static Boolean getIsConnected() {
		return isConnected;
	}

	public static void setIsConnected(Boolean isConnected) {
		Utils.isConnected = isConnected;
	}
}
