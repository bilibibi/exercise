/**
 * 归并排序
 * 时间复杂度 O(nlogn)
 */
public class Merge {
  public Merge(){

  }

  public static void mergeArray(int[] a,int first,int mid,int last){
    int i=first,j=mid+1;
    int[] temp = new int[last-first+1];
    int k=0;

    while(i<=mid && j<=last) {
      if (a[i]<=a[j]) {
        temp[k++]=a[i++];
      } else {
        temp[k++]=a[j++];
      }
    }

    if (i<=mid) {
      temp[k++]=a[i++];
    }

    if(j<=last) {
      temp[k++]=a[j++];
    }

    for (int alen=0; alen<temp.length; alen++) {
      a[first+alen] = temp[alen];
      System.out.print(temp[alen]+" ");
    }
    System.out.println();
  }

  public static void mergeSort(int[] a,int first,int last){
    if (first < last) {
      int mid = (first+last)/2;
      mergeSort(a,first,mid);
      mergeSort(a,mid+1,last);
      mergeArray(a,first,mid,last);
    }
  }

  public static void main(String[] args) {
    // int[] array =new int[9];
    // for(int i=0;i<array.length;i++){
    //   array[i] = (int)(Math.random()*9);
    //   System.out.print(array[i]+" ");
    // }
    // System.out.println();
    int[] array = new int[] {1,3,5,7,9,2,4,6,8};
    mergeSort(array,0,array.length-1);
    for (int i=0; i<array.length; i++) {
      System.out.print(array[i]+" ");
    }
  }
}
