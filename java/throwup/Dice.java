package com.franky.throwup;

class Dice implements ThrowUp{
	public int throwResult(){
		return (int)(Math.random()*6+1);
	}
}