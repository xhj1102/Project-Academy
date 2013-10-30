/**
 * 
 */
package cn.liutils.core.proxy;

import net.minecraftforge.common.MinecraftForge;
import cn.liutils.api.client.render.RenderTrail;
import cn.liutils.api.entity.EntityTrailFX;
import cn.liutils.core.client.register.LIKeyProcess;
import cn.liutils.core.client.register.LISoundRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
public class LIClientProxy extends LICommonProxy {

	@Override
	public void init() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTrailFX.class, new RenderTrail());
	}
	
	@Override
	public void preInit() {
		TickRegistry.registerTickHandler(new LIKeyProcess(), Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new LISoundRegistry());
	}
	
	@Override
	public void postInit() {}
}
