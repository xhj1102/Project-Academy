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
package cn.misaka.ability.category.electromaster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cn.liutils.api.util.Pair;
import cn.liutils.api.util.PlayerTicker;
import cn.misaka.ability.api.APControlMain;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.client.render.SkillRender;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.control.preset.ControlPreset.SkillKey;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.category.electromaster.client.SkillRenderArc;
import cn.misaka.ability.category.electromaster.client.SkillRenderRailgun;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.core.register.APItems;
import cn.misaka.support.entity.EntityRailgun;
import cn.misaka.support.entity.fx.EntityRailgunFX;
import cn.misaka.support.entity.fx.EntityRayAttenuate.AttenuateType;

/**
 * 哔哩哔哩！
 * @author WeAthFolD
 *
 */
public class SkillRailgun extends AbilitySkill {
	
	public static class Entry extends Pair<Object, ShootType> {

		public Entry(Object k, ShootType v) {
			super(k, v);
		}
		
		public boolean isItem() {
			return first instanceof Item;
		}
		
		public Block getBlock() {
			return (Block) first;
		}
		
		public Item getItem() {
			return (Item) first;
		}
		
	};
	
	public enum ShootType {
		QTE, DIRECT;
	}
	
	private Map<Object, ShootType> acceptEntities = new HashMap();

	public SkillRailgun(int id) {
		super("skill.elec.railgun", id);
		acceptEntities.put(APItems.coin, ShootType.QTE);
		acceptEntities.put(Items.iron_ingot, ShootType.DIRECT);
		acceptEntities.put(Blocks.iron_block, ShootType.DIRECT);
		this.skillRender = new SkillRenderArc();
	}

	@Override
	public ResourceLocation getLogo() {
		return APClientProps.ELEC_RAILGUN;
	}

	@Override
	public int getSuggestKey(int skillKeyID) {
		return 0;
	}
	
	@Override
	public int getMaxKeys() {
		return 1;
	}
	
	/**
	 * 当按键状况被改变时调用（按下或放开）
	 * @param world
	 * @param player
	 * @param stat
	 * @param kid 状态发生改变的键位id
	 * @param mctrl
	 */
	@Override
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(stat.isKeyDown(0)) {
			ItemStack stack = player.getCurrentEquippedItem();
			PlayerData data = APDataMain.loadPlayerData(player);
			if(stack != null) {
				Item item0 = stack.getItem();
				ShootType type = contains(item0);
				if(type != null) {
					if(type == ShootType.QTE) {
						IRailgunQTE inf = (IRailgunQTE) item0;
						Pair<Float, Float> range = inf.getAcceptedRange();
						float prog = inf.getQTEProgress(stack);
						if(!inf.isQTEinProgress(stack) || prog < range.first || prog > range.second)
							return;
					}
					if(data.drainCP(300)) {
						if(!world.isRemote) {
							AttenuateType tpe;
							float factor, initDamage;
							if(item0 instanceof ItemBlock) {
								tpe = AttenuateType.SQUARED;
								factor = 0.03F;
								initDamage = 30 + data.getSkillExp(skillID) * 4;
							} else {
								tpe = AttenuateType.LINEAR;
								factor = 0.03F;
								initDamage = 25 + data.getSkillExp(skillID) * 4;
							}
							world.spawnEntityInWorld(new EntityRailgun(world, player, initDamage).setAttenuateType(tpe));
						} else {
							world.spawnEntityInWorld(new EntityRailgunFX(world, player));
						}
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isPreparing(PlayerData data) {
		EntityPlayer player = data.getPlayer();
		ItemStack stack = player.getCurrentEquippedItem();
		//if player has railgun ability and current item requires QTE and skill is activated
		if(data.getCategoryID() == 2 && stack != null && stack.getItem() instanceof IRailgunQTE && data.isActivated) {
			IRailgunQTE judge = (IRailgunQTE) stack.getItem();
			if(judge.isQTEinProgress(stack)) {
				if(APControlMain.decipher(new SkillKey(6, 0)) != -1) { //Railgun in player control preset
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean useSkillWithItem() {
		return true;
	}
	
	private ShootType contains(Item item0) {
		if(item0 instanceof ItemBlock){
			Block blck = ((ItemBlock)item0).field_150939_a;
			return acceptEntities.get(blck);
		} else
			return acceptEntities.get(item0);
	}
	
	public boolean isSkillActivated(World world, EntityPlayer player, SkillControlStat stat, PlayerControlStat mctrl) {
		return PlayerTicker.getDeltaTick(player, "railgun", Integer.MAX_VALUE) <= 30 ||
				isPreparing(APDataMain.loadPlayerData(player));
	}

}
