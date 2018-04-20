import java.util.*;
import java.io.*;

class ReflectTest2 {
	public static void main(String[] args) throws Exception{
		InputStream is = new FileInputStream("config.properties");
//		InputStream is = ReflectTest2.class.getClassLoader().getResourceAsStream("config.properties");
//		InputStream is = ReflectTest2.class.getResourceAsStream("config.properties");
		Properties props = new Properties();
		props.load(is);
		is.close();
		String className = props.getProperty("className");
		Collection<String> coll = (Collection<String>)Class.forName(className).newInstance();
		
//		Collection<String> coll = new HashSet<String>();
		coll.add("fang");
		coll.add("yu");
		coll.add("jia");
		coll.add("fang");
		
		System.out.println(coll.size());
	}
}