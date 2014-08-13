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
package cn.misaka.ability.system.control;

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
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.api.data.PlayerDataClient;
import cn.misaka.ability.system.control.preset.ControlPreset;
import cn.misaka.ability.system.control.preset.ControlPreset.Entry;
import cn.misaka.ability.system.data.APDataMain;
import cn.misaka.ability.system.network.message.MsgControl;
import cn.misaka.core.AcademyCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 操作控制统合。
 * @author WeAthFolD
 *
 */
public class APControlMain {
	
	protected static Map<EntityPlayer, PlayerControlStat>
		dataMap_client = new HashMap(),
		dataMap_server = new HashMap();
	
	public static final int KEYS = 4, PRESETS = 4;
	
	private static int current_preset_id = 0;
	
	/**
	 * 获取（并加载）玩家的能力操作数据。
	 * @param player
	 * @return
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
	
	public static void onSkillKeyChanged(EntityPlayer player, int skill_id, int key_id, boolean isDown) {
		PlayerControlStat ctrl = loadControlData(player);
		PlayerData data = APDataMain.loadPlayerData(player);
		AbilityCategory cls = data.getAbilityClass();
		if(cls == null) {
			System.err.println("Can't find player ability class while control has changed, this is a bug!");
			return;
		}
		AbilitySkill skl = cls.getSkill(skill_id);
		if(data.isDataStateGood()) {
			ctrl.onKeyStateChange(
					skill_id,
					skl.getMaxKeys(),
					key_id,
					isDown);
			skl.onKeyStateChange(player.worldObj, player, ctrl.getSkillStat(skill_id), key_id, ctrl);
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
			
			AbilityCategory ability = data.getAbilityClass();
			PlayerControlStat ctrl = entry.getValue();
			if(ability == null) {
				System.err.println("Can't find player ability class while doing tickUpdate. This is a BUG!");
				return;
			}
			
			AbilityLevel level = ability.getLevel(data.getLevel());
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
	
	public static ControlPreset getPreset(int id) {
		return GenericUtils.safeFetchFrom(controlPresets, id);
	}
	
	public static void setPresetID(int id) {
		current_preset_id = id;
	}
	
	@SideOnly(Side.CLIENT)
	private static List<ControlPreset> controlPresets = new ArrayList<ControlPreset>();
	
	public static void init(Configuration conf) {
		for(int i = 0; i < PRESETS; i++) {
			ControlPreset cp = new ControlPreset();
			for(int j = 0; j < KEYS; j++)
				cp.settings[j] = Entry.fromString(
						conf.get("controls", "preset_" + i + "_" + j, "0,0").getString()
						);
			controlPresets.add(cp);
		}
	}
	
	public static void saveToConfig(Configuration conf) {
		for(int i = 0; i < PRESETS; i++) {
			String[] ss = entriesToString(i);
			for(int j = 0; j < KEYS; j++) 
				conf.get("controls", "preset_" + i + "_" + j, "0,0").set(ss[j]);;
		}
		conf.save();
	}
	
	public static int getCurrentPresetID() {
		return current_preset_id;
	}
	
	public static ControlPreset getCurrentPreset() {
		return controlPresets.get(current_preset_id);
	}
	
	@SideOnly(Side.CLIENT)
	private static String[] entriesToString(int id) {
		Entry[] entries = controlPresets.get(id).settings;
		String [] ss = new String[4];
		for(int i = 0; i < KEYS; i++)
			ss[i] = entries[i].toString();
		return ss;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * 处理键盘映射。
	 * @param keyid
	 */
	public static void onKeyChangedClient(int keyid, boolean down) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer; //Can't be NULL!
		PlayerDataClient data = (PlayerDataClient) APDataMain.loadPlayerData(player);
		if(data.getCategory() == 0) return;
		if(data != null && data.isActivated && data.isDataStateGood()) {
			Entry entry = cipher(keyid);
			if(entry != null) {
				onSkillKeyChanged(player, entry.first, entry.second, down); //解读为skill局部的id
				AcademyCraft.netHandler.sendToServer(new MsgControl(entry.first, entry.second, down));
			}
		}
	}
	
	public static Entry cipher(int keyid) {
		ControlPreset pres = getCurrentPreset();
		Entry ent = keyid >= 0 ? pres.settings[keyid] : null;
		return ent == null ? null : ent.first < 0 ? null : ent;
	}
	
	public static int decipher(Entry entry) {
		ControlPreset pres = getCurrentPreset();
		for(int i = 0; i < KEYS; i++) {
			if(pres.settings[i].first == entry.first && pres.settings[i].second == entry.second)
				return i;
		}
		return -1;
	}
	
	private static Map<EntityPlayer, PlayerControlStat> getMap(World world) {
		return getMap(world.isRemote);
	}
	
	private static Map<EntityPlayer, PlayerControlStat> getMap(boolean isClient) { //好想inline啊
		return isClient ? dataMap_client : dataMap_server;
	}

}
