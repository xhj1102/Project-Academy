package cn.misaka.ability.system.client.gui;

import org.lwjgl.opengl.GL11;
import cn.liutils.api.client.gui.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author jiangyue
 *
 */

public class GuiPresetSetter extends LIGuiScreen {
    
    private int skillID, subKeyID, keyID, presetID;
    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 88;
    private LiGuiButton [] elements = new LiGuiButton [20];
    
    public GuiPresetSetter() {
        super(xSizeOfTexture, ySizeOfTexture);	
        
        /*  Add the selected objects to board */
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);  // TODO
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);
        elements[0] = new LIGuiButton("", 0, 0, 0, 0);
        /* There will be loads of code, I promise. */
        
        /* Then we add them to the GuiScreen. */
        this.drawElements();
    }
    
    @Override
    public void initGui() {
        // Not used anymore.
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
    }
    
    @Override
    public void onButtonClicked(LiGuiButton butt, boolean is) {
        String name = butt.name;
        int nameInt = Integer.valueOf(name);
        /* Use different methods to do different things. */
        if(nameInt >= 1 && nameInt <= 99) onMenuChanged(nameInt);
        if(nameInt >= 100 && nameInt <= 199) onChildMenuChanged(nameInt - 100);
        if(nameInt >= 200 && nameInt <= 299) onPresetKeyChanged(nameInt - 200);
        if(nameInt >= 300 && nameInt <= 399) {
            onPresetChanged(nameInt - 300);
            confirmPresetKeyChange();
        }
    }
    
    
    
    
    
    
    /**
     * Here does the implements to the AP Controlling.
     */
    public void onMenuChanged(int i) {
        this.skillID = i;
    }
    
    public void onChildMenuChanged(int i) {
        this.subKeyID = i;
    }
    
    public void onPresetKeyChanged(int i) {
        this.keyID = i;
    }
    
    public void onPresetChanged(int i) {
        this.presetID = i;
    }
    
    /**
     * The method connects to the AP API correctly.
     */
    public void confirmPresetKeyChange() {
        invokePresetChange(this.skillID, this.subKeyID, this.keyID, this.presetID);
    }
    
    public void invokePresetChange(int par1, int par2, int par3, int par4) {
        // TODO
    }
    
    
}