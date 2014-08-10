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
package cn.misaka.ability.system.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.api.data.PlayerDataClient;
import cn.misaka.ability.api.data.PlayerDataServer;

/**
 * @author WeAthFolD
 *
 */
public class APDataMain {
	
	private static HashMap<EntityPlayer, PlayerData> dataMap_client = new HashMap<EntityPlayer, PlayerData>();
	private static HashMap<EntityPlayer, PlayerData> dataMap_server = new HashMap<EntityPlayer, PlayerData>();

	public static PlayerData loadPlayerData(EntityPlayer player) {
		HashMap<EntityPlayer, PlayerData> dataMap = getDataMap(player.worldObj.isRemote);
		PlayerData data = dataMap.get(player);
		if(data == null) {
			data = PlayerData.getPlayerData(player);
			dataMap.put(player, data);
		}
		return data;
	}
	
	public static void onTick(boolean isRemote) {
		HashMap<EntityPlayer, PlayerData> map = getDataMap(isRemote);
		for(PlayerData data : map.values()) {
			data.updateTick();
		}
		if(isRemote) {
		} else {
			
		}
	}
	
	public static void savePlayerData(boolean isRemote) {
		if(!isRemote) {
			for(Map.Entry<EntityPlayer, PlayerData> entry : getDataMap(false).entrySet()) {
				PlayerData.saveUpdater(entry.getKey(), entry.getValue().toUpdater());
			}
		}
	}
	
	public static  PlayerData getPlayerData(EntityPlayer player) {
		return getDataMap(player.worldObj.isRemote).get(player);
	}
	
	private static HashMap<EntityPlayer, PlayerData> getDataMap(boolean isRemote) {
		return isRemote ? dataMap_client : dataMap_server;
	}

}
