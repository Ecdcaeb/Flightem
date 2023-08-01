package com.Hileb.flightem.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public abstract class RenderEntityModel<T extends Entity> extends Render<T> {
    public ModelBase mainModel;

    public RenderEntityModel(RenderManager renderManager,float shadowSizeIn,ModelBase modelBase) {
        super(renderManager);
        this.mainModel=modelBase;
        this.shadowSize = shadowSizeIn;
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entityYaw);
        this.bindEntityTexture(entity);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.renderAsModel(entity,x,y,z,entityYaw,partialTicks);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    protected void renderAsModel(T entity, double x, double y, double z, float entityYaw, float partialTicks){
        mainModel.render(entity,partialTicks, 0, 0, 0, 0, 1F);
    }
    public void setupTranslation(double entityRenderX, double entityRenderY , double  entityRenderZ)
    {
        GlStateManager.translate((float)entityRenderX, (float)entityRenderY + 0.375F, (float)entityRenderZ);
    }
    public void setupRotation(float entityYaw)
    {
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }
}
