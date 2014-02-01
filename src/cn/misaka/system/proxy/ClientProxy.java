/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.system.proxy;

import org.lwjgl.input.Keyboard;

import cn.liutils.core.client.register.LIKeyProcess;
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
