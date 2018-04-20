package com.franky.throwup;

class Coin implements ThrowUp{
	public int throwResult(){
		return (int)(Math.random()*2+1);
	}
}