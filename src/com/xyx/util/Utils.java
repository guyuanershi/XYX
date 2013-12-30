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
    
	public static String processRawData(ArrayList<String> rawData, String dataType)
	{
		String sb = "";
		
		for (String s : rawData){
			s = s.replaceAll("\\{|\\}", "");
			String[] ss = s.split("title=");
			if(ss.length == 2)
			{
				String s2 = ss[0].replaceAll("value=", "").replaceAll(", rank=0,|, rank=", "");
				if(ss[1].equals(dataType))
				{
					sb = s2.trim();
					if(sb.endsWith(","))
						sb = sb.substring(0, sb.length()-1);
					break;
				}
			}
			else
			{
				if(dataType.equals("") && s.startsWith("cn="))
				{
					int start = s.indexOf("=");
					int end = s.indexOf(",");
					
					sb = s.substring(start+1, end-1);
				}
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
	
	public static int getResourceByName(Class classtype, String resName){
		int id = 0;
		if (classtype == null) return id;
		try {
			id = classtype.getDeclaredField(resName.toLowerCase()).getInt(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
