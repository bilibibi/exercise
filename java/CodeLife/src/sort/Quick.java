package sort;

/**
* 快速排序
* 平均时间复杂度 O(nlogn)
*/
public class Quick {

	private static int[] a = new int[]{5,7,8,9,6,2,4,3,1,5,6,4,3,2,1,7,8,9};

	public Quick(){

	}

	private void quicksort(int left,int right){
		if(left>=right)
			return;
		System.out.println("left="+left+" right="+right);

		int temp=a[left],i=left,j=right,t;
		while (i!=j) {
			while (a[j]>=temp && i<j) {
				j--;
			}
			while (a[i]<=temp && i<j) {
				i++;
			}
			if(i<j){
				t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		//交换最左和指针最终停留位置的值
		if(left!=i){
			a[left]=a[i];
			a[i]=temp;
		}

		for(int s : a){
			System.out.print(s+" ");
		}
		System.out.println();

		this.quicksort(left, i-1);
		this.quicksort(i+1, right);
	}

	public static void main(String[] args) {
		Quick quick = new Quick();
		quick.quicksort(0, a.length-1);

		System.out.println("result:");
		for(int s : a){
			System.out.print(s+" ");
		}
	}
}
