/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.core.item;

import cn.liutils.api.util.GenericUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;

/**
 * @author WeAthFolD
 *
 */
public class APElectItemManager implements IElectricItemManager {
	
	public static final APElectItemManager INSTANCE = new APElectItemManager();

	@Override
	public int discharge(ItemStack itemStack, int amount, int tier,
			boolean ignoreTransferLimit, boolean simulate) {
		int en = getCharge(itemStack);
		int amt = GenericUtils.mini(amount, en, ignoreTransferLimit ? 100000 : getTransLimit(itemStack));
		if(!simulate) itemStack.setItemDamage(itemStack.getItemDamage() + amt);
		return amt;
	}

	@Override
	public int charge(ItemStack itemStack, int amount, int tier, boolean ignoreTransferLimit, boolean simulate) {
		int en = itemStack.getItemDamage();
		int amt = GenericUtils.mini(amount, en, ignoreTransferLimit ? 100000 : getTransLimit(itemStack));
		if(!simulate) itemStack.setItemDamage(en - amt);
		return amt;
	}
	
	public APElectItemManager() {
	}
	
	private int getMaxCharge(ItemStack stack) {
		ISpecialElectricItem ic = (ISpecialElectricItem) stack.getItem();
		return ic.getMaxCharge(stack);
	}
	
	private int getTransLimit(ItemStack stack) {
		ISpecialElectricItem ic = (ISpecialElectricItem) stack.getItem();
		return ic.getTransferLimit(stack);
	}
	
	private ISpecialElectricItem getItem(ItemStack stack) {
		return (ISpecialElectricItem) stack.getItem();
	}


	/* (non-Javadoc)
	 * @see ic2.api.item.IElectricItemManager#getCharge(net.minecraft.item.ItemStack)
	 */
	@Override
	public int getCharge(ItemStack itemStack) {
		return itemStack.getMaxDamage() - itemStack.getItemDamage();
	}

	@Override
	public boolean canUse(ItemStack itemStack, int amount) {
		return amount <= getCharge(itemStack);
	}

	/* (non-Javadoc)
	 * @see ic2.api.item.IElectricItemManager#use(net.minecraft.item.ItemStack, int, net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public boolean use(ItemStack itemStack, int amount, EntityLivingBase entity) {
		return amount <= getCharge(itemStack);
	}

	/* (non-Javadoc)
	 * @see ic2.api.item.IElectricItemManager#chargeFromArmor(net.minecraft.item.ItemStack, net.minecraft.entity.EntityLivingBase)
	 */
	@Override
	public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entity) {
	}

	/* (non-Javadoc)
	 * @see ic2.api.item.IElectricItemManager#getToolTip(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getToolTip(ItemStack itemStack) {
		return "" + getCharge(itemStack) + "/" + getMaxCharge(itemStack) + " EU";
	}
}
