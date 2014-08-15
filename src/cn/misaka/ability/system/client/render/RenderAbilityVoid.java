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
package cn.misaka.ability.system.client.render;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.util.RenderUtils;
import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.client.render.SkillRender.SkillRenderType;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author WeAthFolD
 *
 */
public class RenderAbilityVoid implements IItemRenderer {

	protected static ModelBiped model = new ModelBiped();
	
	/**
	 * 
	 */
	public RenderAbilityVoid() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		SkillRenderType tp = type == ItemRenderType.EQUIPPED_FIRST_PERSON ? SkillRenderType.FIRSTPERSON : SkillRenderType.EQUIPPED;
		EntityPlayer player = (EntityPlayer) data[1];
		PlayerData pdata = APDataMain.loadPlayerData(player);
		PlayerControlStat pstat = APControlMain.loadControlData(player);
		if(!pdata.isDataStateGood()) return;

		GL11.glPushMatrix(); {
			
			AbilityCategory ac = pdata.getAbilityCategory();
			
			if(ac != null) { //遍历skill渲染器然后执行渲染
				for(int i = 0; i < ac.getMaxSkills(); i++) {
					AbilitySkill skl = ac.getSkill(i);
					SkillControlStat sklstat = pstat.getSkillStat(i);
					if(sklstat != null && skl.isSkillActivated(player.worldObj, player, sklstat, pstat)) 
						skl.getSkillRender().onRender(player, sklstat, pdata, tp);
				}
			}
			
			if(type == ItemRenderType.EQUIPPED_FIRST_PERSON)
				renderHand(player);
			
		} GL11.glPopMatrix();
	}
	
	public static final void renderHand(EntityPlayer player) {
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();

		RenderUtils.renderEnchantGlint_Equip();
		RenderUtils.loadTexture("minecraft:textures/entity/steve.png");
		GL11.glRotated(-23.75, 0.0F, 0.0F, 1.0F);
		GL11.glRotated(21.914, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(32.75, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(.758F, -.072F, -.402F);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);

		model.onGround = 0.0F;
		model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
		model.bipedRightArm.render(0.0625F);

		
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	protected static final void renderHand2(EntityPlayer player) {
		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_CULL_FACE);

		RenderUtils.renderEnchantGlint_Equip();
		RenderUtils.loadTexture("minecraft:textures/entity/steve.png");
		GL11.glRotated(-23.75, 0.0F, 0.0F, 1.0F);
		GL11.glRotated(21.914, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(32.75, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(.758F, -.072F, -.402F);
		GL11.glColor3f(0.0F, 1.0F, 1.0F);

		model.onGround = 0.0F;
		model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
		model.bipedRightArm.render(0.0625F);
		
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

}
