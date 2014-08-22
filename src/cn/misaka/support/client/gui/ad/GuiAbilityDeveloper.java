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

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.gui.LIGuiButton;
import cn.liutils.api.client.gui.LIGuiPage;
import cn.liutils.api.client.gui.LIGuiPart;
import cn.liutils.api.client.gui.LIGuiScreen;
import cn.liutils.api.client.util.HudUtils;
import cn.liutils.api.client.util.RenderUtils;
import cn.liutils.api.register.IGuiElement;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author WeAthFolD
 *
 */
public class GuiAbilityDeveloper extends LIGuiScreen {
	
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
	
	private LIGuiPage pageMain = new LIGuiPage("main", 0F, 0F) {

		@Override
		public void drawPage() {
		}

		@Override
		public void addElements(Set<LIGuiPart> set) {
			set.add(new LIGuiButton("pglf", 89, 7, 8.5F, 7.5F).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN));
			set.add(new LIGuiButton("pgrt", 216, 7, 8.5F, 7.5F).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN));
		}

		@Override
		public void onPartClicked(LIGuiPart part) {
			System.out.println("prcl");
			if(part.name.equals("pgrt")) {
				++pageID;
				if(pageID == MAX_PAGES)
					pageID = 0;
			} else if(part.name.equals("pglf")) {
				--pageID;
				if(pageID < 0)
					pageID = MAX_PAGES - 1;
			}
		}
		
	};
	
	public static final float PG_OFFSET_X = 3.5F, PG_OFFSET_Y = 34.5F,
			PG_WIDTH = 136.5F, PG_HEIGHT = 146.5F;
	
	TileAbilityDeveloper myTile;
	
	public static final int MAX_PAGES = 2;
	private final LIGuiPage[] pages = {
		new PageLearning(),
		new PageLearning(),
		new PageSkillLearning()
	};
	
	boolean isLearned;
	int pageID;

	/**
	 * @param tile
	 * @param learned
	 */
	public GuiAbilityDeveloper(TileAbilityDeveloper tile, boolean learned) {
		super(228, 180);
		myTile = tile;
		isLearned = learned;
	}
	
	@Override
	public void onGuiClosed() {
		System.out.println("onGuiClosed");
	}
	
    @Override
	public void drawScreen(int par1, int par2, float par3)
    {
    	
    	HudUtils.setTextureResolution(512, 512);
    	HudUtils.setZLevel(zLevel);
    	
    	float x0 = this.width / 2F - 114,
    			y0 = this.height / 2F - 93;
    		
    	this.drawDefaultBackground();
    	
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glColor4f(1F, 1F, 1F, 1F);
    	
    	this.drawElements(par1, par2);
    	
    	PlayerData data = APDataMain.loadPlayerData(Minecraft.getMinecraft().thePlayer);
    	renderPlayerInf(data, x0, y0);
    	
    	RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_MAIN);
    	HudUtils.drawTexturedModalRect(x0, y0, 0, 0, 228F, 184.5F, 456, 369);
    }
    
    private void renderPlayerInf(PlayerData data, float x0, float y0) {
    	AbilityCategory cat = data.getAbilityCategory();
    	if(cat != null) {
    		
    		ResourceLocation logo = cat.getLogo();
    		if(logo != null) {
    			RenderUtils.loadTexture(logo);
    			HudUtils.drawTexturedModalRect(x0 + 150, y0 + 133.5F, 18.5F, 18.5F);
    		}
    	} else {
    		
    	}
    }

	@Override
	public void updateActivatedPages(Set<LIGuiPage> set) {
		set.add(pageMain);
		set.add(pageID == 0 ? (isLearned ? pages[1] : pages[0]) : pages[2] );
	}
}
