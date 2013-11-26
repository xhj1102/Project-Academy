/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.render.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cn.liutils.api.client.render.RenderIcon;
import cn.misaka.ability.entity.EntityMeltdowner;
import cn.misaka.core.client.props.AMClientProps;

/**
 * @author WeAthFolD
 *
 */
public class RenderMeltdownBall extends RenderIcon {

	/**
	 * @param ic
	 */
	public RenderMeltdownBall() {
		super(AMClientProps.TEX_EFFECT_MELTDOWN);
		this.hasLight = false;
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		EntityMeltdowner md = (EntityMeltdowner) par1Entity;
		this.setSize(md.size);
		this.alpha = md.alpha;
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

}
