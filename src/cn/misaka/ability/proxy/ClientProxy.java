/**
 * 
 */
package cn.misaka.ability.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import cn.liutils.core.client.register.LIKeyProcess;
import cn.misaka.ability.client.AbilityClientEventHandler;
import cn.misaka.ability.client.keys.KeyAbilityControl;
import cn.misaka.ability.client.system.ClientAbilityMain;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author WeAthFolD
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		
		MinecraftForge.EVENT_BUS.register(new AbilityClientEventHandler());
		TickRegistry.registerTickHandler(new ClientAbilityMain(), Side.CLIENT);
		
		LIKeyProcess.addKey(new KeyBinding("abi0", LIKeyProcess.MOUSE_LEFT), false, new KeyAbilityControl(0));
		LIKeyProcess.addKey(new KeyBinding("abi1", LIKeyProcess.MOUSE_RIGHT), false, new KeyAbilityControl(1));
		LIKeyProcess.addKey(new KeyBinding("abi2", Keyboard.KEY_R), false, new KeyAbilityControl(2));
		LIKeyProcess.addKey(new KeyBinding("abi3", Keyboard.KEY_F), false, new KeyAbilityControl(3));
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

	
}
