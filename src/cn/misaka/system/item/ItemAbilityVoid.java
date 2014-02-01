/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.item;

import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * TODO：编写中
 * @author WeAthFolD
 *
 */
public class ItemAbilityVoid extends Item {

	/**
	 * @param par1
	 */
	public ItemAbilityVoid(int par1) {
		super(par1);
	}
	
    public void onUpdate(ItemStack stack, World world, EntityPlayer player, int ha, boolean holding) {
    	if(!holding) stack.itemID = 0;
    	PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
    	if(!data.isActivated) stack.itemID = 0;
    }

}
