package cn.misaka.ability.system;

import cn.misaka.ability.client.system.ClientAbilityComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 代表每一系能力的一种技能，在其主AbilityClass中用一个ArrayList统一存储。
 * @author WeAthFolD
 *
 */
public abstract class AbilityComponent {

	public AbilityComponent() {
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
	public abstract ClientAbilityComponent getClientComponent();

}
