package cn.misaka.core.proxy;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.misaka.core.AcademyMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Proxy {
	
	public static final String LOCALIZATIONPATH = "/assets/misaka/lang";
	public static Set<String> languages = new HashSet();
	
	static {
		languages.add("zh_CN");
	}
	
	public void init() {
		for (String lang : languages) {
			LanguageRegistry.instance().loadLocalization(
					LOCALIZATIONPATH + lang + ".properties", lang, false);
		}
		
	}

	public static boolean isRendering() {
		return !isSimulating();
	}

	private static boolean isSimulating() {
		return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}
}
