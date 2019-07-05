package sort;

public class Stack {
	public Stack(){
		
	}
	
	private boolean stackSort(int[] a){
		if(a.length==0){
			return false;
		}
		
		int len=a.length;
		int mid=len/2;
		
		int[] s=new int[mid];
		for(int i=0;i<mid;i++){
			s[i]=a[i];
		}
		
		int top=mid,next;
		if(len%2==0){
			next=mid;
		}else{
			next=mid+1;
		}
		
		for (int i=next;i<len;i++) {
			top--;
			if(s[top]!=a[i]){
				break;
			}
		}
		
		if(top==0){
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		//判断是否为回文字符串
		int[] a1=new int[]{1,2,3,3,2,1};
		int[] a2=new int[]{1,2,3,4,5,6};
		int[] a3=new int[]{1,2,3,4,3,2,1};
		
		Stack stack = new Stack();
		System.out.println("a1 is "+stack.stackSort(a1));
		System.out.println("a2 is "+stack.stackSort(a2));
		System.out.println("a3 is "+stack.stackSort(a3));
	}
}