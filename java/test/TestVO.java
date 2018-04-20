class TestVO {
	private int x;
	private int y;
	private String s;
	
	public TestVO(){
		
	}
	
	public TestVO(int x,int y,String s){
		this.x=x;
		this.y=y;
		this.s=s;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getY(){
		return y;
	}
	public void setS(String s){
		this.s=s;
	}
	public String getS(){
		return s;
	}
}