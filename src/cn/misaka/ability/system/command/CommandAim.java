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

import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.data.APDataMain;
import cn.misaka.ability.system.network.message.MsgSyncToClient;
import cn.misaka.core.AcademyCraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

/**
 * @author WeAthFolD
 *
 */
public class CommandAim extends CommandBase {
	
	private static HashMap<String, Field> fldMap = new HashMap<String, Field>();
	
	static {
		try {
			fldMap.put("classid", PlayerData.class.getField("classid"));
			fldMap.put("level", PlayerData.class.getField("level"));
			fldMap.put("cp", PlayerData.class.getField("max_cp"));
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
		return "/aim [set][status][develop]";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] args) {
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
						EntityPlayerMP player = this.getCommandSenderAsPlayer(ics);
						if(player != null) {
							PlayerData data = APDataMain.loadPlayerData(player);
							try {
								fldMap.get(args[1]).set(data, Integer.valueOf(args[2]));
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
			}
		}
	}
	
	private void showStatus(ICommandSender ics) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		//TODO
	}
	
	private String getSetUsage() {
		return "/aim set [key] [value]";
	}
	
	private void sendChat(ICommandSender ics, String st) {
		ics.addChatMessage(new ChatComponentTranslation(st));
	}

}
