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

import cn.liutils.api.command.LICommandBase;
import cn.misaka.ability.api.ability.AbilityClass;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.control.APControlMain;
import cn.misaka.ability.system.control.preset.ControlPreset;
import cn.misaka.ability.system.data.APDataMain;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WeAthFolD
 *
 */
public class CommandControlModification extends LICommandBase {

	public CommandControlModification() {}

	@Override
	public String getCommandName() {
		return "aimctrl";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/aimctrl {view} {set [presetID] [keyID] [skillID] [skillKeyID]} {preset [id]}";
	}
	
	@Override
	public void processCommand(ICommandSender ics, String[] var2) {
		EntityPlayer player = CommandBase.getCommandSenderAsPlayer(ics);
		if(var2.length == 0) {
			
			this.sendChat(ics, getCommandUsage(ics));
			
		} else if(var2.length == 1) {
			
			if(var2[0].equals("view")) {
				for(int i = 0; i < APControlMain.PRESETS; i++) {
					StringBuilder s2 = new StringBuilder("");
					ControlPreset preset = APControlMain.getPreset(i);
					for(int j = 0; j < APControlMain.KEYS; j++) 
						s2.append("<").append(preset.settings[j].toString()).append(j == APControlMain.KEYS - 1 ? ">" : ">, ");
					s2.append("\b\b");
					
					this.sendWithTranslation(ics, "aimctrl.presetinf", new Object[] {
						String.valueOf(i + 1),
						s2.toString()
					});
				} 
				
				this.sendWithTranslation(ics, "aimctrl.curpreset", new Object[] {
						String.valueOf(APControlMain.getCurrentPresetID())
				});
			} else  wrongMessage(ics);
			
		} else if(var2.length == 2) {
			
			if(var2[0].equals("preset")) {
				int id = Integer.valueOf(var2[1]);
				if(id < 0 || id >= APControlMain.PRESETS) {
					this.sendError(ics, "Invalid number or input is not a number.");
					return;
				}
				APControlMain.setPresetID(id);
				this.sendWithTranslation(ics, "setting.successful");
			} else wrongMessage(ics);
			
		} else if(var2.length == 5) {
			
			if(var2[0].equals("set")) {
				int presetID = Integer.valueOf(var2[1]),
					keyID = Integer.valueOf(var2[2]),
					skillID = Integer.valueOf(var2[3]),
					subKeyID = Integer.valueOf(var2[4]);
				PlayerData data = APDataMain.loadPlayerData(player);
				AbilityClass aclass = data.getAbilityClass();
				if(aclass == null) {
					this.sendWithTranslation(ics, "aimctrl.classinit");
					return;
				}
				if(0 <= presetID && presetID < APControlMain.PRESETS && 
						0 <= keyID && keyID < APControlMain.KEYS &&
						0 <= skillID && skillID < aclass.getMaxSkills()) {
					APControlMain.getPreset(presetID).settings[keyID].first = skillID;
					APControlMain.getPreset(presetID).settings[keyID].second = subKeyID;
				} else this.sendWithTranslation(ics, "setting.outofbounds");
			} else wrongMessage(ics);
			
		} else wrongMessage(ics);
	}
	
	private void wrongMessage(ICommandSender ics) {
		this.sendError(ics, "Illegal arguments.");
	}

}
