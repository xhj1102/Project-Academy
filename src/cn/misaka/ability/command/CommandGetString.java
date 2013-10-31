/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

/**
 * @author WeAthFolD
 *
 */
public class CommandGetString extends CommandBase {

	/**
	 * 
	 */
	public CommandGetString() {
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "getstring";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandUsage(net.minecraft.command.ICommandSender)
	 */
	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/getstring [str]";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] strs) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		if(player != null) {
			String s = player.getEntityData().getString(strs[0]);
			player.sendChatToPlayer(ChatMessageComponent.createFromText(s));
		}
	}

}
