package cn.misaka.ability.system.item;

import java.util.Random;
import cn.misaka.ability.api.enchant.APEnchantType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemEnchantedTool extends ItemTool {
	
	public APEnchantType enchant;
	public ItemStack item;
	public static float tooldamage;
	private static final Random rand = new Random();

	public ItemEnchantedTool(ToolMaterial material, APEnchantType enchant1, ItemStack item1) {
		super(tooldamage, material, null);
		enchant = enchant1;
		item = item1;
	}
	
	/**
	 * 攻击加强&耐久加强
	 */
	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase attackedEntity, 
    	EntityLivingBase player) {
		ItemTool item1 = null;//(ItemTool) APEnchantment.itemForJudge.getItem();
		tooldamage = (float) ((AttributeModifier) item1.getItemAttributeModifiers()
				.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()))
				.getAmount() + enchant.damage;
		attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(player), 
				tooldamage);
		damageItemRandom(par1ItemStack, enchant, player); //耐久加强
		return true;
	}
	
	/**
	 * 方块破坏
	 */
	@Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        if (p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            damageItemRandom(p_150894_1_, enchant, p_150894_7_);
        }
        return true;
    }
	
	/**
	 * 获得原物品图标
	 */
    @Override
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = item.getIconIndex();
	}
    
    /**
     * 实现耐久增强
     */
    public void damageItemRandom (ItemStack item, APEnchantType enchant, 
    		EntityLivingBase player) {
    	if (enchant.endure > 0F) {
    		if (rand.nextFloat() < enchant.endure) {
    			item.damageItem(1, player);
    		}
    	} else {
    		if (enchant.endure == 0F) {
    			item.damageItem(1, player);
    		}
    	}
    }
}
