package com.xyx.util;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;

public class Utils {
    public final static String EXTRA_ASTRO_DATA = "com.xyx.extra.astro.MESSAGE";
    public final static String EXTRA_GET_TYPE_OF_ASTRO = "com.xyx.extra.astro.GETTYPEOFASTRO";

    public final static String PREFERENCE_TODAY = "com.xyx.preferences.PREFERENCE_TODAY";
    public final static String PREFERENCE_TODAY_TIME = "com.xyx.preferences.PREFERENCE_TODAY_TIME";
    public final static String PREFERENCE_TOMMOROW = "com.xyx.preferences.PREFERENCE_TOMMOROW";
    public final static String PREFERENCE_TOMMOROW_TIME = "com.xyx.preferences.PREFERENCE_TOMMOROW_TIME";
    public final static String PREFERENCE_WEEK = "com.xyx.preferences.PREFERENCE_WEEK";
    public final static String PREFERENCE_WEEK_TIME = "com.xyx.preferences.PREFERENCE_WEEK_TIME";
    public final static String PREFERENCE_MONTH = "com.xyx.preferences.PREFERENCE_MONTH";
    public final static String PREFERENCE_MONTH_TIME = "com.xyx.preferences.PREFERENCE_MONTH_TIME";
    public final static String PREFERENCE_YEAR = "com.xyx.preferences.PREFERENCE_YEAR";
    public final static String PREFERENCE_YEAR_TIME = "com.xyx.preferences.PREFERENCE_YEAR_TIME";

    public static int CURRENT_TIME = -1;
    
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
	public static String processWeekData(ArrayList<String> rawData, String dataType, boolean isValue)
	{
		String strVal = "";		
		for (String s : rawData)
		{
			if(s.contains(dataType))
			{
				s = s.replaceAll("\\{|\\}", "");
				
				if(isValue)
				{
					String[] ss = s.split(",");
					for(String s2 : ss)
					{
						if(s2.contains("value="))
						{
							String[] sss = s2.split("=");
							strVal = sss[1].trim();
							break;
						}
						
					}
					
					if(strVal!="")
						break;
				}
				else 
				{
					String[] ss = s.split(",");
					for(String s2 : ss)
					{
						if(s2.contains("rank="))
						{
							String[] sss = s2.split("=");
							strVal = sss[1];
							break;
						}
						
					}
					
					if(strVal!="")
						break;
				}
			}
		}
		
		return strVal;
	}
	//rank1  a[0]
	//rank2  a[1]
	//value1 a[3]
	//value2 a[4]
	public static ArrayList<String> processWeekLoveData(ArrayList<String> rawData, String dataType)
	{
		ArrayList<String> StrArray = new ArrayList<String>() ;		
		for (String s : rawData)
		{
			if(s.contains(dataType))
			{
				s = s.replaceAll("\\{|\\}", "");
				String str1 = "rank=[";
				int rank_start = s.indexOf(str1);
				int rank_end = s.indexOf("], title");
				String rankString =  s.substring(rank_start+str1.length(), rank_end);
				String[] StrArray1 = rankString.split(",");
				
				String str2 = "value2=[";
				int value_start = s.indexOf(str2);
				int value_end = s.indexOf("], title2");
				String valueString = s.substring(value_start+str2.length(), value_end-1);
				
				String[] StrArray2 = valueString.split(",");
				
				if(StrArray1.length == 2 && StrArray2.length == 2)
				{
					StrArray.add(StrArray1[0].trim());
					StrArray.add(StrArray1[1].trim());
					StrArray.add(StrArray2[0].trim());
					StrArray.add(StrArray2[1].trim());
				}
				break;
			}
		}
		
		return StrArray;
	}

    public static String processMonthData(ArrayList<String> rawData, String dataType, boolean isValue)
    {
        String strVal = "";
        for (String s : rawData)
        {
            if(s.contains(dataType))
            {
                s = s.replaceAll("\\{|\\}", "");

                if(isValue)
                {
                    String[] ss = s.split(",");
                    for(String s2 : ss)
                    {
                        if(s2.contains("value="))
                        {
                            String[] sss = s2.split("=");
                            strVal = sss[1].trim();
                            break;
                        }

                    }

                    if(strVal!="")
                        break;
                }
                else
                {
                    String[] ss = s.split(",");
                    for(String s2 : ss)
                    {
                        if(s2.contains("rank="))
                        {
                            String[] sss = s2.split("=");
                            strVal = sss[1];
                            break;
                        }

                    }

                    if(strVal!="")
                        break;
                }
            }
        }

        return strVal;

    }

    public static String processYearAstroData(ArrayList<String> rawData, String dataType, boolean isValue)
    {
        String strVal = "";
        for (String s : rawData)
        {
            if(s.contains(dataType))
            {
                s = s.replaceAll("\\{|\\}", "");

                if(isValue)
                {
                    String[] ss = s.split(",");
                    for(String s2 : ss)
                    {
                        if(s2.contains("value="))
                        {
                            String[] sss = s2.split("=");
                            strVal = sss[1].trim();
                            break;
                        }

                    }

                    if(strVal!="")
                        break;
                }
                else
                {
                    String[] ss = s.split(",");
                    for(String s2 : ss)
                    {
                        if(s2.contains("rank="))
                        {
                            String[] sss = s2.split("=");
                            strVal = sss[1];
                            break;
                        }

                    }

                    if(strVal!="")
                        break;
                }
            }
        }

        return strVal;

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

    public static int randomColor(int notcolor){
        Random rad = new Random();
        return Color.rgb(rad.nextInt(230), rad.nextInt(230), rad.nextInt(230));
    }
}
