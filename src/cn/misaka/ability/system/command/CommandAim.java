/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system.command;

import cn.liutils.api.command.LICommandBase;
import cn.misaka.ability.system.AbilityDataHelper;
import cn.misaka.ability.system.ServerAbilityMain;
import cn.misaka.ability.system.network.AbilityDataSyncer;
import cn.misaka.ability.system.network.AbilityDataSyncer.EnumDataType;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

/**
 * @author WeAthFolD
 *
 */
public class CommandAim extends LICommandBase {

	/**
	 * 
	 */
	public CommandAim() {
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
		return "/aim, /aim activate, /aim deactivate, /aim level [level], /aim class [classid]";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] strs) {
		EntityPlayer player = getCommandSenderAsPlayer(ics);
		EnumDataType typeToSend = null;
		if(player == null) return;
		
		if(strs.length == 0) {
			sendAbilityInformation(player);
		} else if(strs[0].equals("activate")) {
			activateAbility(player);
			typeToSend = EnumDataType.SIMPLE;
		} else if(strs[0].equals("deactivate")) { 
			deactivateAbility(player);
			typeToSend = EnumDataType.SIMPLE;
		} else if(strs[0].equals("level")) {
			if(strs.length == 2) {
				setAbilityLevel(player, Integer.valueOf(strs[1]));
				typeToSend = EnumDataType.FULL;
			} else sendWrongMessage(player);
		} else if(strs[0].equals("class")) {
			if(strs.length == 2) {
				setAbilityClass(player, Integer.valueOf(strs[1]));
				typeToSend = EnumDataType.FULL;
			} else sendWrongMessage(player);
		} else sendWrongMessage(player);
		
		if(typeToSend != null) {
			ServerAbilityMain.forceUpdate(player); //←当做强制更新时的范本吧。
			AbilityDataSyncer.sendPacketFromServer(player, typeToSend);
		}
	}
	
	private void activateAbility(EntityPlayer player) {
		AbilityDataHelper.setHasAbility(player, true);
		sendChatToPlayer(player, EnumChatFormatting.GREEN + 
				StatCollector.translateToLocal("aca.ability.activated.name"));
	}
	
	private void deactivateAbility(EntityPlayer player) {
		AbilityDataHelper.setHasAbility(player, false);
		sendChatToPlayer(player, EnumChatFormatting.RED + 
				StatCollector.translateToLocal("aca.ability.deactivated.name"));
	}
	
	private void setAbilityClass(EntityPlayer player, int type) {
		AbilityDataHelper.setType(player.getEntityData(), type);
	}
	
	private void setAbilityLevel(EntityPlayer player, int level) {
		AbilityDataHelper.setLevel(player.getEntityData(), level);
		sendChatToPlayer(player, StatCollector.translateToLocal("aca.ability.level.name")
				+ ": " + level);
	}
	
	private void sendAbilityInformation(EntityPlayer player) {
		StringBuilder sb = new StringBuilder("------------------------\n");
		sb.append(StatCollector.translateToLocal("aca.ability.isactivated.name")).append(" ").append(AbilityDataHelper.playerHasAbility(player) ? 
				EnumChatFormatting.GREEN + "yes" : EnumChatFormatting.RED + "no");
		sendChatToPlayer(player, sb.toString());
	}
	
	private void sendWrongMessage(EntityPlayer player) {
		this.sendChatToPlayer(player, EnumChatFormatting.RED + 
				StatCollector.translateToLocal("aca.command.wrongusage.name"));
	}

}
