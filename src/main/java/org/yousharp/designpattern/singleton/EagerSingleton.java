package org.designpattern.singleton;

/**
 * 优点：饥饿式单例，可以适应多线程环境
 * 缺点：类的实例在加载时即创建，无论运行时是否需要；如果该类的实例不是很大，创建了不使用也没关系，
 * 那么这是一种很好的实现单例模式的方法。
 * User: Daniel
 * Date: 13-12-4
 * Time: 下午11:00
 */
public class EagerSingleton {
	// volatile reads and writes establish a happen-before relationship
	private volatile static EagerSingleton instance = new EagerSingleton();

	// private constructor
	private EagerSingleton() {}

	// return the instance
	public static EagerSingleton getInstance() {
		return instance;
	}
}
