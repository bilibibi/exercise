package com.franky.throwup;

class CoinFactory implements ThrowUpFactory {
	public Coin showResult(){
		return new Coin();
	}
}