/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.tileentity;

import cn.misaka.ability.register.AbilityBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author WeAthFolD
 *
 */
public class TileAbilityDeveloper extends TileEntity {

	int ticksExisted = 0;
	
	/**
	 * 
	 */
	public TileAbilityDeveloper() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isLoaded = false;
	public int adjX, adjY, adjZ;
	
    public void updateEntity() {
    	if(!isLoaded) {
    		int meta = this.getBlockMetadata();
    		ForgeDirection dir = ForgeDirection.values()[(meta & 3) + 2];
    		adjX = xCoord + dir.offsetX;
    		adjY = yCoord;
    		adjZ = zCoord + dir.offsetZ;
    		System.out.println(xCoord + " " + yCoord + " " + zCoord + " " + adjX + " " + adjY + " " + adjZ);
    		isLoaded = true;
    	}
    	++ticksExisted;
    }
    
    public void onNeighborChanged() {
    	if(ticksExisted > 5 && worldObj.getBlockId(adjX, adjY, adjZ) != AbilityBlocks.abilityDeveloper.blockID) {
    		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
    	}
    }

}
