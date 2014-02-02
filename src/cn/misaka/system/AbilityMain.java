/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cn.liutils.api.util.GenericUtils;
import cn.misaka.ability.ability.test.AbilityClassTest;
import cn.misaka.core.AcademyMod;
import cn.misaka.system.ability.AbilityClass;
import cn.misaka.system.ability.AbilityLevel;
import cn.misaka.system.ability.AbilitySkill;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.network.AbilityDataSender;
import cn.misaka.system.network.AbilityDataSender.EnumDataType;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * @author WeAthFolD
 *
 */
public class AbilityMain implements ITickHandler {

	private static ArrayList<AbilityClass> classList = new ArrayList();
	static {
		//TODO:在这里添加所有的能力类。
		classList.add(new AbilityClassTest());
	}
	
	private Map<EntityPlayer, PlayerAbilityData> dataMap = new HashMap(); //玩家数据对应表。
	private Map<EntityPlayer, PlayerControlStat> ctrlMap = new HashMap(); //当前操作信息对应表。(FOR SERVER ONLY)
	
	private boolean isServerTicker = false;
	
	public AbilityMain() {}
	
	public PlayerAbilityData getAbilityData(EntityPlayer Player) {
		PlayerAbilityData data = dataMap.get(Player);
		if(data == null) {
			data = new PlayerAbilityData(Player);
			dataMap.put(Player, data);
			if(!data.isDataStateGood()) {
				AbilityDataSender.sendSyncRequestFromClient(EnumDataType.FULL);
				AbilityDataSender.sendSyncRequestFromClient(EnumDataType.CONTROL);
				return null;
			}
			return data;
		}
		return data.isDataStateGood() ? data : null;
	}
	
	public PlayerControlStat getControlStat(EntityPlayer Player) {
		PlayerControlStat stat = ctrlMap.get(Player);
		if(stat == null) {
			stat = new PlayerControlStat();
			ctrlMap.put(Player, stat);
		}
		return stat;
	}

	public AbilityClass getAbilityClass(EntityPlayer player) {
		PlayerAbilityData data = getAbilityData(player);
		if(data != null) 
			return GenericUtils.safeFetchFrom(classList, data.ability_class);
		
		return null;
	}
	
	public AbilityClass getAbilityClass(PlayerAbilityData data) {
		return GenericUtils.safeFetchFrom(classList, data.ability_class);
	}
	
	
	@Override
	/**
	 * 主要的tick处理函数。
	 */
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		World world = player.worldObj;
		PlayerAbilityData data = getAbilityData(player);
		if(!world.isRemote) isServerTicker = true; //用于判断是否在C/S集成服务器中运行
		if(!isServerTicker || !world.isRemote) { //如果是集成服务期，仅在S端调用
			if(data == null) {
				data = new PlayerAbilityData(player);
				dataMap.put(player, data);
			}
			data.updateTick();
		}
		
		//接下来的在两端都要调用（操作部分）
		if(!data.isDataStateGood()) return;
		AbilityClass abc = getAbilityClass(player);
		ItemStack is = player.getCurrentEquippedItem();
		boolean b2 = is == null || is.itemID == ModuleSystem.itemAbilityVoid.itemID;
		
		if(abc != null) {
			AbilityLevel lvl = abc.getAbilityLevel(data.ability_level);
			if(lvl != null) {
				
				PlayerControlStat stat = getControlStat(player);
				for(int i = 0; i < 4; i++) {
					if(stat.keyDown[i]) {
						int[] flag =  lvl.getSkillForKey(i);
						AbilitySkill skl = abc.getAbilitySkill(flag[0]);
						if(!b2 || !skl.onButtonTick(world, data, flag[1], stat)) { //执行日常的键位更新
							stat.keyDown[i] = false;
							data.lastActiveSkill = null;
						}
					}
				}
				
			} else { System.out.println("No level found : " + data.ability_level); }
		} else { System.out.println("No class found : " + data.ability_class); }
		//System.out.println("Successfully finished update in " + world.isRemote + " side");
	}
	
	public boolean onControlStatChange(EntityPlayer player, int keyID, boolean isDown) {
		PlayerControlStat stat = getControlStat(player);
		PlayerAbilityData data = getAbilityData(player);
		World world = player.worldObj;
		System.out.println("OnControlStatChange called");
		if(data == null || !data.isDeveloped || !data.isActivated) return false;
		boolean b = !isDown && !stat.keyDown[keyID];
		ItemStack is = player.getCurrentEquippedItem();
		boolean b2 = is == null || is.itemID == ModuleSystem.itemAbilityVoid.itemID;
		
		if(isDown) stat.onKeyDown(keyID);
		else stat.onKeyUp(keyID);
		
		
		int[] arr = getSkillArrayFor(player, keyID);
		if(arr != null) {
			AbilityClass abc = getAbilityClass(data);
			if(abc != null) {
				AbilitySkill skl = abc.getAbilitySkill(arr[0]);
				if(isDown) {
					if(b2 && skl.onButtonDown(world, data, arr[1], stat) && data.lastActiveSkill == null)
						data.lastActiveSkill = skl;
				} else if(!b) { //只在进行更新循环时才调用 
					skl.onButtonUp(world, data, arr[1], stat);
					data.lastActiveSkill = null;
				}
			}
		}
		return true;
	}
	
	public int[] getSkillArrayFor(EntityPlayer player, int keyID) {
		int[] thearr = new int[2];
		PlayerAbilityData data = getAbilityData(player);
		if(data != null) {
			AbilityClass abc = getAbilityClass(data);
			if(abc != null) {
				AbilityLevel lvl = abc.getAbilityLevel(data.ability_level);
				if(lvl != null) {
					
					if(lvl.useCustomKeyset()) return lvl.getSkillForKey(keyID);
					else {
						return data.controlData.controlSets[data.controlData.currentSetID].keyData[keyID];
					}
					
				}
				return null;
			}
			return null;
		}
		return null;
	}
	
	
	public void onWorldSave() {
		for(Map.Entry<EntityPlayer, PlayerAbilityData> entry : dataMap.entrySet()) {
			EntityPlayer player = entry.getKey();
			if(player.worldObj.isRemote) return;
			if(player.addedToChunk && !player.isDead) {
				PlayerAbilityData data = entry.getValue();
				data.saveProperties();
				AcademyMod.log.fine("AcademyCraft saved ability properties for " + player.getEntityName());
			}
		}
	}

	/**
	 * 对所有客户端进行一次数据同步。
	 * SERVER ONLY.
	 */
	public void syncAllPlayers(EnumDataType type) {
		for(PlayerAbilityData data : dataMap.values()) {
			AbilityDataSender.sendAbilityDataFromServer(data, type);
		}
	}
	
	/**
	 * 清理数据链表以防出现错误。
	 */
	public void cleanup() {
		dataMap.clear();
		ctrlMap.clear();
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "AbilitySystem Ticks";
	}

}
