package cn.misaka.ability.api.enchantment;

import cn.misaka.ability.system.item.ItemEnchantedSword;
import cn.misaka.ability.system.item.ItemEnchantedTool;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class APEnchantment {
	
	public static ItemStack itemForSave;
	public static ItemStack itemForChange;
	public static ItemStack itemForJudge;
	public static String itemType;
	
	/**
	 * 用于替换玩家使用的武器等
	 * @param player 
	 * 			玩家
	 */
	public void changeItemFromPlayer(EntityPlayer player) {
		itemForSave = player.getCurrentEquippedItem();
		player.inventory.setInventorySlotContents(player.inventory.currentItem, 
				itemForChange);
	}
	
	/**
	 * 用户还原玩家使用的武器等
	 * @param player
	 * 			玩家
	 */
	public void dechangeItemFromPlayer(EntityPlayer player) {
		player.inventory.setInventorySlotContents(player.inventory.currentItem, 
				itemForSave);
	}
	
	/**
	 * 用于生成一个默认镜像物品
	 * @param player
	 * 			玩家
	 */
	public void createItemStackForChange(EntityPlayer player, APEnchantType enchant) {
		itemForJudge = player.getCurrentEquippedItem();
		if (itemForJudge.getItem() instanceof ItemSword) {
			itemType = "sword";
			itemForChange = new ItemStack(new ItemEnchantedSword(checkToolMaterial(itemForJudge), enchant, itemForJudge)
			.setUnlocalizedName("ap_enchantedSword"));
		} else {
			if (itemForJudge.getItem() instanceof ItemTool) {
				itemType = "tool";
				itemForChange = new ItemStack(new ItemEnchantedTool(checkToolMaterial(itemForJudge), enchant, itemForJudge)
				.setUnlocalizedName("ap_enchantedTool"));
			}
		}
	}
	
	/**
	 * 判断物品材质
	 * @param item
	 * 			用于判断的物品
	 * @return
	 * 			返回物品的材质
	 */
	public ToolMaterial checkToolMaterial(ItemStack item) {
		ToolMaterial toolmaterial = null;
		if (itemType == "tool") {
			toolmaterial = ((ItemTool) itemForJudge.getItem()).func_150913_i();
		} else {
			if (itemType == "sword") {
				String strmaterial = ((ItemSword) itemForJudge.getItem()).getToolMaterialName();
				switch (strmaterial) {
				case "WOOD":
					toolmaterial = ToolMaterial.WOOD;
					break;
				case "STONE":
					toolmaterial = ToolMaterial.STONE;
					break;
				case "IRON":
					toolmaterial = ToolMaterial.IRON;
					break;
				case "GOLD":
					toolmaterial = ToolMaterial.GOLD;
					break;
				case "EMERALD":
					toolmaterial = ToolMaterial.EMERALD;
					break;
				}
			}
		}
		return toolmaterial;
	}
	
}
