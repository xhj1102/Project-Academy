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
import cn.misaka.ability.system.control.APControlMain;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * @author WeAthFolD
 */
public class MsgControl implements IMessage {

	public int ability_id;
	public int key_id;
	public boolean downOrUp;
	
	public MsgControl(int ability, int key, boolean down) {
		ability_id = ability;
		key_id = key;
		downOrUp = down;
	}
	
	public MsgControl() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		ability_id = buf.readByte();
		key_id = buf.readByte();
		downOrUp = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(ability_id);
		buf.writeByte(key_id);
		buf.writeBoolean(downOrUp);
	}
	
	public static class Handler implements IMessageHandler<MsgControl, IMessage> {

		@Override
		public IMessage onMessage(MsgControl message, MessageContext ctx) {
			System.out.println("Retrived controlChange message from client");
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			APControlMain.onSkillKeyChanged(player, message.ability_id, message.key_id, message.downOrUp);
			return null;
		}
		
	}

}
