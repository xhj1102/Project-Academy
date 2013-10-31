/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cn.liutils.api.entity.EntityVoid;
import cn.liutils.api.util.EntityUtils;
import cn.misaka.core.item.MisakaBaseItem;

/**
 * @author WeAthFolD
 *
 */
public class ItemAbilityVoid extends MisakaBaseItem {

	/**
	 * @param id
	 * @param aName
	 */
	public ItemAbilityVoid(int id) {
		super(id, "abilityVoid");
		setUnlocalizedName("abilityVoid");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean holding) {
		if(!holding) {
			EntityPlayer player = (EntityPlayer) entity;
			if(slot >= 0)
				player.inventory.mainInventory[slot] = null;
		}
	}
	
    @Override
    public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
    
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
        return new EntityVoid(world);
    }

}
