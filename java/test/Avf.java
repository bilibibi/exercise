class Base{}

class Agg extends Base{
	public String getFields(){
		String name = "Agg";
		return name;
	}
}

public class Avf{
	public static void main(String argv[]){
		Base a = new Agg();
		System.out.println(((Agg)a).getFields());
	}
}