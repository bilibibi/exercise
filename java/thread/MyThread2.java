package com.franky.thread;

class MyThread2 implements Runnable {
	private int ticket = 10;
	
	public void run(){
		for(int i=0;i<20;i++){
			if(this.ticket>0){
				System.out.println("卖票：ticket"+this.ticket--);
			}
		}
	}
	
	public static void main(String[] args) {
		MyThread2 mt = new MyThread2();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
	}
}