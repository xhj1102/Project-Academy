package cn.liutils.api;

import cn.liutils.core.register.Config;
import cn.liutils.core.register.ConfigHandler;

/**
 * 各种Registry类的统一接口，方便各方面注册。
 * @author WeAthFolD
 *
 */
public class LIGeneralRegistry {

	/**
	 * 加载一个含有可设置参数的类。
	 * 
	 * @param conf
	 *            公用设置
	 * @param cl
	 *            类，要注册的参数必须为Static
	 */
	public static void loadConfigurableClass(Config conf, Class<?> cl) {
		ConfigHandler.loadConfigurableClass(conf, cl);
	}
	
	public static int getItemId(String name, int cat) {
		return ConfigHandler.getItemId(name, cat);
	}
	
	public static int getBlockId(String name, int cat) {
		return ConfigHandler.getBlockId(name, cat);
	}
	
	public static int getFixedBlockId(String name, int def) {
		return ConfigHandler.getFixedBlockId(name, def);
	}
	
	public static int getFixedBlockId(String name, int def, int max) {
		return ConfigHandler.getFixedBlockId(name, def, max);
	}

}
