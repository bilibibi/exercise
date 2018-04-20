import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class TestSingleton {
	
	public static void main(String[] args) throws InterruptedException {
		final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
		final CountDownLatch cdl = new CountDownLatch(1);
		 
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						cdl.await();
						 
						Singleton singleton = Singleton.getInstance();
						set.add(singleton.toString());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		 
		 
		Thread.sleep(1000);
		cdl.countDown();
		Thread.sleep(1000);
		 
		System.out.println("一共有" + set.size() + "个实例");
		for (String str : set) {
			System.out.println(str);
		}
		 
		executor.shutdown();
	}
}