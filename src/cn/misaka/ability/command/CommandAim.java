/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.command;

import cn.liutils.api.command.LICommandBase;
import cn.misaka.ability.system.AbilityDataHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
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
		return "/aim, /aim activate, /aim deactivate";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] strs) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		if(player == null) return;
		
		if(strs.length == 0) {
			sendAbilityInformation(player);
		} else if(strs[0].equals("activate")) {
			activateAbility(player);
		} else if(strs[0].equals("deactivate")) { 
			deactivateAbility(player);
		} else sendWrongMessage(player);
	}
	
	private void activateAbility(EntityPlayer player) {
		AbilityDataHelper.setHasAbility(player, true);
		sendChatToPlayer(player, EnumChatFormatting.GREEN + "aca.ability.activated.name");
	}
	
	private void deactivateAbility(EntityPlayer player) {
		AbilityDataHelper.setHasAbility(player, false);
		sendChatToPlayer(player, EnumChatFormatting.RED + "aca.ability.deactivated.name");
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
