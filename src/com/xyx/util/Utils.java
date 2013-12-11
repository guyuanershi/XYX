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
    
	public static String processRawData(ArrayList<String> data)
	{
		String sb = "";
		for (String s : data){
			s = s.replaceAll("\\{|\\}", "");
			String[] ss = s.split("title=");
			if(ss.length == 2)
			{
				String s2 = ss[0].replaceAll("value=", "      ").replaceAll(", rank=0,|, rank=", "");
				sb = ss[1] + s2 +  "\n" + sb;
			}
			else
			{
				sb= ss[0] + "\n" + sb;
			}
		}
		return sb;
	}
    
    
    private static Boolean isConnected;

	public static Boolean getIsConnected() {
		return isConnected;
	}

	public static void setIsConnected(Boolean isConnected) {
		Utils.isConnected = isConnected;
	}
}
