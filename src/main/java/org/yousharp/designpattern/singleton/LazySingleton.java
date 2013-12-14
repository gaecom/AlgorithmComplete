package org.designpattern.singleton;

/**
 * 优点：懒汉式单例，只有在需要时才创建实例
 * 缺点：无法适应多线程环境，因为如果instance为空，多个线程同时访问锁，只有一个
 * 线程会加锁，创建实例，释放锁后，另一个线程加锁，同样可以创建新的线程。
 * User: Daniel
 * Date: 13-12-5
 * Time: 上午8:37
 */
public class LazySingleton {
	// static volatile
	private volatile static LazySingleton instance = null;

	// private constructor
	private LazySingleton() {}

	// check and lock
	public static LazySingleton getInstance() {
		if (instance == null) {
			synchronized (LazySingleton.class) {
				instance = new LazySingleton();
			}
		}
		return instance;
	}
}
