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
package cn.misaka.ability.system.network.message;

import cn.misaka.ability.block.tile.TileAbilityDeveloper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author WeAthFolD
 */
public class MsgDeveloperDismount implements IMessage {
	


	public MsgDeveloperDismount() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class Handler implements IMessageHandler<MsgDeveloperDismount, IMessage> {

		@Override
		public IMessage onMessage(MsgDeveloperDismount message,
				MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			System.out.println("Dismounting sync");
			TileEntity tile = player.worldObj.getTileEntity(MathHelper.floor_double(player.posX),
					MathHelper.floor_double(player.posY),
					MathHelper.floor_double(player.posZ));
			if(tile != null && tile instanceof TileAbilityDeveloper) {
				((TileAbilityDeveloper)tile).disMount();
			} else
				System.err.println("Didn't find server instance while trying to dismount AbilityDeveloper");
			return null;
		}
		
	}

}
