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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import cn.misaka.ability.api.APCategoryStorage;
import cn.misaka.ability.api.ability.AbilityCategory;
import cn.misaka.ability.api.ability.AbilityLevel;
import cn.misaka.ability.system.data.PlayerDataClient;
import cn.misaka.ability.system.data.PlayerDataServer;
import cn.misaka.ability.system.data.PlayerDataUpdater;
import cn.misaka.ability.system.network.message.MsgSyncToClient;
import cn.misaka.core.AcademyCraft;

import com.google.gson.*;

/**
 * 单个玩家的能力信息。（基类）
 * @author WeAthFolD, Alan
 */
public abstract class PlayerData {
	
	//---------默认数值-----------
	public static final int
		INIT_CP[] = new int[] {
			800, 2000, 3500, 6000, 10000
		};
	public static final float
		RECOVER_SPEED[] = new float[] {
			0.5F, 1.5F, 2.2F, 3.0F, 4.0F
		}
	;
	
	
	//------默认数值END----------
	
	protected int 
		catid, //能力系id
		level; //玩家等级
		
	public int max_cp; //最大cp值
	public float
		current_cp; //当前cp值
	
	protected boolean[] skill_open; //某技能是否被学习
	protected float[] skill_exp; //技能熟练度
	
	/**
	 * 当前能力是否被激活
	 */
	public boolean isActivated;
	
	/**
	 * 对应的玩家实例
	 */
	protected EntityPlayer thePlayer;

	public PlayerData(EntityPlayer player) {
		thePlayer = player;
	}
	
	public int getLevelID() {
		return level;
	}
	
	public int getCategoryID() {
		return catid;
	}
	
	public void setLevel(int l) {
		level = l;
		onStateChanged();
	}
	
	public void setCategory(int c) {
		catid = c;
		onStateChanged();
	}
	
	/**
	 * 获取CP的恢复速度。单位：CP/Tick
	 * @return
	 */
	public float getCPRecoverSpeed() {
		return RECOVER_SPEED[level];
	}
	
	public void resetSkillInf() {
		AbilityCategory cat = getAbilityCategory();
		if(cat == null) return;
		AbilityLevel alevel = cat.getLevel(level);
		if(alevel == null) return;
		skill_open = new boolean[cat.getMaxSkills()];
		skill_exp = new float[cat.getMaxSkills()];
		for(int i = 0; i > cat.getMaxSkills(); i++) {
			skill_open[i] = alevel.isSkillDefaultActivated(i);
		}
		if(!thePlayer.worldObj.isRemote) {
			AcademyCraft.netHandler.sendTo(new MsgSyncToClient(this, 0x02), (EntityPlayerMP) thePlayer);
		}
	}
	
	public EntityPlayer getPlayer() {
		return thePlayer;
	}
	
	public AbilityCategory getAbilityCategory() {
		return this.isDataStateGood() ? APCategoryStorage.getAbility(catid) : null;
 	}
	
	public AbilityLevel getLevel() {
		AbilityCategory cat = getAbilityCategory();
		if(cat == null) return null;
		return cat.ability_levels.length >= level ? null : cat.ability_levels[level];
	}
	
	public boolean isSkillActivated(int id) {
		return skill_open == null || skill_open.length <= id ? true : skill_open[id];
	}
	
	public float getSkillExp(int id) {
		return skill_exp == null || skill_exp.length <= id ? 0.0F : skill_exp[id];
	}
	
	
	//----------------DATA存取------------------
	/**
	 * 从nbt加载数据或发送同步请求。
	 */
	protected abstract void loadData();

	/**
	 * 返回当前数据是否（总体上）处于良好情况
	 */
	public abstract boolean isDataStateGood();
	
	/**
	 * 每tick运行。检查数据完整性，并选择性进行同步。，
	 */
	public abstract void updateTick();
	
	public void fromAbilityData(PlayerDataUpdater data) {
		fromAbilityData(data, 0x03);
	}
	
	/**
	 * 从DataUpdater中获取玩家能力数据，并设置到this。
	 */
	public void fromAbilityData(PlayerDataUpdater data, int flag) {
		if(data == null) {
			System.err.println("Attempting to load ability data from a NULL pointer");
			return;
		}
		//同步一般数据
		if((flag & 0x01) != 0) {
			catid = data.category;
			level = data.level;
			max_cp = data.maxCP;
			current_cp = data.currentCP;
		}
		//同步技能信息
		if((flag & 0x02) != 0) {
			skill_open = data.ac_skill_open;
			skill_exp = data.ac_skill_exp;
		}
		//状态更改，进行检查
		this.onStateChanged();
	}
	
	/**
	 * 将当前信息转换成Updater以进行传输或保存。
	 */
	public PlayerDataUpdater toUpdater() {
		return new PlayerDataUpdater(thePlayer, catid, level, max_cp, (int) current_cp, skill_open, skill_exp);
	}
	
	/**
	 * 即时保存数据。
	 */
	public void saveData() {
		saveUpdater(thePlayer, toUpdater());
	}
	
	public void onStateChanged() {
		if(skill_open == null || skill_exp == null || skill_open.length != getAbilityCategory().getMaxSkills() 
				|| skill_exp.length != getAbilityCategory().getMaxSkills()) {
			System.err.println("Creating new skill information for pre is null");
			resetSkillInf();
		}
	}
	
	//-----------------STATIC METHODS(LOAD AND SAVE)----------------------
	
	public static PlayerData createPlayerData(EntityPlayer player) {
		return player.worldObj.isRemote ? new PlayerDataClient(player) : new PlayerDataServer(player);
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
    	System.out.println("Aquiring Updater" + s);
    	File f = new File(s);
    	
    	PlayerDataUpdater updater;
    	if(!f.exists()){
    		updater = new PlayerDataUpdater(p, (byte)0, (byte)0, 0, 0, new boolean[3], new float[3]);
    		saveUpdater(p, updater);
    	}
    	s += File.separator + p.getCommandSenderName() + ".ac";
    	f = new File(s);
    	if(!f.exists()){
    		updater = new PlayerDataUpdater(p, (byte)0, (byte)0, 0, 0, new boolean[3], new float[3]);
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
	
}
