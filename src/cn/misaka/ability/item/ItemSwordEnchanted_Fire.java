/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.item;

import cn.liutils.api.util.GenericUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;

/**
 * @author WeAthFolD
 *
 */
public class ItemSwordEnchanted_Fire extends ItemSwordEnchanted {

	public static ItemSwordEnchanted_Fire[] items = new ItemSwordEnchanted_Fire[5];
	
	/**
	 * @param par1
	 * @param mat
	 */
	public ItemSwordEnchanted_Fire(int par1, EnumToolMaterial mat) {
		super(par1, mat);
		items[mat.ordinal()] = this;
	}
	
	public static void createEnchantedSword(ItemStack sword, EntityPlayer player, int level) {
		for(int i = 0; i < items.length; i++) {
			if(items[i].originID == sword.itemID) {
				sword.itemID = items[i].itemID;
				GenericUtils.loadCompound(sword).setByte("enchLevel", (byte) level);
				break;
			}
		}
	}
	
    public float func_82803_g()
    {
    	return 0.0F;
    }
	
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase ent1, EntityLivingBase player)
    {
    	int lev = GenericUtils.loadCompound(stack).getByte("enchLevel");
    	float addDamage = 2 * lev;
    	ent1.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0F + addDamage + this.theMaterial.getDamageVsEntity());
        stack.damageItem(1, player);
        return true;
    }
    
    public static void restoreItem(ItemStack stack) {
    	for(int i = 0; i < items.length; i++) {
    		if(stack.itemID == items[i].itemID) {
    			stack.itemID = items[i].theItem.itemID;
    			break;
    		}
    	}
    }

}
