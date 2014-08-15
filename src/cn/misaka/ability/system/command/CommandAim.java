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
package cn.misaka.ability.system.command;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;

import cn.liutils.api.command.LICommandBase;
import cn.liutils.api.util.Pair;
import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.data.ModifierInitialize;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.network.message.MsgSyncToClient;
import cn.misaka.core.AcademyCraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * 反射大法好
 * @author WeAthFolD
 */
public class CommandAim extends LICommandBase {
	
	private static HashMap<String, DataType> fldMap = new HashMap<String, DataType>();
	
	private static class DataType extends Pair<Field, Boolean> {

		public DataType(Field k, Boolean v) {
			super(k, v);
		}
		
	}
	
	static {
		try {
			fldMap.put("catid", new DataType(PlayerData.class.getDeclaredField("catid"), true));
			fldMap.put("level", new DataType(PlayerData.class.getDeclaredField("level"), true));
			fldMap.put("cp", new DataType(PlayerData.class.getField("max_cp"), false));
			fldMap.put("current_cp", new DataType(PlayerData.class.getField("current_cp"), false));
		} catch (Exception e) {
			
		}
	}

	/**
	 * 
	 */
	public CommandAim() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "aim";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandUsage(net.minecraft.command.ICommandSender)
	 */
	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/aim [set][status][develop][view]";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] args) {
		EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(ics);
		if(args.length == 0) {
			sendChat(ics, "Not enough arguments");
		} else {
			if(args[0].equals("set")) {
				if(args.length != 3) {
					sendChat(ics, "Illegal argument size, Usage: " + getSetUsage());
				} else {
					if(!fldMap.containsKey(args[1])) {
						sendChat(ics, "Didn't find the corresponding field.");
					} else {
						if(player != null) {
							PlayerData data = APDataMain.loadPlayerData(player);
							try {
								DataType dt = fldMap.get(args[1]);
								Field field = dt.first;
								field.setAccessible(true);
								field.set(data, Integer.valueOf(args[2]));
								if(dt.second)
									data.onStateChanged();
								AcademyCraft.netHandler.sendTo(new MsgSyncToClient(data, 0x01), player);
								sendChat(ics, "setting successful");
							} catch (NumberFormatException e) {
								sendChat(ics, "argument is not a number");
							} catch (Exception e) {
								sendChat(ics, "Failed to either fetch the player data or set the field");
							}
						}
					}
 				}
			} else if(args[0].equals("status")) {
				
			} else if(args[0].equals("develop")) {
				//TODO: Invoke Ability Dev functions
				PlayerData data = APDataMain.loadPlayerData(player);
				new ModifierInitialize().applyModification(player, data);
				AcademyCraft.netHandler.sendTo(new MsgSyncToClient(data, 0x01), player);
				
			} else if(args[0].equals("view")) {
				PlayerData data = APDataMain.loadPlayerData(player);
				try {
					for(Entry<String, DataType> entry : fldMap.entrySet()) {
						DataType dt = entry.getValue();
						System.out.println("Fetching " + dt.toString());
						dt.first.setAccessible(true);
						this.sendChat(ics, entry.getKey() + ": " + entry.getValue().first.get(data));
					}
				} catch(Exception e) {}
			} else {
				
			}
		}
	}
	
	private void showStatus(ICommandSender ics) {
		EntityPlayer player = CommandBase.getCommandSenderAsPlayer(ics);
		//TODO
	}
	
	private String getSetUsage() {
		return "/aim set [key] [value]";
	}

}
