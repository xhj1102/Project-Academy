package cn.misaka.core.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends Proxy {
	public void load() {
		super.load();
	}

	public void loadLocalization() {
		super.loadLocalization();
	}

}
