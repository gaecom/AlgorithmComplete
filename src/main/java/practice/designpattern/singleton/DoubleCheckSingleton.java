package practice.designpattern.singleton;

/**
 * 双重判定：解决饿汉式单例的多线程缺陷，在线程加锁后再次判断实例是否已经被创建了。
 * 优点：这个一个正确的单例模式。
 * 注意：volatile关键字，在多线程时，保证线程的读写顺序，即一个线程的写操作完成后，另一个线程才能读。
 * User: Daniel
 * Date: 13-12-5
 * Time: 下午9:35
 */
public class DoubleCheckSingleton {
	// volatile keyword
	private volatile static DoubleCheckSingleton instance = null;

	// private constructor
	private DoubleCheckSingleton (){}

	// double-checking
	public static DoubleCheckSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckSingleton.class) {
				if (instance == null) {
					instance = new DoubleCheckSingleton();
				}
			}
		}
		return instance;
	}

}
