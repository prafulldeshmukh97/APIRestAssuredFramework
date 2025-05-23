package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties prop=new Properties();;
	
	static {
		
		//this called reflection //we can use fileinputstream aswell ,herewe dont need to pass fiole full path
		try (InputStream input=	ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties"))
		{
			if(input!=null)
			{
				prop.load(input);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public static String get(String key)
	{
		return prop.getProperty(key);
	}
	public static void set(String key,String value)
	{
		 prop.setProperty(key,value);
	}

}
