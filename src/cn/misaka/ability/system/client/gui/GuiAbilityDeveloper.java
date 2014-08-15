/**
 * Copyright (C) Lambda-Innovation, 2013-2014
 * This code is open-source. Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 */
package cn.misaka.ability.system.client.gui;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.liutils.api.register.IGuiElement;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class GuiAbilityDeveloper extends GuiScreen {
	
	public static class Element implements IGuiElement {

		@Override
		public Object getServerContainer(EntityPlayer player, World world,
				int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGui(EntityPlayer player, World world, int x,
				int y, int z) {
			return new GuiAbilityDeveloper((TileAbilityDeveloper) world.getTileEntity(x, y, z));
		}
		
	}
	
	TileAbilityDeveloper myTile;

	/**
	 * 
	 */
	public GuiAbilityDeveloper(TileAbilityDeveloper tile) {
		myTile = tile;
	}
	
	@Override
	public void onGuiClosed() {
		System.out.println("onGuiClosed");
//		myTile.disMount();
		//AcademyCraft.netHandler.sendToServer(new MsgDeveloperDismount()); //自动下来~ 
	}
	
    @Override
	public void drawScreen(int par1, int par2, float par3)
    {
    	this.drawDefaultBackground();
    	HudUtils.setTextureResolution(512, 512);
    	HudUtils.setZLevel(zLevel);
    	RenderUtils.loadTexture(APClientProps.TEX_GUI_ABILITY_DEVELOPER);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	HudUtils.drawTexturedModalRect(this.width / 2 - 114, this.height / 2 - 93, 1, 1, 228, 180, 456, 369);
    }

}
