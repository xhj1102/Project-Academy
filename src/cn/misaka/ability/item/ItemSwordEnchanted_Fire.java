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
import net.minecraft.nbt.NBTTagCompound;
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
	public ItemSwordEnchanted_Fire(int par1) {
		super(par1);
	}
	
	public void createEnchantedSword(ItemStack sword, EntityPlayer player, int level) {
		toEnchantedItem(sword);
		System.out.println("Creating enchanted sword in " + player.worldObj.isRemote);
		GenericUtils.loadCompound(sword).setByte("enchLevel", (byte) level);
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
    	NBTTagCompound tag = GenericUtils.loadCompound(stack);
    	int lev = tag.getByte("enchLevel");
    	float addDamage = 2 * lev;
    	
    	ent1.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0F + addDamage + tag.getFloat("damage"));
        stack.damageItem(1 + (lev == 1 ? 1 : (this.getOriginID(stack) == Item.swordStone.itemID ? 3 : 1)), player);
        return true;
    }

}
