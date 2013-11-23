/**
 * 
 */
package cn.misaka.ability.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cn.liutils.core.LIUtilsMod;
import cn.liutils.core.client.register.LIKeyProcess;
import cn.misaka.ability.register.AbilityItems;
import cn.misaka.ability.system.client.AbilityClientEventHandler;
import cn.misaka.ability.system.client.keys.KeyAbilityControl;
import cn.misaka.ability.system.client.keys.KeyAbilitySwitch;
import cn.misaka.ability.system.client.render.RenderAbilityVoid;
import cn.misaka.ability.system.client.system.ClientAbilityMain;
import cn.misaka.core.client.props.AMClientProps;
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
		
		LIKeyProcess.addKey("abi0", LIKeyProcess.MOUSE_LEFT, false, new KeyAbilityControl(0));
		LIKeyProcess.addKey("abi1", LIKeyProcess.MOUSE_RIGHT, false, new KeyAbilityControl(1));
		LIKeyProcess.addKey("abi2", Keyboard.KEY_R, false, new KeyAbilityControl(2));
		LIKeyProcess.addKey("abi3", Keyboard.KEY_F, false, new KeyAbilityControl(3));
		LIKeyProcess.addKey("switch", AMClientProps.KEY_MODESWITCH, false, new KeyAbilitySwitch());
		
		if(LIUtilsMod.DEBUG) {
			//KeyMoving.addProcess(new Debug_AbilityVoid());
		}
	}
	
	@Override
	public void init() {
		
		MinecraftForgeClient.registerItemRenderer(AbilityItems.abilityVoid.itemID, new RenderAbilityVoid());
		super.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

	
}
