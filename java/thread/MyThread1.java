package com.franky.thread;

class MyThread1 extends Thread{
	private int ticket=10;
	
	public void run(){
		for(int i=0;i<20;i++){
			if(this.ticket>0){
				System.out.println("卖票：ticket"+this.ticket--);
			}
		}
	}
	
	public static void main(String[] args) {
		MyThread1 mt1 = new MyThread1();
		MyThread1 mt2 = new MyThread1();
		MyThread1 mt3 = new MyThread1();
		mt1.start();
		mt2.start();
		mt3.start();
	}
}