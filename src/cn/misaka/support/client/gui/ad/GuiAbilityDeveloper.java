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
	
	public abstract class Page {
		String lclName;
		private Set<LIGuiPart> partSet = new HashSet<LIGuiPart>();
		
		public final float OFFSET_X = 3.5F, OFFSET_Y = 34.5F,
				WIDTH = 136.5F, HEIGHT = 146.5F;
		
		public Page(String unlocalized_name) {
			lclName = unlocalized_name;
			addElements(partSet);
		}
		
		public final Set<LIGuiPart> getPart() {
			return partSet;
		}
		
		public abstract void renderPageArea();
		
		public abstract void addElements(Set<LIGuiPart> set);
		
		public abstract void onButtonClicked(LIGuiButton button);
		
		public final void renderText() {
			//TODO:Draw the goddamn text!
		}
	}
	
	TileAbilityDeveloper myTile;
	
	public static final int MAX_PAGES = 2;
	private final Page[] pages = {
		new PageLearning(this),
		new PageLearning(this),
		new PageSkillLearning(this)
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
		this.addElements(
			new LIGuiButton("pgrt", 216, 3, 8, 7).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN),
			new LIGuiButton("pglf", 89, 3, 8, 7).setTextureOverride(APClientProps.TEX_GUI_AD_MAIN)
		);
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
    	
    	float x0 = this.width / 2F - 114,
    			y0 = this.height / 2F - 93;
    	GL11.glPushMatrix(); {
    		GL11.glTranslatef(x0 + 3F, y0 + 33F, 0F);
    		Page page = getCurrentPage();
    		page.renderPageArea();
    	} GL11.glPopMatrix();
    	
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	GL11.glColor4f(1F, 1F, 1F, 1F);
    	this.drawElements();
    	
    	PlayerData data = APDataMain.loadPlayerData(Minecraft.getMinecraft().thePlayer);
    	renderPlayerInf(data, x0, y0);
    	
    	RenderUtils.loadTexture(APClientProps.TEX_GUI_AD_MAIN);
    	GL11.glColor4f(1F, 1F, 1F, 1F);
    	HudUtils.drawTexturedModalRect(x0, y0, 1, 1, 228, 184.5F, 456, 369);
    	super.drawScreen(par1, par2, par3);
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
	public Set<LIGuiPart>[] getAdditionalButtons() {
		return new Set[] { getCurrentPage().partSet };
	}

    private Page getCurrentPage() {
    	return pageID == 0 ? 
    			(isLearned ?  pages[1] : pages[0] ) :
    			pages[2];
    }
    
	@Override
	public void onButtonClicked(LIGuiButton button, boolean b) {
		if(b) {
			if(button.name.equals("pgrt")) {
				++pageID;
				if(pageID == MAX_PAGES)
					pageID = 0;
			} else if(button.name.equals("pglf")) {
				--pageID;
				if(pageID < 0)
					pageID = MAX_PAGES - 1;
			}
		} else {
			getCurrentPage().onButtonClicked(button);
		}
	}

}
