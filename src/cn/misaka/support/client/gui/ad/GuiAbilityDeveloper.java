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
package cn.misaka.support.client.gui.ad;

import java.lang.reflect.Field;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.LIGuiScreen;
import cn.liutils.api.client.gui.part.LIGuiButton;
import cn.liutils.api.client.gui.part.LIGuiPart;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.liutils.api.register.IGuiElement;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.block.tile.TileAbilityDeveloper;

/**
 * @author WeAthFolD
 *
 */
public class GuiAbilityDeveloper extends LIGuiScreen {
	
	public static final float PG_OFFSET_X = 3.5F, PG_OFFSET_Y = 37.5F,
			PG_WIDTH = 136.5F, PG_HEIGHT = 146.5F,
			TITLE_CENTER_X = 156.5F, TITLE_CENTER_Y = 12F;
	
	public static final int REC_FONT_COLOR = 0x3F7E93;
	
	public static final int MAX_PAGES = 2;
	
	private final LIGuiPage pageMain = new PageMain(this);
	private final LIGuiPage[] pages = {
		new PageLearning(this),
		new PageLearning(this),
		new PageSkillLearning(this)
	};
	
	public final TileAbilityDeveloper myTile;
	EntityPlayer player;
	boolean isLearned;
	int pageID;
	
	public GuiAbilityDeveloper(TileAbilityDeveloper tile, boolean learned) {
		super(228, 180);
		player = tile.mountPlayer;
		myTile = tile;
		isLearned = learned;
	}
	
	@Override
	public void onGuiClosed() {}
	
    @Override
	public void drawScreen(int par1, int par2, float par3)
    {
    	HudUtils.setTextureResolution(512, 512);
    	HudUtils.setZLevel(zLevel);
    	this.drawDefaultBackground();
    	
    	GL11.glDisable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glColor4f(1F, 1F, 1F, 1F);
    	this.drawElements(par1, par2);
    	
    	GL11.glEnable(GL11.GL_LIGHTING);
    }

	@Override
	public void updateActivatedPages(Set<LIGuiPage> set) {
		set.add(pageMain);
		set.add(getCurrentSubpage());
	}
	
	public LIGuiPage getCurrentSubpage() {
		return pageID == 0 ? (isLearned ? pages[1] : pages[0]) : pages[2];
	}
	
	public FontRenderer getFontRenderer() {
		return this.fontRendererObj;
	}
	
	public static class Element implements IGuiElement {

		@Override
		public Object getServerContainer(EntityPlayer player, World world,
				int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGui(EntityPlayer player, World world, int x,
				int y, int z) {
			return new GuiAbilityDeveloper((TileAbilityDeveloper) world.getTileEntity(x, y, z),
					APDataMain.loadPlayerData(player).getCategoryID() != 0);
		}
		
	}
	
}
