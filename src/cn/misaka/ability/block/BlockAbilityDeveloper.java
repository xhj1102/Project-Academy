/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cn.liutils.api.block.LIBlockBase;
import cn.liutils.api.block.LIContainerBase;
import cn.liutils.core.proxy.LIGeneralProps;
import cn.misaka.ability.tileentity.TileAbilityDeveloper;
import cn.misaka.core.AcademyMod;

/**
 * 能力开发机（固定版） 主类。
 * @author WeAthFolD
 *
 */
public class BlockAbilityDeveloper extends LIContainerBase {

	/**
	 * @param par1
	 * @param material
	 */
	public BlockAbilityDeveloper(int par1) {
		super(par1, Material.iron, AcademyMod.instance);
		this.setStepSound(this.soundMetalFootstep);
		this.setCreativeTab(AcademyMod.cct);
		this.setHardness(3.5F);
	}

	/**
	 * 在这里设置metadata,以及放置另外一个方块.
	 * METADATA结构：
	 * bit 0、1： 0|1|2|3， 指示方向Z- Z+ X- X+ （往指示方向放置子方块）
	 * bit 2：0|1 非|是子方块
	 */
   /* public int onBlockPlaced(World world, int x, int y, int z, int side, float par6, float par7, float par8, int meta)
    {
    	System.out.println("onBlockPlaced called, metadata " + meta);
    	if(meta >> 2 == 1) return meta; //是子方块
    	
    	int dirFlag = meta & 3 + 2;
    	ForgeDirection dir = ForgeDirection.values()[dirFlag];
    	world.setBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this.blockID, 4 | (dirFlag - 2), 0x03);
        return meta;
    }
    */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
    	System.out.println("OnBlockPlacedBy called");
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        int meta = 0;
        if (l == 0) meta = 0;
        if (l == 1) meta = 3;
        if (l == 2) meta = 1;
        if (l == 3) meta = 2;
    	int dirFlag = meta + 2;
    	ForgeDirection dir = ForgeDirection.values()[dirFlag];
    	int x1 = x + dir.offsetX, y1 = y + dir.offsetY, z1 = z + dir.offsetZ;
    	int x2 = x1 + dir.offsetX, y2 = y1 + dir.offsetY, z2 = z1 + dir.offsetZ;
    	if(world.getBlockId(x1, y1, z1) == 0 && 
    			world.getBlockId(x2, y2, z2) == 0) {
    		world.setBlock(x1, y1, z1, this.blockID, 4 | (dir.getOpposite().ordinal() - 2), 0x03);
    		world.setBlock(x2, y2, z2, this.blockID, 4 | (dir.getOpposite().ordinal() - 2), 0x03);
    		world.setBlockMetadataWithNotify(x, y, z, meta, 0x03);
    	} else {
    		this.dropBlockAsItem(world, x, y, z, 0, 0);
    		world.setBlockToAir(x, y, z);
    	}
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
    {
    	/*
    	 * int meta = world.getBlockMetadata(x, y, z);
    	ForgeDirection dir = ForgeDirection.values()[meta & 3];
    	if((meta >> 2) == 1) dir = dir.getOpposite();
    	int blockID2 = world.getBlockId(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ), 
    			meta2 = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
    	if(blockID2 != this.blockID || meta2 == meta) {
    		world.setBlockToAir(x, y, z);
    	}
    	 * 
    	 */
    	TileAbilityDeveloper tile = (TileAbilityDeveloper) world.getBlockTileEntity(x, y, z);
    	tile.onNeighborChanged();
    	super.onNeighborBlockChange(world, x, y, z, par5);
    
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileAbilityDeveloper();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return LIGeneralProps.RENDER_TYPE_EMPTY;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
