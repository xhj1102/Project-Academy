/**
 * Code by Lambda Innovation, 2013.
 */
package cn.misaka.ability.client.model;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.model.ITileEntityModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author WeAthFolD
 * 
 */
public class ModelAbilityDeveloper extends ModelBase implements ITileEntityModel {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;

	/**
	 * 
	 */
	public ModelAbilityDeveloper() {
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 93);
		Shape1.addBox(0F, 0F, 0F, 14, 5, 30);
		Shape1.setRotationPoint(-7F, 19F, -7F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 56, 65);
		Shape2.addBox(0F, 0F, 0F, 10, 2, 26);
		Shape2.setRotationPoint(-5F, 18F, -5F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 81);
		Shape3.addBox(0F, 0F, 0F, 8, 1, 1);
		Shape3.setRotationPoint(-4F, 13F, 13F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 81);
		Shape4.addBox(0F, 0F, 0F, 1, 8, 1);
		Shape4.setRotationPoint(-4F, 13F, 13F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.3490659F);
		Shape5 = new ModelRenderer(this, 0, 81);
		Shape5.addBox(-1F, 0F, 0F, 1, 8, 1);
		Shape5.setRotationPoint(4F, 13F, 13F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, -0.3490659F);
		Shape6 = new ModelRenderer(this, 0, 30);
		Shape6.addBox(0F, 0F, 0F, 12, 7, 7);
		Shape6.setRotationPoint(-6F, 13F, -6F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0872665F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 52);
		Shape7.addBox(0F, 0F, 0F, 12, 7, 5);
		Shape7.setRotationPoint(-6F, 12.5F, 17F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		setRotation(Shape7, -0.0872665F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 0);
		Shape8.addBox(0F, 0F, 0F, 0, 7, 17);
		Shape8.setRotationPoint(4F, 13.5F, 0F);
		Shape8.setTextureSize(128, 128);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, -0.3490659F);
		Shape9 = new ModelRenderer(this, 0, 0);
		Shape9.addBox(0F, 0F, 0F, 0, 7, 17);
		Shape9.setRotationPoint(-4F, 13.5F, 0F);
		Shape9.setTextureSize(128, 128);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0.3490659F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(0F, 0F, 0F, 8, 0, 17);
		Shape10.setRotationPoint(-4F, 13.5F, 0F);
		Shape10.setTextureSize(128, 128);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 72);
		Shape11.addBox(0F, 0F, 0F, 1, 1, 17);
		Shape11.setRotationPoint(-0.5F, 13F, 0.5F);
		Shape11.setTextureSize(128, 128);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 25, 76);
		Shape12.addBox(0F, 0F, 0F, 6, 1, 3);
		Shape12.setRotationPoint(-3F, 17F, 14F);
		Shape12.setTextureSize(128, 128);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(TileEntity is, double x, double y, double z, float f1,
			float f5, float f) {
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
	}

}
