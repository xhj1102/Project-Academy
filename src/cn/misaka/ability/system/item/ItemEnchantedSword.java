package cn.misaka.ability.system.item;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.ability.api.enchant.APEnchantType;
import cn.misaka.ability.api.enchant.APEnchantment;
import cn.misaka.ability.api.enchant.PlayerEnchantStatus;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEnchantedSword extends ItemSword {

	public final static Random rand = new Random();
	
	/**
	 * 构造器
	 * @param material
	 * 			工具材质
	 * @param enchant1
	 * 			附魔信息
	 * @param item1
	 * 			原物品
	 */
	public ItemEnchantedSword(ToolMaterial material) {
		super(material);
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack)
    {
    	Item item = Item.getItemById(stack.stackTagCompound.getInteger("preID"));
    	return item == null ? null : item.getIconIndex(stack);
    }
	
	/**
	 * 攻击加强&耐久加强
	 */
	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase attackedEntity, 
    		EntityLivingBase player2) {
		if(!(player2 instanceof EntityPlayer)) return false;
		EntityPlayer player = (EntityPlayer) player2;
		PlayerEnchantStatus stat = APEnchantment.loadEnchantStatus(player);
		ItemSword item = (ItemSword) stat.itemBeforeEnchant.getItem();
		APEnchantType ench = stat.getEnchantment();
		float sworddamage = item.func_150931_i() + ench.damage;
		attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(player), 
			sworddamage);
		damageItemRandom(par1ItemStack, ench, player); //耐久加强
		return true;
	}
	
	/**
	 * 方块破坏
	 */
	@Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase player2)
    {
		if(!(player2 instanceof EntityPlayer)) return false;
		EntityPlayer player = (EntityPlayer) player2;
        if (p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
        {
            damageItemRandom(p_150894_1_, APEnchantment.loadEnchantStatus(player).getEnchantment(), player);
        }
        return true;
    }
	
	/**
	 * 获得原物品图标
	 */
    @Override
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
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
