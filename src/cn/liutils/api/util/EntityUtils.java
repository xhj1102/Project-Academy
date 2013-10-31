/**
 * Code by Lambda Innovation, 2013.
 */
package cn.liutils.api.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author WeAthFolD
 *
 */
public class EntityUtils {

	public static int getItemSlotByStack(ItemStack item, EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.mainInventory.length; i++) {
			ItemStack is = inv.mainInventory[i];
			if(is != null && item == is)
				return i;
		}
		return -1;
	}

}
