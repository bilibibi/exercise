import java.lang.reflect.Field;

class ReflectTest {
	public static void main(String[] args) throws Exception{
		ReflectPoint rp = new ReflectPoint(3, 5);
		changeValue(rp);
		System.out.println(rp);
	}
	
	private static void changeValue(Object obj) throws Exception{
		for(Field field : obj.getClass().getDeclaredFields()){
			System.out.println(field);
			if(field.getType()==String.class){
				String oldString = (String)field.get(obj);
				String newString = oldString.replace('b', 'c');
				field.setAccessible(true);
				field.set(obj, newString);
			}
		}
	}
}

class ReflectPoint {
	private int x;
	private int y;
	public String s1 = "ball";
	public String s2 = "baby";
	public String s3 = "fangyj";
	
	public ReflectPoint(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	
	public String toString(){
		return s1+","+s2+","+s3;
	}
}