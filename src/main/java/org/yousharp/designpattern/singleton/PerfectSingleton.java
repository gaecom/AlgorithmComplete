package org.designpattern.singleton;

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
	// searial version id
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
