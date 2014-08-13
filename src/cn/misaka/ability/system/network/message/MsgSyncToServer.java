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

import net.minecraft.entity.player.EntityPlayer;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.data.APDataMain;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * 由客户端发送的信息同步。其实就是isActivated
 * @author WeAthFolD
 *
 */
public class MsgSyncToServer implements IMessage {
	
	public boolean isActivated;

	public MsgSyncToServer(PlayerData data) {
		isActivated = data.isActivated;
	}
	
	public MsgSyncToServer() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		isActivated = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isActivated);
	}
	
	public static class Handler implements IMessageHandler<MsgSyncToServer, IMessage> {

		@Override
		public IMessage onMessage(MsgSyncToServer message,
				MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			PlayerData data = APDataMain.loadPlayerData(player);
			data.isActivated = message.isActivated;
			System.out.println("Activation change server" + data.isActivated);
			return null;
		}
		
	}

}
