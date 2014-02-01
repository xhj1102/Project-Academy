/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import cn.misaka.system.control.PlayerControlStat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * 能力技能类。所有实际的产生实体等能力相关动作都在这里进行。
 * 注意skill中所有的keyID都被映射到了局部ID（即0.1.2,...，不是和键盘绑定的ID）
 * @author WeAthFolD
 *
 */
public abstract class AbilitySkill {

	/**
	 * 
	 */
	public AbilitySkill() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void onButtonTick(World world, EntityPlayer player, int keyID, PlayerControlStat stat);
	
	public abstract void onButtonDown(World world, EntityPlayer player, int keyID, PlayerControlStat stat);
	
	public abstract void onButtonUp(World world, EntityPlayer player, int keyID, PlayerControlStat stat);
	
	/**
	 * 获取最多需要使用的键位数。
	 * @return
	 */
	public abstract int getMaxKeys();
	
	public abstract String getDescriptionForKey(int keyID);
	
}
