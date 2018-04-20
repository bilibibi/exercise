package com.franky.proxy;

import com.franky.proxy.ProxyFactory;
import com.franky.proxy.Star;

public class Client {

	@Test
	public void client() {
		Star star = (Star) new ProxyFactory(new StarBeforeAdvice(), new RealStar(), new StarAfterAdvice()).createProxy();
		star.singSong();
	}

	/**
	 * BeforeAdvice实现可定制化
	 */
	private static class StarBeforeAdvice implements BeforeAdvice {

		@Override
		public void before() {
			System.out.println("签合约");
		}
	}

	/**
	 * AfterAdvice实现可定制化
	 */
	private static class StarAfterAdvice implements AfterAdvice {

		@Override
		public void after() {
			System.out.println("收款");
		}
	}
}