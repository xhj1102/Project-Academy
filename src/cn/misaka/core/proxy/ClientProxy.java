package cn.misaka.core.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends Proxy {
	
	@Override
	public void load() {
		super.load();
	}

	@Override
	public void loadLocalization() {
		super.loadLocalization();
	}

}
