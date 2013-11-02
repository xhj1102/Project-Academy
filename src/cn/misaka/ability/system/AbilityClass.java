/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.misaka.ability.proxy.AbilityGeneralProps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 一种能力系。被ServerAbilityMain统一管理和调用，在这里进行一种能力系的各种功能计算。
 * @author WeAthFolD
 *
 */
public abstract class AbilityClass {

	public ArrayList<AbilityComponent> components = new ArrayList();

	protected HashMap<EntityPlayer, ControlStat> controlStatus = new HashMap();
	
	public static class ControlStat {
		public static final int LENGTH = AbilityGeneralProps.MAX_KEYS;
		
		public boolean[] keyDown = new boolean[LENGTH];
		public int[] keyTick = new int[LENGTH];
		
		public void setKeyStat(int id, boolean b) {
			keyDown[id] = b;
			if(!b) keyTick[id] = 0;
		}
		
		public void updateTick() {
			for(int i = 0; i < AbilityGeneralProps.MAX_KEYS; i++) {
				if(keyDown[i])
					keyTick[i]++;
				else keyTick[i] = 0;
			}
		}
		
		public void reset() {
			for(int i = 0; i < AbilityGeneralProps.MAX_KEYS; i++) {
				keyDown[i] = false;
				keyTick[i] = 0;
			}
		}
	}
	
	public final void resetAll() {
		for(Map.Entry<EntityPlayer, ControlStat> entry : controlStatus.entrySet()) {
			entry.getValue().reset();
		}
	}
	
	public void resetPlayerStat(EntityPlayer player) {
		ControlStat stat = controlStatus.get(player);
		if(stat != null) stat.reset();
	}
	
	public final void onTick(EntityPlayer player, World world, PlayerAbilityData data) {
		ControlStat stat = controlStatus.get(player);
		if(stat == null) return;
		
		stat.updateTick();
		AbilityComponent comp = getComponent(data.level);
		if(comp == null) {
			System.err.println("onTick() Didn't find the right component to the ability");
			return;
		}
		for(int i = 0; i < stat.LENGTH; i++) {
			if(stat.keyDown[i]) {
				comp.onButtonTick(player, world, data, i, stat.keyTick[i]);
			}
		}
		comp.onAbilityTick(player, world, data, stat);
	}
	
	public final void onButtonStateChange(EntityPlayer player, World world, PlayerAbilityData data, 
			int keyID, boolean isDown) {
		ControlStat stat = controlStatus.get(player);
		if(stat == null) {
			stat = new ControlStat();
			controlStatus.put(player, stat);
		}
		AbilityComponent comp = getComponent(data.level);
		if(comp == null) {
			System.err.println("onButtonStateChange() Didn't find the right component to the ability");
			return;
		}
		System.out.println("STATECHANGE#" + world.isRemote + " : " + isDown);
		if(isDown)
			comp.onButtonDown(player, world, data, keyID, stat);
		else if(!isDown)
			comp.onButtonUp(player, world, data, keyID, stat);
		stat.setKeyStat(keyID, isDown);
	}
	
	public final AbilityComponent getComponent(int level) {
		return level < components.size() ? components.get(level) : null;
	}
	
	public abstract String getAbilityName();
	
}
