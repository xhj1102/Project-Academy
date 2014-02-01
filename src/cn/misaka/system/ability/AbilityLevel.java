/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.ability;

import cn.misaka.system.data.AbilityControlData;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author WeAthFolD
 *
 */
public abstract class AbilityLevel {

	AbilityClass baseClass;
	
	/**
	 * 仅在使用自动分配键位时被使用。这个ControlData代表了默认的该等级能力键位绑定。
	 */
	AbilityControlData preset = null;
	
	/**
	 * 
	 */
	public AbilityLevel(AbilityClass thclass) {
		baseClass = thclass;
	}
	
	public abstract boolean isSkillAvailable(EntityPlayer player, AbilitySkill skill, int id);
	
	/**
	 * 是否使用自定义的键位分布。一般在0~3为true，3~5为false.
	 * @return
	 */
	public abstract boolean useCustomKeyset();
	
	/**
	 * TODO:Unfinished.
	 */
	public abstract void onButtonDown();
	
	/**
	 * 获取一个键位对应的skillID，仅在非自动分配键位时使用。
	 * @param keyID
	 * @return [0]skillID
	 *         [1]映射到的keyID
	 */
	public abstract int[] getSkillForKey(int keyID);
	

}
