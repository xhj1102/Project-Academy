/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * 有攻击力和特殊效果加成的剑。被用在诸如火系高温攻击力提升等场合。
 * @see ItemEnchantingUtils
 * @author WeAthFolD
 *
 */
public abstract class ItemSwordEnchanted extends ItemSword {
	
	int originID;
	public final EnumToolMaterial theMaterial;
	public final ItemSword theItem;

	/**
	 * @param par1
	 * @param mat
	 */
	public ItemSwordEnchanted(int par1, EnumToolMaterial mat) {
		super(par1, mat);
		theMaterial = mat;
		switch(mat) {
		case EMERALD:
			originID = Item.swordDiamond.itemID;
			break;
		case GOLD:
			originID = Item.swordGold.itemID;
			break;
		case IRON:
			originID = Item.swordIron.itemID;
			break;
		case STONE:
			originID = Item.swordStone.itemID;
			break;
		case WOOD:
			originID = Item.swordWood.itemID;
			break;
		default:
			break;
		}
		theItem = (ItemSword) Item.itemsList[originID];
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
    
    public void restore(ItemStack stack) {
    	if(stack.itemID == this.itemID)
    		stack.itemID = originID;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns the icon index of the stack given as argument.
     */
    public Icon getIconIndex(ItemStack stack)
    {
    	return theItem.getIconIndex(stack);
    }


}
