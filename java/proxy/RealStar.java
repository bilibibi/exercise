package com.franky.proxy;

import com.franky.Star;

public class RealStar implements Star {
	/**
	 * 唱歌是要自己真唱的
	 */
	@Override
	public void singSong() {
		System.out.println("明星在唱歌");
	}
}