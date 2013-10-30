package cn.liutils.core.client.register;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import cn.liutils.api.debug.Debug_MovingProcessor;
import cn.liutils.api.debug.KeyMoving;
import cn.liutils.api.debug.command.Command_SetMode;
import cn.liutils.core.LIUtilsMod;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * TODO:UNFINISHED
 * @author Administrator
 *
 */
public class LIClientTickHandler implements ITickHandler {

	public LIClientTickHandler() {
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		if(LIUtilsMod.DEBUG)
			if(player != null) 
				doDebug(player);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "LIUtils Client Tick Handler";
	}
	
	private void doDebug(EntityPlayer player) {
		ItemStack item = player.getCurrentEquippedItem();
		if(item != null) {
			IItemRenderer render = MinecraftForgeClient.getItemRenderer(item, ItemRenderType.EQUIPPED_FIRST_PERSON);
			if(render != null) {
				Debug_MovingProcessor proc = KeyMoving.getProcessor(render);
				Command_SetMode.setActiveProcessor(player, proc);
			} else Command_SetMode.setActiveProcessor(player, null);
		} else Command_SetMode.setActiveProcessor(player, null);
	}

}
