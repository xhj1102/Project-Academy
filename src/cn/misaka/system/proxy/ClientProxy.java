/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import cn.liutils.core.client.register.LIKeyProcess;
import cn.misaka.system.ModuleSystem;
import cn.misaka.system.client.event.ClientAbilityMain;
import cn.misaka.system.client.event.ClientEventListener;
import cn.misaka.system.client.render.RenderAbilityVoid;
import cn.misaka.system.control.keys.KeyAbilityControl;

/**
 * @author WeAthFolD
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() { 
		super.preInit();
		
		LIKeyProcess.addKey("abi0", LIKeyProcess.MOUSE_LEFT, false, new KeyAbilityControl(0));
		LIKeyProcess.addKey("abi1", LIKeyProcess.MOUSE_RIGHT, false, new KeyAbilityControl(1));
		LIKeyProcess.addKey("abi2", Keyboard.KEY_R, false, new KeyAbilityControl(2));
		LIKeyProcess.addKey("abi3", Keyboard.KEY_F, false, new KeyAbilityControl(3));
		
		TickRegistry.registerTickHandler(new ClientAbilityMain(), Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new ClientEventListener());
	}
	
	@Override
	public void init() { 
		MinecraftForgeClient.registerItemRenderer(ModuleSystem.itemAbilityVoid.itemID, new RenderAbilityVoid());
		super.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

}
