package practice.designpattern.singleton;

/**
 * 静态块是在类加载的时候执行，甚至比构造函数的调用还要早；
 * 缺陷：类似于饱汉式单例，无论是否需要，示例都会被创建。
 * User: Daniel
 * Date: 13-12-5
 * Time: 下午9:52
 */
public class StaticBlockSingleton {
	// static final
	private static final StaticBlockSingleton instance;

	// private constructor
	private StaticBlockSingleton() {
	}

	// static block
	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}

}
