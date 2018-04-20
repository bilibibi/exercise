package com.franky.proxy;

public class ProxyFactory {

	private BeforeAdvice beforeAdvice;
	private Object targetObject;
	private AfterAdvice afterAdvice;

	public ProxyFactory() {
	}

	public ProxyFactory(BeforeAdvice beforeAdvice, Object targetObject, AfterAdvice afterAdvice) {
		this.beforeAdvice = beforeAdvice;
		this.targetObject = targetObject;
		this.afterAdvice = afterAdvice;
	}

	private InvocationHandler handler = new InvocationHandler() {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (beforeAdvice != null) {
				beforeAdvice.before();
			}

			Object result = null;
			if (targetObject != null) {
				result = method.invoke(targetObject, args);
			}

			if (afterAdvice != null) {
				afterAdvice.after();
			}

			return result;
		}
	};

	public Object createProxy() {
		return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), targetObject.getClass().getInterfaces(), handler);
	}
}