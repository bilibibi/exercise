package sort;

/**
* 冒泡排序
* 时间复杂度 O(n²)
*/
public class Bubble {
	public Bubble(){

	}

	private void bubbleSort(int[] a){
		for(int i=1;i<=a.length-1;i++){
			for(int j=1;j<=a.length-i;j++){
				if(a[j-1]>a[j]){
					int temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
			for(int j : a){
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		int[] a = new int[]{11,27,16,22,31,9};
		Bubble bubble = new Bubble();
		bubble.bubbleSort(a);
	}
}
