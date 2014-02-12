/**
 * 
 */
package cn.misaka.ability.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import cn.liutils.core.LIUtilsMod;
import cn.misaka.ability.client.render.RenderSwordEnchanted_Fire;
import cn.misaka.ability.client.render.block.RenderAbilityDeveloper;
import cn.misaka.ability.client.render.entity.RenderMdRay;
import cn.misaka.ability.client.render.entity.RenderMeltdownBall;
import cn.misaka.ability.client.render.entity.RenderTeleportIndicator;
import cn.misaka.ability.entity.EntityMdRay;
import cn.misaka.ability.entity.EntityMeltdowner;
import cn.misaka.ability.entity.EntityTeleportIndicator;
import cn.misaka.ability.register.AbilityItems;
import cn.misaka.ability.tileentity.TileAbilityDeveloper;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author WeAthFolD
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		
		if(LIUtilsMod.DEBUG) {
			//KeyMoving.addProcess(new Debug_AbilityVoid());
		}
	}
	
	@Override
	public void init() {
		
		MinecraftForgeClient.registerItemRenderer(AbilityItems.swordEnch_fire.itemID, new RenderSwordEnchanted_Fire());
		RenderingRegistry.registerEntityRenderingHandler(EntityMeltdowner.class, new RenderMeltdownBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityMdRay.class, new RenderMdRay());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportIndicator.class, new RenderTeleportIndicator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAbilityDeveloper.class, new RenderAbilityDeveloper());
		super.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

	
}
