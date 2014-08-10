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
package cn.misaka.ability.api.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.misaka.ability.system.data.PlayerDataUpdater;

import com.google.gson.*;

/**
 * 单个玩家的能力信息。（基类）
 * @author WeAthFolD, Alan
 *
 */
public abstract class PlayerData {
	
	public int 
		classid,
		level,
		max_cp;
		
	public int
		current_cp;
	
	public boolean[] skill_open;
	public float[] skill_exp;
	
	/**
	 * 当前能力是否被激活
	 */
	public boolean isActivated;
	
	
	EntityPlayer thePlayer;

	public PlayerData(EntityPlayer player) {
		thePlayer = player;
	}
	
	/**
	 * 从nbt加载数据或发送同步请求。
	 */
	protected abstract void loadData();

	/**
	 * 返回当前数据是否（总体上）处于良好情况
	 */
	public abstract boolean isDataStateGood();
	
	/**
	 * 没tick运行。检查数据完整性，并选择性进行同步。，
	 */
	public abstract void updateTick();
	
	public void fromAbilityData(PlayerDataUpdater data) {
		fromAbilityData(data, 0x03);
	}
	
	public void fromAbilityData(PlayerDataUpdater data, int flag) {
		if(data == null) {
			System.err.println("Attempting to load ability data from a NULL pointer");
			return;
		}
		if((flag & 0x01) != 0) {
			classid = data.classid;
			level = data.level;
			max_cp = data.maxCP;
		}
		if((flag & 0x02) != 0) {
			skill_open = data.ac_skill_open;
			skill_exp = data.ac_skill_exp;
		}
	}
	
	public PlayerDataUpdater toUpdater() {
		return new PlayerDataUpdater(thePlayer, classid, level, max_cp, skill_open, skill_exp);
	}
	
	public void saveData() {
		saveUpdater(thePlayer, toUpdater());
	}
	
	public EntityPlayer getPlayer() {
		return thePlayer;
	}
	
	//-----------------STATIC METHODS(LOAD AND SAVE)----------------------
	
	public static PlayerData getPlayerData(EntityPlayer player) {
		PlayerData data;
		if(player.worldObj.isRemote) {
			data = new PlayerDataClient(player);
			
		} else {
			data = new PlayerDataServer(player);
		}
		return data;
	}
	
	/**
	 * 接下来是json与数据间的转换
	*/
    private static String data2json(PlayerDataUpdater a){
    	Gson g = new Gson();
    	return g.toJson(a);
    }
    
    private static PlayerDataUpdater json2data(String json){
    	Gson g = new Gson();
    	return g.fromJson(json, PlayerDataUpdater.class);
    }
    /**
     * 接下来就是鸡冻人心的读取和存储分了，啪啪啪
    */
    public static void saveUpdater(EntityPlayer p, PlayerDataUpdater a) {
    	String s = getUpdaterSavePath();
    	s += File.separator + "AbilityData"; //+ File.separator + p.getCommandSenderName() + ".ac";
    	System.out.println(s);
    	File f = new File(s);
    	if(!f.exists()){
    		f.mkdir();
    	}
    	s += File.separator + p.getCommandSenderName() + ".ac";
    	f = new File(s);
    	try {
    		if(!f.exists()){
        		f.createNewFile();
        	}
        	FileWriter fw = new FileWriter(f);
        	fw.write(data2json(a));
        	fw.close();
    	} catch(Exception e) {
    		System.err.println("Encountered a problem while saving player ability data");
    	}
    }
    
    public static PlayerDataUpdater getUpdater(EntityPlayer p) {
    	String s = getUpdaterSavePath();
    	s += File.separator + "AbilityData"; //+ File.separator + p.getCommandSenderName() + ".ac";
    	System.out.println("Saving www " + s);
    	File f = new File(s);
    	
    	PlayerDataUpdater updater;
    	if(!f.exists()){
    		updater = new PlayerDataUpdater(p, (byte)0, (byte)0, 0, new boolean[3], new float[3]);
    		saveUpdater(p, updater);
    	}
    	s += File.separator + p.getCommandSenderName() + ".ac";
    	f = new File(s);
    	if(!f.exists()){
    		updater = new PlayerDataUpdater(p, (byte)0, (byte)0, 0, new boolean[3], new float[3]);
    		saveUpdater(p, updater);
    	}
    	
    	try {
    		FileReader fr = new FileReader(f);
    		char[] temp = new char[1024];
    		String json = new String(temp, 0, fr.read(temp));
    		fr.close();
    		return json2data(json);
    	} catch(Exception e) {
    		System.err.println("Encountered a problem while reading player ability data.");
    	}
    	return null;
    }
    
	/**
	 * 用于定位世界的保存路径
	*/
	public static String getUpdaterSavePath(){
    	String runDir = System.getProperty("user.dir");
    	System.out.println(runDir);
    	//String[] mcDir = runDir.split(".minecraft");
    	String saveDir = runDir + File.separator + "saves" + File.separator + MinecraftServer.getServer().getFolderName();
    	File f = new File(saveDir);
    	if(!f.exists()){
    		saveDir = runDir + ("\\saves" + File.separator + MinecraftServer.getServer().getFolderName());
    	}
    	return saveDir;
    }
	
}
