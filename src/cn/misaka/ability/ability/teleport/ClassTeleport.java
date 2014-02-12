/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.ability.teleport;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cn.misaka.system.ability.AbilityClass;

/**
 * @author WeAthFolD
 *
 */
public class ClassTeleport extends AbilityClass {

	/**
	 * 
	 */
	public ClassTeleport() {
		
		this.levelList.add(new LevelTest(this));
		this.skillList.add(new SkillPlayerTransform(this, 0));
	}

	/* (non-Javadoc)
	 * @see cn.misaka.system.ability.AbilityClass#getAbilityName()
	 */
	@Override
	public String getAbilityName() {
		return "Teleporter";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getClassLogo() {
		return null;
	}

}
