/**
* 选择排序
* 平均时间复杂度 O(n²)
*/
public class Selection {

	private static int[] a = new int[]{5,7,8,9,6,2,4,3,1};

  public Selection(){

  }

  private void selectionSort(int[] arr){
    int len = arr.length;
    int minIndex,temp;
    for(int i=0;i<len-1;i++){
      minIndex = i;
      for (int j=i+1;j<len;j++){
        if (arr[j]<arr[minIndex]){
          minIndex = j;
        }
      }
      if (minIndex != i){
        temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
      }
    }
  }

  public static void main(String[] args) {
    Selection selection = new Selection();
    selection.selectionSort(a);

    System.out.println("result:");
		for(int s : a){
			System.out.print(s+" ");
		}
  }
}
