package com.Hileb.flightem.entity;

import com.Hileb.flightem.config.FlightemConfigAdjustment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/30 14:25
 **/
public class FlightemEntityRender extends RenderEntityModel<FlightemEntity>{
    public FlightemEntityRender(RenderManager renderManager) {
        super(renderManager, 0,null);
    }

    @Override
    protected void renderAsModel(FlightemEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();

        ItemStack stack=entity.getItemStack();
        float angleX=0;
        float angleY=0;
        float angleZ=0;
        float scaleAdj=1f;
        FlightemSpecialRender flightemSpecialRender=null;
        if (FlightemConfigAdjustment.REGISTERS.containsKey(stack.getItem())){
            FlightemConfigAdjustment adjustment= FlightemConfigAdjustment.REGISTERS.get(stack.getItem());
            angleX=adjustment.angleX;
            angleY=adjustment.angleY;
            angleZ=adjustment.angleZ;
            scaleAdj=adjustment.size;
            flightemSpecialRender=adjustment.sr;
        }
        GlStateManager.rotate(angleX, 1.0F,0, 0.0F);//B 180
        GlStateManager.rotate(angleY,0,1F,0);
        GlStateManager.rotate(angleZ, 0,0,1.0F);
        GlStateManager.scale(scaleAdj,scaleAdj,scaleAdj);
        if (!stack.isEmpty()){
            if (flightemSpecialRender!=null){
                flightemSpecialRender.render();
            }
            else Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
        }
        GlStateManager.scale(1/scaleAdj,1/scaleAdj,1/scaleAdj);

        GlStateManager.popMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(FlightemEntity entity) {
        return null;
    }
}
