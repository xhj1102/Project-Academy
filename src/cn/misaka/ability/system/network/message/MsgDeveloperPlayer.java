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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * 从服务端发送给所有坐上AD的玩家的附近的玩家的客户端，并设定对应的nbt Tag
 * @author WeAthFolD
 */
public class MsgDeveloperPlayer implements IMessage {
	
	int playerID;
	boolean state;
	int dirFlag;

	public  MsgDeveloperPlayer(EntityPlayer player, boolean on, int flag) {
		playerID = player.getEntityId();
		state = on;
		dirFlag = flag;
	}
	
	public MsgDeveloperPlayer() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		playerID = buf.readInt();
		state = buf.readBoolean();
		dirFlag = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(playerID);
		buf.writeBoolean(state);
		buf.writeByte(dirFlag);
	}
	
	public static class Handler implements IMessageHandler< MsgDeveloperPlayer, IMessage > {

		@Override
		public IMessage onMessage(MsgDeveloperPlayer msg, MessageContext ctx) {
			Entity ent = Minecraft.getMinecraft().theWorld.getEntityByID(msg.playerID);
			if(ent == null || !(ent instanceof EntityPlayer))
				return null;
			//System.out.println("PRCESSING PLAYER SYNC");
			ent.getEntityData().setBoolean("ac_ondev", msg.state);
			ent.getEntityData().setByte("ac_devdir", (byte) msg.dirFlag);
			return null;
		}
		
	}

}
