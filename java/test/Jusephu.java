package com.franky.test;

/**
* 使用数组实现约瑟夫环问题
* 由n个人围成一个首尾相连的圈报数。
* 从第k个人开始，从1开始报数，报到m的人出圈，
* 剩下的人继续从1开始报数，直到所有的人都出圈为止。
* 对于给定的m、n和k，求出所有人的出圈顺序.
*/

public class Jusephu {
	public static void main(String[] args) {
		CycLink link = new CycLink();
		link.setN(55);
		link.createLink();
		link.show();
		link.setK(5);
		link.setM(8);
		link.play();
	}
}

class Child{
	int no = 0;
	Child nextChild = null;
	public Child(int no){
		this.no = no;
	}
}
	
//环形列表
class CycLink{
	public int n = 0,m = 0,k = 0;
	public Child firstChild = null;
	public Child temp = null;
		
	public void setN(int n){
		this.n = n;
	}
	
	public void setM(int m){
		this.m = m;
	}
	
	public void setK(int k){
		this.k = k;
	}
	
	public void play(){
		Child temp = firstChild;
		//遍历到开始数数的人
		for(int i=1;i<k;i++){
			temp = temp.nextChild;
		}
		
		while(n!=0){
			//开始数数
			for(int i=1;i<m;i++) {
				temp = temp.nextChild;
			}
			//找到出局人的上一个人
			Child temp2=temp;
			while (temp2.nextChild!=temp) {
				temp2=temp2.nextChild;
			}
			//移出出局的人
			temp2.nextChild = temp.nextChild;
			System.out.println(temp.no+" out");
			temp = temp.nextChild;
			this.n--;
		}
	}
	
	public void createLink(){
		for(int i=1;i<=n;i++){
			Child ch = new Child(i);
			if(i==1){
				this.firstChild = ch;
				temp = ch;
			}else if(i<n){
				temp.nextChild = ch;
				temp = ch;
			}else{
				temp.nextChild = ch;
				temp = ch;
				temp.nextChild = this.firstChild;
			}
		}
	}
		
	public void show(){
		Child temp = this.firstChild;
		do{
			System.out.print(temp.no + " ");
			temp = temp.nextChild;
		} while (temp!=this.firstChild);
		System.out.println("");
	}
}