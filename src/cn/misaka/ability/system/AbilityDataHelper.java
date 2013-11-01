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
	
	private static final String PREFIX = "am_";
	
	private static void setBoolean(NBTTagCompound tag, String str, boolean b) {
		tag.setBoolean(PREFIX + str, b);
	}
	
	private static boolean getBoolean(NBTTagCompound tag, String str) {
		return tag.getBoolean(PREFIX + str);
	}
	
	private static void setInteger(NBTTagCompound tag, String str, int i) {
		tag.setInteger(PREFIX + str, i);
	}

}
