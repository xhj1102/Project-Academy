package cn.misaka.support.item;

import java.util.List;

import cn.misaka.core.AcademyCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;

/*
 * @author Lyt99
 * 估计完坑要很长时间...	
 * 抄代码瞬间完成√
 */

public class ItemEnergyCrystal extends Item{
			
	protected IIcon[] textures;
	protected int maxCharge = 10000;

	public ItemEnergyCrystal(){
		setUnlocalizedName("ap_energycrystal");
		setCreativeTab(AcademyCraft.cct);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.textures = new IIcon[2];
		this.textures[0] = iconRegister.registerIcon("academy:energycrystal_empty");
		this.textures[1] = iconRegister.registerIcon("academy:energycrystal");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int charge){
		if(charge == 0)
			return this.textures[0];
		else
			return this.textures[1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack par1){
		return getIconFromDamage(getItemCharge(par1));
	}

	@Override
	public int getMaxDamage(){
		return this.maxCharge;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack){
		return maxCharge;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4){
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if(this.canShowInformation(par1ItemStack)){
			par3List.add(StatCollector.translateToLocal("gui.remaining.energy")+ ": " + getItemCharge(par1ItemStack) + "/" + this.maxCharge + " " + StatCollector.translateToLocal("gui.energy.unit"));
		}
		
	}

	protected boolean canShowInformation(ItemStack stack){
		return true;
	}
	protected int getItemCharge(ItemStack stack) {
		return loadCompound(stack).getInteger("charge");
	}
	
	private NBTTagCompound loadCompound(ItemStack stack) {
		if (stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound;
	}
		
}
