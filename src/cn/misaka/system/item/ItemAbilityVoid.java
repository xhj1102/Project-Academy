/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.item;

import cn.misaka.core.AcademyMod;
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
		this.setCreativeTab(AcademyMod.cct);
		this.setUnlocalizedName("ability_void");
		
	}
	
    public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean holding) {
    	EntityPlayer player = (EntityPlayer) ent;
    	PlayerAbilityData data = CommonProxy.abilityMain.getAbilityData(player);
    	if(holding) player.isSwingInProgress = false;
    	if(!holding || !data.canPlayerUseAbility()) player.inventory.setInventorySlotContents(slot, null);
    }

}
