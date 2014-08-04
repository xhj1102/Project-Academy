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
package cn.misaka.system.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.api.ability.AbilityClass;
import cn.misaka.api.ability.AbilityLevel;
import cn.misaka.api.ability.AbilitySkill;
import cn.misaka.api.client.data.PlayerDataClient;
import cn.misaka.api.control.PlayerControlData;
import cn.misaka.api.control.SkillControlStat;
import cn.misaka.api.data.PlayerData;
import cn.misaka.api.data.PlayerDataHelper;
import cn.misaka.core.AcademyCraft;
import cn.misaka.system.control.preset.ControlPreset;
import cn.misaka.system.data.APDataMain;
import cn.misaka.system.network.message.MsgControl;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 操作控制统合。
 * @author WeAthFolD
 *
 */
public class APControlMain {
	
	protected static Map<EntityPlayer, PlayerControlData>
		dataMap_client = new HashMap(),
		dataMap_server = new HashMap();

	/**
	 * 获取（并加载）玩家的能力操作数据。
	 * @param player
	 * @return
	 */
	public static PlayerControlData loadControlData(EntityPlayer player) {
		Map<EntityPlayer, PlayerControlData> map = getMap(player.worldObj);
		PlayerControlData data = map.get(player);
		if(data == null) {
			data = new PlayerControlData();
			map.put(player, data);
		}
		return data;
	}
	
	/**
	 * 获取玩家的能力操作数据。注意有可能为NULL。
	 * @param player
	 * @return
	 */
	public static PlayerControlData getControlData(EntityPlayer player) {
		return getMap(player.worldObj).get(player);
	}
	
	public static void onSkillKeyChanged(EntityPlayer player, int skill_id, int key_id, boolean isDown) {
		PlayerControlData ctrl = loadControlData(player);
		PlayerData data = APDataMain.loadPlayerData(player);
		AbilityClass cls = PlayerDataHelper.getAbilityClass(data);
		if(cls == null) {
			System.err.println("Can't find player ability class while control has changed, this is a bug!");
			return;
		}
		AbilitySkill skl = cls.getSkill(skill_id);
		if(data.isDataStateGood()) {
			ctrl.onKeyStateChange(skill_id, skl.getMaxKeys(), key_id, isDown);
			skl.onKeyStateChange(player.worldObj, player, ctrl.getSkillStates(skill_id), key_id, ctrl);
		}
	}
	
	public static void onTick(boolean isClient) {
		Set< Map.Entry<EntityPlayer, PlayerControlData> > set = 
				getMap(isClient).entrySet();
		
		for(Map.Entry<EntityPlayer, PlayerControlData> entry : set) { 
			//Go through all the players and their skill states, execute updates
			EntityPlayer player = entry.getKey();
			PlayerData data = APDataMain.loadPlayerData(player);
			if(data == null || !data.isDataStateGood()) continue;
			
			AbilityClass ability = PlayerDataHelper.getAbilityClass(data);
			PlayerControlData ctrl = entry.getValue();
			if(ability == null) {
				System.err.println("Can't find player ability class while doing tickUpdate. This is a BUG!");
			}
			
			AbilityLevel level = ability.getLevel(data.level);
			level.onPlayerUpdate(player.worldObj, player, ctrl);
			
			for(int k = 0; k < ability.ability_skills.length; k++) {
				AbilitySkill skl = ability.ability_skills[k];
				SkillControlStat st = ctrl.getSkillStates(k);
				if(st.isKeyDown()) {
					if(!skl.onSkillTick(player.worldObj, player, st, ctrl)) {
						st.clear();
					}
				}
			}
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * 处理键盘映射。
	 * @param keyid
	 */
	public static void onKeyChangedClient(int keyid, boolean down) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer; //Can't be NULL!
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		if(data != null && data.isActivated && data.isDataStateGood()) {
			ControlPreset.Entry prs[] = data.getCurrentPreset();
			onSkillKeyChanged(player, prs[keyid].key, prs[keyid].value, down); //解读为skill局部的id
			AcademyCraft.netHandler.sendToServer(new MsgControl(prs[keyid].key, prs[keyid].value, down));
		}
	}
	
	private static Map<EntityPlayer, PlayerControlData> getMap(World world) {
		return getMap(world.isRemote);
	}
	
	private static Map<EntityPlayer, PlayerControlData> getMap(boolean isClient) { //好想inline啊
		return isClient ? dataMap_client : dataMap_server;
	}

}
