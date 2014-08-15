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

import cn.misaka.ability.api.APDataMain;
import cn.misaka.ability.api.data.PlayerData;
import cn.misaka.ability.system.data.PlayerDataUpdater;
import cn.misaka.core.AcademyCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * flag byte 0:Generic byte 1:skill information
 * 位可以叠加。
 * @author WeAthFolD
 *
 */
public class MsgSyncToClient implements IMessage {
	
	public PlayerDataUpdater updater;
	public int flag;

	/**
	 * 
	 */
	public MsgSyncToClient(PlayerData data, int flag) {
		updater = data.toUpdater();
		this.flag = flag;
	}
	
	public MsgSyncToClient() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		flag = buf.readByte();
		updater = new PlayerDataUpdater();
		if((flag & 0x01) != 0) {
			updater.category = buf.readByte();
			updater.level = buf.readByte();
			updater.maxCP = buf.readInt();
			updater.currentCP = buf.readInt();
		}
		
		if((flag & 0x02) != 0) {
			int len = buf.readByte();
			updater.ac_skill_exp = new float[len];
			updater.ac_skill_open = new boolean[len];
			for(int i = 0; i < len; i++) {
				updater.ac_skill_open[i] = buf.readBoolean();
			}
			for(int i = 0; i < len; i++) {
				updater.ac_skill_exp[i] = buf.readFloat();
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if((flag& 0x02) != 0 && (updater.ac_skill_exp == null || updater.ac_skill_open == null)) {
			System.err.println("Trying to sync skill Infs while they are null, BUGGED!");
			flag -= 0x02;
		}
		buf.writeByte(flag);
		if((flag & 0x01) != 0) {
			buf.writeByte(updater.category);
			buf.writeByte(updater.level);
			buf.writeInt(updater.maxCP);
			buf.writeInt(updater.currentCP);
		}
		if((flag & 0x02) != 0) {
			int len = updater.ac_skill_exp.length;
			buf.writeByte(len);
			for(int i = 0; i < len; i++) 
				buf.writeBoolean(updater.ac_skill_open[i]);
			for(int i = 0; i < len; i++) 
				buf.writeFloat(updater.ac_skill_exp[i]);
		}
	}
	
	public static class Handler implements IMessageHandler<MsgSyncToClient, IMessage> {

		@Override
		public IMessage onMessage(MsgSyncToClient message, MessageContext ctx) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			APDataMain.loadPlayerData(player).fromAbilityData(message.updater, message.flag);
			return null;
		}
		
	}
	
	/**
	 * 从客户端发到服务端的同步请求。以服务端发包同步作为回应。
	 * @author WeAthFolD
	 */
	public static class Request implements IMessage {
		
		public byte flag;
		
		public Request(int flag) {
			this.flag = (byte) flag;
		}

		public Request() {}

		@Override
		public void fromBytes(ByteBuf buf) {
			flag = buf.readByte();
		}

		/* (non-Javadoc)
		 * @see cpw.mods.fml.common.network.simpleimpl.IMessage#toBytes(io.netty.buffer.ByteBuf)
		 */
		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeByte(flag);
		}
		
		public static class Handler implements IMessageHandler<Request, IMessage> {

			@Override
			public IMessage onMessage(Request message, MessageContext ctx) {
				System.out.println("Retrieved sync request");
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				AcademyCraft.netHandler.sendTo(new MsgSyncToClient(APDataMain.loadPlayerData(player), message.flag), player);
				return null;
			}
			
		}

	}


}
