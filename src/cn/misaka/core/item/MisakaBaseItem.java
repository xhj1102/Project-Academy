package cn.misaka.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class MisakaBaseItem extends Item {
	
	private String name;

	public MisakaBaseItem(int aId, String aName) {
		super(aId);
		this.name = aName;
	}

	private boolean isBase() {
		return name.equalsIgnoreCase("base") ? true : false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if (!isBase())
			this.itemIcon = par1IconRegister.registerIcon("academy:" + name);
	}

}
