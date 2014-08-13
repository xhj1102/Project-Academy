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

import java.util.HashSet;
import java.util.Set;

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
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.client.render.entity.EntityRailgunFX;
import cn.misaka.ability.entity.EntityRailgun;
import cn.misaka.ability.entity.fx.EntityRayAttenuate.AttenuateType;
import cn.misaka.ability.system.data.APDataMain;
import cn.misaka.core.proxy.APClientProps;
import cn.misaka.core.register.APItems;

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
	
	private static Set<Entry> acceptEntities = new HashSet();
	static {
		acceptEntities.add(new Entry(APItems.coin, ShootType.QTE));
		acceptEntities.add(new Entry(Items.iron_ingot, ShootType.DIRECT));
		acceptEntities.add(new Entry(Blocks.iron_block, ShootType.DIRECT));
	}

	public SkillRailgun() {
		super("skill.elec.railgun");
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
	public void onKeyStateChange(World world, EntityPlayer player, SkillControlStat stat, int kid, PlayerControlStat mctrl) {
		if(stat.isKeyDown(0)) {
			System.out.println("Attempting to shoot railgun");
			ItemStack stack = player.getCurrentEquippedItem();
			PlayerData data = APDataMain.loadPlayerData(player);
			if(stack != null) {
				Item item0 = stack.getItem();
				Entry ent = contains(item0);
				if(ent != null) {
					if(ent.second == ShootType.QTE) {
						IRailgunQTE inf = (IRailgunQTE) item0;
						Pair<Float, Float> range = inf.getAcceptedRange();
						float prog = inf.getQTEProgress(stack);
						if(!inf.isQTEinProgress(stack) || prog < range.first || prog > range.second)
							return;
					}
					if(!world.isRemote) {
						AttenuateType tpe;
						float factor, initDamage;
						if(item0 instanceof ItemBlock) {
							tpe = AttenuateType.SQUARED;
							factor = 0.03F;
							initDamage = 30 + data.skill_exp[6] * 4;
						} else {
							tpe = AttenuateType.LINEAR;
							factor = 0.03F;
							initDamage = 25 + data.skill_exp[6] * 4;
						}
						world.spawnEntityInWorld(new EntityRailgun(world, player, initDamage).setAttenuateType(tpe));
						System.out.println("Shooting railgun~");
					} else {
						world.spawnEntityInWorld(new EntityRailgunFX(world, player));
					}
				}
			}
		}
	}
	
	private Entry contains(Item item0) {
		if(item0 instanceof ItemBlock)
			for(Entry ent : acceptEntities) {
				if(ent.first == ((ItemBlock)item0).field_150939_a)
					return ent;
			}
		else
			for(Entry ent : acceptEntities) {
				if(ent.first == item0)
					return ent;
			}
		return null;
	}

}
