package cn.misaka.support.enchant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.support.client.render.APEnchantRender;

public abstract class APEnchantType {
	
	public float 
		damage,
		endure,
		repel;
	
	public int
		fire;
	
	@SideOnly(Side.CLIENT)
	public abstract APEnchantRender getEnchantRender();
}
