package com.value.auto.provider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.time.DateFormatUtils;

import com.value.auto.metadata.Table;

public class GlobalPropertiesProvider {

	static Properties props;
	public static List<Table> database = null;
	private GlobalPropertiesProvider(){}
	
	private static void initProperties() {

		try {
			props = loadAllProperties(".\\config\\global.properties");
			String basepackage = props.getProperty("package.base");
			props.put("projectName", props.getProperty("package.project"));
			props.put("architecturePath", props.getProperty("source.architecturePath"));
			props.put("modulePath", props.getProperty("source.modulePath"));
			props.put("basePackage", basepackage);
			props.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			String basepackage_dir = basepackage.replace('.', '/');
			props.put("basepackage_dir", basepackage_dir);
			
			for(Iterator it = props.entrySet().iterator();it.hasNext();) {
				Map.Entry entry = (Map.Entry)it.next();
				System.out.println("[Property] "+entry.getKey()+"="+entry.getValue());
			}
			
			System.out.println();
			
		}catch(IOException e) {
			throw new RuntimeException("Load Properties error",e);
		}
	}
	
	public static Properties getProperties() {
		if(props == null)
			initProperties();
		return props;
	}
	
	public static String getProperty(String key, String defaultValue) {
		return getProperties().getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return getProperties().getProperty(key);
	}

	public static Properties loadAllProperties(String resourceName) throws IOException {
		Properties properties = new Properties();
		Enumeration urls = GlobalPropertiesProvider.class.getClassLoader().getResources(resourceName);
		
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			InputStream is = null;
			try {
				URLConnection con = url.openConnection();
				con.setUseCaches(false);
				is = con.getInputStream();
				properties.load(is);
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return properties;
	}
}
