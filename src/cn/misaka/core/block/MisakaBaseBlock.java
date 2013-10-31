package cn.misaka.core.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class MisakaBaseBlock extends Block {

	private String name;

	public MisakaBaseBlock(int par1, Material par2Material, String aName) {
		super(par1, par2Material);
		this.name = aName;
	}

	private boolean isBase() {
		return name.equalsIgnoreCase("base") ? true : false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if (!isBase())
			this.blockIcon = par1IconRegister.registerIcon("academy:" + name);

	}

}
