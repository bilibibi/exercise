package com.franky.throwup;

class DiceFactory implements ThrowUpFactory {
	public Dice showResult(){
		return new Dice();
	}
}