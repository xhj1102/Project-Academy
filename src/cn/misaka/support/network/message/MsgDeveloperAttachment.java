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
package cn.misaka.support.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cn.misaka.support.block.tile.TileAbilityDeveloper;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * 开发机的附件消息更新。
 * updateFlag：位1 是否更新sided 位2 是否更新plain
 * @author WeAthFolD
 */
public class MsgDeveloperAttachment implements IMessage {
	
	TileAbilityDeveloper tile;
	int updateFlag;
	
	int[] sidedAttaches;
	int x, y, z;

	/**
	 * 
	 */
	public MsgDeveloperAttachment(TileAbilityDeveloper dev, int flag) {
		tile = dev;
		updateFlag = flag;
		sidedAttaches = tile.sidedModules;
	}
	
	public MsgDeveloperAttachment() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		updateFlag = buf.readByte();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		if((updateFlag & 0x01) != 0) {
			sidedAttaches = new int[4];
			for(int i = 0; i < 4; i++)
				sidedAttaches[i] = buf.readByte();
		}
		
		if(updateFlag >> 1 != 0) {
			
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(updateFlag);
		buf.writeInt(tile.xCoord);
		buf.writeInt(tile.yCoord);
		buf.writeInt(tile.zCoord);
		
		if((updateFlag & 0x01) != 0) {
			for(int i = 0; i < 4; i++) {
				buf.writeByte(sidedAttaches[i]);
			}
		}
		
		if(updateFlag >> 1 != 0) {
			//Placeholder for futher update
		}
	}
	
	public static class Handler implements IMessageHandler< MsgDeveloperAttachment, IMessage > {

		@Override
		public IMessage onMessage(MsgDeveloperAttachment msg, MessageContext ctx) {
			World world = Minecraft.getMinecraft().theWorld;
			TileEntity te = world.getTileEntity(msg.x, msg.y, msg.z);
			if(te != null && te instanceof TileAbilityDeveloper) {
				TileAbilityDeveloper dev = (TileAbilityDeveloper) te;
				
				if((msg.updateFlag & 0x01) != 0) {
					System.out.println("Module setted");
					dev.sidedModules = msg.sidedAttaches;
				}
				
				if(msg.updateFlag >> 1 != 0) {
					
				}
			}
			return null;
		}
		
	}

}
