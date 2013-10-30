/**
 * 
 */
package cn.liutils.core.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import cn.liutils.api.client.render.RenderTrail;
import cn.liutils.api.debug.Debug_ProcessorModel;
import cn.liutils.api.debug.KeyMoving;
import cn.liutils.api.entity.EntityTrailFX;
import cn.liutils.core.LIUtilsMod;
import cn.liutils.core.client.register.LIClientTickHandler;
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

	public static LIClientTickHandler clientTickHandler = new LIClientTickHandler();
	
	@Override
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTrailFX.class, new RenderTrail());
	}
	
	@Override
	public void preInit() {
		TickRegistry.registerTickHandler(new LIKeyProcess(), Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new LISoundRegistry());
		TickRegistry.registerTickHandler(clientTickHandler, Side.CLIENT);
		
		if(LIUtilsMod.DEBUG) {
			KeyMoving key = new KeyMoving();
			key.addProcess(new Debug_ProcessorModel());
			LIKeyProcess.addKey(new KeyBinding("up", Keyboard.KEY_UP), true, key);
			LIKeyProcess.addKey(new KeyBinding("down", Keyboard.KEY_DOWN), true, key);
			LIKeyProcess.addKey(new KeyBinding("left", Keyboard.KEY_LEFT), true, key);
			LIKeyProcess.addKey(new KeyBinding("right", Keyboard.KEY_RIGHT), true, key);
			LIKeyProcess.addKey(new KeyBinding("forward", Keyboard.KEY_NUMPAD8), true, key);
			LIKeyProcess.addKey(new KeyBinding("back", Keyboard.KEY_NUMPAD2), true, key);
		}
	}
	
	@Override
	public void postInit() {}
}
