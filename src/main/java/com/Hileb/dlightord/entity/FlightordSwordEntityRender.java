package com.Hileb.dlightord.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class FlightordSwordEntityRender extends Render<FlightordSwordEntity> {

    protected FlightordSwordEntityRender(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(FlightordSwordEntity entity) {
        return null;
    }

    @Override
    public void doRender(FlightordSwordEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        ItemStack stack=entity.itemSword.getStackInSlot(0);
        Minecraft.getMinecraft().getRenderItem().renderItem(stack,null, ItemCameraTransforms.TransformType.GROUND,false);
    }
}
