/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.item;

import cn.liutils.api.util.GenericUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * 有攻击力和特殊效果加成的剑。被用在诸如火系高温攻击力提升等场合。
 * @see ItemEnchantingUtils
 * @author WeAthFolD
 *
 */
public abstract class ItemSwordEnchanted extends ItemSword {

	/**
	 * @param par1
	 * @param mat
	 */
	public ItemSwordEnchanted(int par1) {
		super(par1, EnumToolMaterial.WOOD);
	}
	
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
    	EntityPlayer player = (EntityPlayer) entity;
    	if(!par5)
    		restore(stack);
    }
    
    public void toEnchantedItem(ItemStack stack) {
    	if(stack.getItem() instanceof ItemSword) {
    		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
    		ItemSword sword = (ItemSword) stack.getItem();
    		stack.itemID = this.itemID;
    		nbt.setInteger("originID", sword.itemID);
    		nbt.setFloat("damage", sword.func_82803_g());
    	}
    }
    
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase ent1, EntityLivingBase player)
    {
    	NBTTagCompound tag = GenericUtils.loadCompound(stack);
    	ent1.attackEntityFrom(DamageSource.causeMobDamage(player), 4.0F + tag.getFloat("damage"));
        stack.damageItem(1, player);
        return true;
    }
     
    public void restore(ItemStack stack) {
    	if(stack.itemID == this.itemID) {
    		stack.itemID = GenericUtils.loadCompound(stack).getInteger("originID");
    	}
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns the icon index of the stack given as argument.
     */
    public Icon getIconIndex(ItemStack stack)
    {
    	Item item = Item.itemsList[GenericUtils.loadCompound(stack).getInteger("originID")];
    	return item == null ? null : item.getIconFromDamage(0);
    }
    
    protected int getOriginID(ItemStack stack) {
    	return GenericUtils.loadCompound(stack).getInteger("originID");
    }


}
