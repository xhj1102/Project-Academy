package cn.misaka.core.proxy;

import java.io.File;

import cn.misaka.core.AcademyMod;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Proxy {
	public static final String LOCALIZATIONPATH = "/assets/academy/lang";
	public void load() {
	}

	public static boolean isRendering() {
		return !isSimulating();
	}

	private static boolean isSimulating() {
		return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public void loadLocalization() {
		File LocalizationDirectory = new File(getClass().getResource(
				LOCALIZATIONPATH).getFile());
		File[] files = LocalizationDirectory.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				File file = files[i];
				String fileName = file.getName();
				if (!fileName.endsWith(".lang") || fileName.length() != 10) {
					continue;
				}
				LanguageRegistry.instance().loadLocalization(
						LOCALIZATIONPATH + fileName,
						fileName.substring(0, 5), false);
				AcademyMod.log.info("成功加载语言:" + fileName.substring(0, 5));
			}
		}
	}
}
