package com.franky.throwup;

class ThrowCoinDice {
	public static void showResult(ThrowUpFactory factory){
		ThrowUp throwUp = factory.showResult();
		System.out.println(throwUp.throwResult());
	}
	
	public static void main(String[] args) {
		showResult(new CoinFactory());
		showResult(new DiceFactory());
	}
}