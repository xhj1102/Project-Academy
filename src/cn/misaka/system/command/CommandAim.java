/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.command;

import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.network.AbilityDataSender;
import cn.misaka.system.network.AbilityDataSender.EnumDataType;
import cn.misaka.system.proxy.CommonProxy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

/**
 * 用来进行自身能力调整的一系列指令。
 * @author WeAthFolD
 *
 */
public class CommandAim extends CommandBase {

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
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/aim sync|activate|develope|set";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] str) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
		EnumDataType syncType = EnumDataType.NONE;
		String message = null;
		if(player != null) {
			if(str[0].equals("sync")) {
				syncType = EnumDataType.FULL;
				message = "Successfully Synced from server.";
			} else if(str[0].equals("activate")) {
				syncType = EnumDataType.SIMPLE;
				data.isActivated = !data.isActivated;
				message = "Player Ability " + (data.isActivated ? "activated" : "deactivated");
			} else if(str[0].equals("develope")) {
				syncType = EnumDataType.SIMPLE;
				data.isDeveloped = !data.isDeveloped;
				message = "Player Developing Status : " + data.isDeveloped;
			} else if(str[0].equals("set")) {
				if(str.length < 3 || !Character.isDigit(str[2].charAt(0))) {
					message = EnumChatFormatting.RED + "Invaild input.";
				} else {
					setProperties(data, str[1].toLowerCase(), Integer.valueOf(str[2]));
					syncType = EnumDataType.FULL;
				}
				
			}
		} else {
			message = EnumChatFormatting.RED + "Invaild command, try again.";
		}
		if(message != null) msg(player, message);
		if(syncType != EnumDataType.NONE)
			AbilityDataSender.sendAbilityDataFromServer(data, syncType);
	}
	
	private void msg(EntityPlayer player, String msg) {
		player.sendChatToPlayer(ChatMessageComponent.createFromText(msg));
	}
	
	private void setProperties(PlayerAbilityData data, String key, int value) {
		if(key.equals("class")) {
			data.ability_class = value;
		} else if(key.equals("level")) {
			data.ability_level = value;
		}
	}
	

}
