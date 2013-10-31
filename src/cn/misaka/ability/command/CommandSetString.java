/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WeAthFolD
 *
 */
public class CommandSetString extends CommandBase {
	
	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "setstring";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandUsage(net.minecraft.command.ICommandSender)
	 */
	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/setstring [str] [str]";
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender ics, String[] strs) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		if(player != null) {
			player.getEntityData().setString(strs[0], strs[1]);
		}
	}

}
