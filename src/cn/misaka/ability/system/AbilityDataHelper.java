/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author WeAthFolD
 *
 */
public class AbilityDataHelper {
	
	public static boolean playerHasAbility(EntityPlayer player) {
		return playerHasAbility(player.getEntityData());
	}
	
	public static void setHasAbility(EntityPlayer player, boolean b) {
		setHasAbility(player.getEntityData(), b);
	}
	
	public static boolean playerHasAbility(NBTTagCompound nbt) {
		return getBoolean(nbt, "activated");
	}
	
	public static void setHasAbility(NBTTagCompound nbt, boolean b) {
		setBoolean(nbt, "activated", b);
	}
	
	public static int getLevel(NBTTagCompound nbt) {
		return getByte(nbt, "level");
	}
	
	public static void setLevel(NBTTagCompound nbt, int level) {
		setByte(nbt, "level", level);
	}
	
	public static int getType(NBTTagCompound nbt) {
		return getByte(nbt, "type");
	}
	
	public static void setType(NBTTagCompound nbt, int type) {
		setByte(nbt, "type", type);
	}
	
	
	public static int getCalcPoint(NBTTagCompound nbt) {
		return getInteger(nbt, "calcPoint");
	}
	
	public static void setCalcPoint(NBTTagCompound nbt, int i) {
		setInteger(nbt, "calcPoint", i);
	}
	
	private static final String PREFIX = "am_";
	
	private static void setBoolean(NBTTagCompound tag, String str, boolean b) {
		tag.setBoolean(PREFIX + str, b);
	}
	
	private static boolean getBoolean(NBTTagCompound tag, String str) {
		return tag.getBoolean(PREFIX + str);
	}
	
	private static int getInteger(NBTTagCompound tag, String str) {
		return tag.getInteger(PREFIX + str);
	}
	
	private static void setInteger(NBTTagCompound tag, String str, int i) {
		tag.setInteger(PREFIX + str, i);
	}
	
	private static int getShort(NBTTagCompound tag, String str) {
		return tag.getShort(PREFIX + str);
	}
	
	private static void setShort(NBTTagCompound tag, String str, int i) {
		tag.setShort(PREFIX + str, (short) i);
	}
	
	private static int getByte(NBTTagCompound tag, String str) {
		return tag.getByte(PREFIX + str);
	}
	
	private static void setByte(NBTTagCompound tag, String str, int i) {
		tag.setByte(PREFIX + str, (byte) i);
	}

}
