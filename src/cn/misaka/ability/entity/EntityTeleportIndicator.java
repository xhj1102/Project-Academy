/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.entity;

import cn.liutils.api.util.Motion3D;
import cn.misaka.system.control.PlayerControlStat;
import cn.misaka.system.data.PlayerAbilityData;
import cn.misaka.system.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author WeAthFolD
 *
 */
public class EntityTeleportIndicator extends EntityPlayerIndicator {

	private final PlayerAbilityData playerData;
	private PlayerControlStat controlStat;
	private int activateKeyId = -1, keepKeyId = -1;
	private int cooldownTick = 0;
	
	/**
	 * @param par1World
	 */
	public EntityTeleportIndicator(World par1World) {
		super(par1World);
		playerData = null;
	}
	
	public EntityTeleportIndicator(World world, PlayerAbilityData data) {
		super(world, data.player);
		updatePosition();
		playerData = data;
		controlStat = CommonProxy.abilityMain.getControlStat(associatedPlayer);
		activateKeyId = CommonProxy.abilityMain.getMapForLocalKey(data, 0, 1);
		keepKeyId = CommonProxy.abilityMain.getMapForLocalKey(data, 0, 0);
	}
	
    public void onUpdate()
    {
    	this.motionX = this.motionY = this.motionZ = 0;
    	super.onUpdate(); //Original
    	
    	//TODO: RayTrace, 临近判断，实体位置更改。
    	if(!worldObj.isRemote) {
    		
    		updatePosition();
    		
    		if(cooldownTick > 0) --cooldownTick;
    		if(controlStat.keyDown[activateKeyId] && cooldownTick == 0) {
    			if(playerData.consumeCCP(400, false)) {
    				associatedPlayer.mountEntity((Entity)null);
    				(associatedPlayer).setPositionAndUpdate(posX, posY, posZ);
    				cooldownTick = 30;
    			}
    			return;
    		}
    		if(!controlStat.keyDown[keepKeyId]) {
    			this.setDead();
    		}
    	}
    	
    }
    
    private void updatePosition() {
		Motion3D endPos;
        Vec3 vec3 = associatedPlayer.getPosition(1.0F);
        Vec3 vec31 = associatedPlayer.getLook(1.0F);
        Vec3 vec32 = vec3.addVector(vec31.xCoord, vec31.yCoord, vec31.zCoord);
        MovingObjectPosition res = this.worldObj.clip(vec3, vec32);
        
		if(res == null) {
			endPos = new Motion3D(associatedPlayer, true).move(20.0);
		} else {
			ForgeDirection dir = ForgeDirection.values()[res.sideHit];
			endPos = new Motion3D(res.hitVec.addVector(dir.offsetX, dir.offsetY + 1.5, dir.offsetZ), 0.0, 0.0, 0.0);
			endPos = getNearestPosition(endPos);
		}
		
		this.setPosition(endPos.posX, endPos.posY, endPos.posZ);
    }
    
    private Motion3D getNearestPosition(Motion3D ep) {
    	int bx = (int) ep.posX, bz = (int) ep.posZ, i;
    	for(i = (int) ep.posY; ; i++) {
    		int b1 = worldObj.getBlockId(bx, i, bz), b2 = worldObj.getBlockId(bx, i+1, bz);
    		if(isAirBlock(b1) && isAirBlock(b2))
    			break;
    	}
    	return new Motion3D(ep.posX, i, ep.posZ, 0, 0, 0);
    }
    
    private boolean isAirBlock(int bID) {
    	return bID == 0 || bID == 1;
    }
}
