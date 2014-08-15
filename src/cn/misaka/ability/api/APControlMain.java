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
package cn.misaka.ability.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import cn.liutils.api.util.GenericUtils;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.api.ability.AbilitySkill;
import cn.misaka.ability.api.control.PlayerControlStat;
import cn.misaka.ability.api.control.SkillControlStat;
import cn.misaka.ability.api.control.preset.ControlPreset;
import cn.misaka.ability.api.control.preset.ControlPreset.SkillKey;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.data.PlayerDataClient;
import cn.misaka.ability.system.network.message.MsgControl;
import cn.misaka.core.AcademyCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 玩家操作的处理和信息提供。
 * @author WeAthFolD
 */
public class APControlMain {
	
	/**
	 * 两个Side的数据表
	 */
	protected static Map<EntityPlayer, PlayerControlStat>
		dataMap_client = new HashMap(),
		dataMap_server = new HashMap();
	
	public static final int 
		KEYS = 4, //侦听的键位数
		PRESETS = 4; //预设数量
	
	public static void init(Configuration conf) {
		for(int i = 0; i < PRESETS; i++) {
			ControlPreset cp = new ControlPreset();
			for(int j = 0; j < KEYS; j++)
				cp.settings[j] = SkillKey.fromString(
						conf.get("controls", "preset_" + i + "_" + j, "0,0").getString()
						);
			controlPresets.add(cp);
		}
	}
	
	//-----------------------操作数据-------------------------
	/**
	 * 获取（并加载）玩家的能力操作数据。
	 */
	public static PlayerControlStat loadControlData(EntityPlayer player) {
		Map<EntityPlayer, PlayerControlStat> map = getMap(player.worldObj);
		PlayerControlStat data = map.get(player);
		if(data == null) {
			data = new PlayerControlStat();
			map.put(player, data);
		}
		return data;
	}
	
	/**
	 * 获取玩家的能力操作数据。注意有可能为NULL。
	 * @param player
	 * @return
	 */
	public static PlayerControlStat getControlData(EntityPlayer player) {
		return getMap(player.worldObj).get(player);
	}
	
	//------------------------操作预设----------------------------
	
	@SideOnly(Side.CLIENT)
	private static List<ControlPreset> controlPresets = new ArrayList<ControlPreset>();
	
	/**
	 * 当前活跃的预设id
	 */
	private static int current_preset_id = 0;
	
	public static void setPresetID(int id) {
		current_preset_id = id;
	}
	
	public static int getCurrentPresetID() {
		return current_preset_id;
	}
	
	public static ControlPreset getPreset(int id) {
		return GenericUtils.safeFetchFrom(controlPresets, id);
	}
	
	public static ControlPreset getCurrentPreset() {
		return controlPresets.get(current_preset_id);
	}
	
	/**
	 * 将键位id映射为能力操作信息。
	 */
	public static SkillKey cipher(int keyid) {
		ControlPreset pres = getCurrentPreset();
		SkillKey ent = keyid >= 0 ? pres.settings[keyid] : null;
		return ent == null ? null : ent.first < 0 ? null : ent;
	}
	
	/**
	 * 将能力操作信息映射回键位id。如果找不到这样的映射，返回-1.
	 */
	public static int decipher(SkillKey entry) {
		ControlPreset pres = getCurrentPreset();
		for(int i = 0; i < KEYS; i++) {
			if(pres.settings[i].first == entry.first && pres.settings[i].second == entry.second)
				return i;
		}
		return -1;
	}
	
	/**
	 * 将操作预设保存到config中
	 * @param conf
	 */
	public static void saveToConfig(Configuration conf) {
		for(int i = 0; i < PRESETS; i++) {
			String[] ss = entriesToString(i);
			for(int j = 0; j < KEYS; j++) 
				conf.get("controls", "preset_" + i + "_" + j, "0,0").set(ss[j]);;
		}
		conf.save();
	}
	
	/**
	 * 将操作预设转化为String数组，以便存储
	 */
	private static String[] entriesToString(int id) {
		SkillKey[] entries = controlPresets.get(id).settings;
		String [] ss = new String[4];
		for(int i = 0; i < KEYS; i++)
			ss[i] = entries[i].toString();
		return ss;
	}
	
	//--------------------------控制信息处理------------------------------------
	@SideOnly(Side.CLIENT)
	/**
	 * 处理客户端键盘映射。
	 */
	public static void onKeyChangedClient(int keyid, boolean down) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer; //Can't be NULL!
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		if(data.getCategoryID() == 0) return;
		if(data != null && data.isActivated && data.isDataStateGood()) {
			SkillKey entry = cipher(keyid); //解读为skill局部操作
			if(entry != null) {
				onSkillKeyChanged(player, entry.first, entry.second, down); 
				AcademyCraft.netHandler.sendToServer(new MsgControl(entry.first, entry.second, down));
			}
		}
	}
	
	public static void onSkillKeyChanged(EntityPlayer player, int skillID, int keyID, boolean isDown) {
		PlayerControlStat ctrl = loadControlData(player);
		PlayerData data = APDataMain.loadPlayerData(player);
		AbilityCategory cls = data.getAbilityCategory();
		if(cls == null) {
			System.err.println("Can't find player ability class while control has changed, this is a bug!");
			return;
		}
		AbilitySkill skl = cls.getSkill(skillID);
		if(data.isDataStateGood()) {
			ctrl.onKeyStateChange(
					skillID,
					skl.getMaxKeys(),
					keyID,
					isDown);
			skl.onKeyStateChange(player.worldObj, player, ctrl.getSkillStat(skillID), keyID, ctrl);
		}
	}
	
	public static void onTick(boolean isClient) {
		Set< Map.Entry<EntityPlayer, PlayerControlStat> > set = 
				getMap(isClient).entrySet();
		
		for(Map.Entry<EntityPlayer, PlayerControlStat> entry : set) { 
			//Go through all the players and their skill states, execute updates
			EntityPlayer player = entry.getKey();
			PlayerData data = APDataMain.loadPlayerData(player);
			if(data == null || !data.isDataStateGood()) continue;
			
			AbilityCategory ability = data.getAbilityCategory();
			PlayerControlStat ctrl = entry.getValue();
			if(ability == null) {
				System.err.println("Can't find player ability class while doing tickUpdate. This is a BUG!");
				return;
			}
			
			AbilityLevel level = ability.getLevel(data.getLevelID());
			if(level == null)
				return;
			level.onPlayerUpdate(player.worldObj, player, ctrl);
			
			for(int k = 0; k < ability.ability_skills.length; k++) {
				AbilitySkill skl = ability.ability_skills[k];
				SkillControlStat st = ctrl.loadSkillStat(k, ability.getMaxSkills());
				st.onUpdate();
				if(skl.isSkillActivated(player.worldObj, player, st, ctrl)) {
					if(!skl.onSkillTick(player.worldObj, player, st, ctrl)) {
						st.clear();
					}
				}
			}
			
		}
	}
	
	/**
	 * 简单的wrapper
	 */
	private static Map<EntityPlayer, PlayerControlStat> getMap(World world) {
		return getMap(world.isRemote);
	}
	
	/**
	 * 返回当前Side对应的数据表。
	 */
	private static Map<EntityPlayer, PlayerControlStat> getMap(boolean isClient) { //好想inline啊
		return isClient ? dataMap_client : dataMap_server;
	}

}
