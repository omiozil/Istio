package com.orchestOrder.orderOrchestration.common;

import org.json.JSONObject;

public class DataUtils {
	
	public static String mapToJson(Object data) 
	{
		try
		{
			return new JSONObject(data).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
		
	}

}
