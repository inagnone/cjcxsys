package util;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonUtil {
	static private JsonConfig config = new JsonConfig();  
	static {
		config.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessor() {
			
			@Override
			public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
				// TODO Auto-generated method stub
				return process(arg1);
			}
			
			@Override
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return process(arg0);  
			}
			
			private Object process(Object value) {  
	            try {  
	                  
	                if (value instanceof java.sql.Timestamp) {  
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                    System.out.println("sdf.format( value):"+sdf.format( value));  
	                    return sdf.format( value);  
	                }  
	                return value == null ? "" : value.toString();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	                return "";  
	            }  

	        }  
		});
	}
	public static JsonConfig getConfig(){
		return config;
	}
    
}
