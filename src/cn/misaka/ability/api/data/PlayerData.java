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

import com.google.gson.*;

/**
 * 单个玩家的能力信息。（基类）
 * @author WeAthFolD
 *
 */
public abstract class PlayerData {
	
	public int 
		classid,
		level,
		max_cp;
		
	public int
		current_cp;
	
	public boolean isActivated;
	
	EntityPlayer thePlayer;
	
	
	/**
	 * MAJOR：最大CP、等级、能力class、
	 * CONTROL：技能操作状态（当前预设id和四个预设）（CLIENT ONLY）
	 * REALTIME：
	 * ALL：所有
	 * 注意可以用flag位运算进行叠加。
	 */
	public enum EnumPlayerData {
		MAJOR(1), CONTROL(1 << 1), REALTIME(1 << 2),
		ALL(MAJOR.flag | CONTROL.flag | REALTIME.flag);
		
		public int flag = 0x00;
		EnumPlayerData(int bin) {
			flag = bin;
		}
	}

	public PlayerData(EntityPlayer player) {
		thePlayer = player;
	}
	
	public void onUpdate() {
	}

	/**
	 * 返回当前数据是否（总体上）处于良好情况
	 */
	public abstract boolean isDataStateGood();
	
	/**
	 * 返回某几类数据是否处于良好情况
	 * @param flag 参照 @EnumPlayerData 的flag进行计算。
	 */
	public abstract boolean isDataStateGood(int flag);
	/**
	 * 接下来是json与数据间的转换
	*/
    public static String data2json(AbilityData a){
    	Gson g = new Gson();
    	return g.toJson(a);
    }
    public static AbilityData json2data(String json){
    	Gson g = new Gson();
    	return g.fromJson(json, AbilityData.class);
    }
    /**
     * 接下来就是鸡冻人心的读取和存储分了，啪啪啪
    */
    public static void saveAbility(EntityPlayer p,AbilityData a) throws IOException{
    	String s = getSavePath();
    	s += File.separator + "AbilityData"; //+ File.separator + p.getCommandSenderName() + ".ac";
    	System.out.println(s);
    	File f = new File(s);
    	if(!f.exists()){
    		f.mkdir();
    	}
    	s += File.separator + p.getCommandSenderName() + ".ac";
    	f = new File(s);
    	if(!f.exists()){
    		f.createNewFile();
    	}
    	FileWriter fw = new FileWriter(f);
    	fw.write(data2json(a));
    	fw.close();
    }
    public static AbilityData getAbility(EntityPlayer p) throws IOException{
    	String s = getSavePath();
    	s += File.separator + "AbilityData"; //+ File.separator + p.getCommandSenderName() + ".ac";
    	System.out.println(s);
    	File f = new File(s);
    	if(!f.exists()){
    		return new AbilityData(p,(byte)0,(byte)0,0,new boolean[3],new float[3]);
    	}
    	s += File.separator + p.getCommandSenderName() + ".ac";
    	f = new File(s);
    	if(!f.exists()){
    		return new AbilityData(p,(byte)0,(byte)0,0,new boolean[3],new float[3]);
    	}
    	FileReader fr = new FileReader(f);
    	char[] temp = new char[1024];
    	String json = new String(temp,0,fr.read(temp));
    	fr.close();
    	return json2data(json);
    }
	/**
	 * 用于定位世界的保存路径
	*/
	public static String getSavePath(){
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
