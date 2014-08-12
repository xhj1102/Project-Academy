package cn.misaka.ability.system.item;

import java.util.Random;

import cn.liutils.api.util.Motion3D;
import cn.misaka.ability.api.enchant.APEnchantType;
import cn.misaka.ability.api.enchant.APEnchantment;
import cn.misaka.ability.api.enchant.PlayerEnchantStatus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEnchantedOther extends Item {
	
	private static final Random rand = new Random();
	
	public ItemEnchantedOther() {
		//空空的~
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack)
    {
    	Item item = Item.getItemById(stack.stackTagCompound.getInteger("preID"));
    	return item == null ? null : item.getIconIndex(stack);
    }
    
	/**
	 * 攻击加强
	 */
	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase attackedEntity, 
    		EntityLivingBase player2) {
		if(!(player2 instanceof EntityPlayer)) return false;
		EntityPlayer player = (EntityPlayer) player2;
		PlayerEnchantStatus stat = APEnchantment.loadEnchantStatus(player);
		ItemSword item = (ItemSword) stat.itemBeforeEnchant.getItem();
		APEnchantType ench = stat.getEnchantment();
		float tooldamage = 1F + ench.damage;
		attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(player), 
				tooldamage);
		if(ench.repel != 0) { //击退
			double factor = ench.repel;
			Motion3D motion = new Motion3D(player, true);
			attackedEntity.motionX += motion.motionX * factor;
			attackedEntity.motionY += motion.motionY * factor;
			attackedEntity.motionZ += motion.motionZ * factor;
		}
		return true;
	}
	
    /**
     * 异常检测处理
     */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    	if(!par5)APEnchantment.dechangeItemFromPlayer((EntityPlayer) par3Entity);
    }
}
