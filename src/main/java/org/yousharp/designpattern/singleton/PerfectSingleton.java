package org.yousharp.designpattern.singleton;

import java.io.Serializable;

/**
 * 增加序列化的支持，因为反序列化时，总是会产生新的实例。
 * readResolve函数表示，当反序列化时，该函数返回的对象（当前可用的对象）作为流的返回值，即不会创建新的
 * 实例；
 * 增加serialVersionUID，因为序列化和反序列化时，serialVersionUID不一致，类的结构的变化会导致JVM抛异常。
 * User: Daniel
 * Date: 13-12-5
 * Time: 下午10:23
 */
public class PerfectSingleton implements Serializable {
	// serial version id
	private static final long serialVersionUID = 1L;

	// private constructor
	private PerfectSingleton() {}

	// readResolve
	protected Object readResolve() {
		return getInstance();
	}

	// inner class
	private static class PerfectHolder {
		private static final PerfectSingleton instance = new PerfectSingleton();
	}

	public static PerfectSingleton getInstance() {
		return PerfectHolder.instance;
	}

}

/**
  * 总之，我们可以发现，一共6中单例模式，可以分为三类：饿汉式、懒汉式和内部类；
 * 饿汉式：EagerSingleton(使用构造函数)和StaticBlockSingleton（使用静态初始化块）
 * 懒汉式：LazySingleton（不能用于多线程）和DoubleCheckSingleton（可以用于多线程）
 * 内部类：InnerClassSingleton（不支持序列化）和PerfectSingleton支持序列化
  */