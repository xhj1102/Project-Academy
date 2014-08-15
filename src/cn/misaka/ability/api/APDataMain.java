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

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.api.data.PlayerData;

/**
 * 玩家数据存储的核心，负责PlayerData的管理。
 * @author WeAthFolD
 */
public class APDataMain {
	
	//分Side存储的数据表
	private static HashMap<EntityPlayer, PlayerData> 
		dataMap_client = new HashMap<EntityPlayer, PlayerData>(),
		dataMap_server = new HashMap<EntityPlayer, PlayerData>();

	/**
	 * 返回某玩家的能力数据。如果不存在，自动创建一个并返回。
	 */
	public static PlayerData loadPlayerData(EntityPlayer player) {
		HashMap<EntityPlayer, PlayerData> dataMap = getDataMap(player.worldObj.isRemote);
		PlayerData data = dataMap.get(player);
		if(data == null) {
			data = PlayerData.createPlayerData(player);
			dataMap.put(player, data);
		}
		return data;
	}
	
	/**
	 * tick更新
	 */
	public static void onTick(boolean isRemote) {
		HashMap<EntityPlayer, PlayerData> map = getDataMap(isRemote);
		for(PlayerData data : map.values()) {
			data.updateTick();
		}
	}
	
	/**
	 * 保存所有玩家的数据到json中。
	 */
	public static void savePlayerData(boolean isRemote) {
		if(!isRemote) {
			for(Map.Entry<EntityPlayer, PlayerData> entry : getDataMap(false).entrySet()) {
				PlayerData.saveUpdater(entry.getKey(), entry.getValue().toUpdater());
			}
		}
	}
	
	/**
	 * 获取某玩家的能力数据，有可能为NULL。
	 */
	public static PlayerData getPlayerData(EntityPlayer player) {
		return getDataMap(player.worldObj.isRemote).get(player);
	}
	
	/**
	 * 获取当前Side对应的数据表
	 */
	private static HashMap<EntityPlayer, PlayerData> getDataMap(boolean isRemote) {
		return isRemote ? dataMap_client : dataMap_server;
	}

}
