public class GuiButtonAssignment extends GuiScreen{
	public static class Element implements IGuiElement {

		@Override
		public Object getServerContainer(EntityPlayer player, World world,
										 int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGui(EntityPlayer player, World world, int x,
								   int y, int z) {
			return new GuiButtonAssignment((TileButtonAssignment) world.getTileEntity(x, y, z));
		}

	}

	TileButtonAssignment myTile;

	/**
	 * 
	 */
	public GuiButtonAssignment(TileButtonAssignment tile) {
		myTile = tile;
	}

	@Override
	public void onGuiClosed() {
		System.out.println("onGuiClosed");
		// myTile.disMount();
		// AcademyCraft.netHandler.sendToServer(new MsgDeveloperDismount());
	}

    @Override
	public void drawScreen(int par1, int par2, float par3)
    {
    	// ;
    }
	
	public void onKeySelect() {
		
	}
	
}
