package cn.misaka.ability.api.enchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.liutils.api.util.GenericUtils;
import cn.misaka.core.register.APItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class APEnchantment {
	
	/**
	 * 玩家的当前附魔数据表，分为client和server两边。
	 */
	private static Map<EntityPlayer, PlayerEnchantStatus>
		statMap_client = new HashMap(),
		statMap_server = new HashMap();
	
	/**
	 * 这里存放了所有Enchant实例，任何新的type都要在这里进行注册
	 */
	protected static List<APEnchantType> typeList = new ArrayList();
	
	/**
	 * 用于替换玩家使用的武器等
	 * @param player 
	 * 			玩家
	 */
	public static void changeItemFromPlayer(EntityPlayer player) {
		PlayerEnchantStatus stat = loadEnchantStatus(player);
		if(stat.isEnchanting)
			dechangeItemFromPlayer(player);
		ItemStack curStack = player.getCurrentEquippedItem();
		//检查当前物品是否能被附魔
		if(curStack == null) {
			System.err.println("Trying to timed-enchant a null item, process aborting.");
			return;
		}
		
		stat.itemBeforeEnchant = curStack; //设置原物品 
		stat.enchatSlotID = player.inventory.currentItem; //设置slot
		
		//创建新的替换物品并设置属性
		ItemStack is2 = new ItemStack(getEnchantItem(curStack.getItem()), 1, curStack.getItemDamage());
		is2.stackTagCompound = curStack.stackTagCompound;
		if(curStack.stackTagCompound == null) is2.stackTagCompound = new NBTTagCompound();
		else is2.stackTagCompound = (NBTTagCompound) curStack.stackTagCompound.copy();
		is2.stackTagCompound.setInteger("preID", Item.getIdFromItem(curStack.getItem())); //设置preID以供enchantItem类调用
		player.inventory.setInventorySlotContents(player.inventory.currentItem, is2);
		stat.isEnchanting = true;
	}
	
	private static Item getEnchantItem(Item item0) {
		if(item0 instanceof ItemSword) {
			return APItems.enchantedSword[ToolMaterial.valueOf(((ItemSword)item0).getToolMaterialName()).ordinal()];
		} else {
			if(item0 instanceof ItemTool) {
				return APItems.enchantedTool[ToolMaterial.valueOf(((ItemTool)item0).getToolMaterialName()).ordinal()];
			} else {
				return APItems.enchantedOther;
			}
		}
	}
	
	/**
	 * 用户还原玩家使用的武器等
	 * @param player
	 * 			玩家
	 */
	public static void dechangeItemFromPlayer(EntityPlayer player) {
		PlayerEnchantStatus stat = loadEnchantStatus(player);
		player.inventory.setInventorySlotContents(stat.enchatSlotID, stat.itemBeforeEnchant);
		stat.isEnchanting = false;
		stat.itemBeforeEnchant = null; //设为null防止误用
	}
	
	private static Map<EntityPlayer, PlayerEnchantStatus> getStatusMap(World wrld) {
		return getStatusMap(wrld.isRemote);
	}
	
	private static Map<EntityPlayer, PlayerEnchantStatus> getStatusMap(boolean isRemote) {
		return isRemote ? statMap_client : statMap_server;
	}
	
	/**
	 * 获取玩家的临时附魔信息
	 * @param player
	 * @return
	 */
	public static PlayerEnchantStatus loadEnchantStatus(EntityPlayer player) {
		Map<EntityPlayer, PlayerEnchantStatus> statMap = getStatusMap(player.worldObj);
		PlayerEnchantStatus stat = statMap.get(player);
		if(stat == null) {
			stat = new PlayerEnchantStatus();
			statMap.put(player, stat);
		}
		return stat;
	}
	
	/**
	 * 注册一个附魔类型。如果注册成功，返回该类型的下标，否则返回-1。
	 * @param type
	 * @return
	 */
	public static int registerEnchantment(APEnchantType type) {
		if(typeList.add(type)) return typeList.size() - 1;
		return -1;
	}
	
	public static APEnchantType getEnchantment(int id) {
		return GenericUtils.safeFetchFrom(typeList, id);
	}
	
	
}
