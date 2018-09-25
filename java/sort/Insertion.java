/**
* 插入排序
* 平均时间复杂度 O(n²)
*/
public class Insertion {

  	private static int[] a = new int[]{5,7,8,9,6,2,4,3,1};

    public Insertion(){

    }

    private void insertionSort(int[] arr){
      int preIndex, current;
      for (int i = 1, len = arr.length; i < len; i++){
        preIndex = i - 1;
        current = arr[i];
        while (preIndex >= 0 && arr[preIndex] > current){
          arr[preIndex+1]=arr[preIndex];
          preIndex--;
        }
        arr[preIndex+1] = current;
      }
    }

    public static void main(String[] args) {
      Insertion insertion = new Insertion();
      insertion.insertionSort(a);

      System.out.println("result:");
  		for(int s : a){
  			System.out.print(s+" ");
  		}
    }
}
