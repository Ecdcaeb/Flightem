package com.Hileb.dlightord.entity;

import com.Hileb.dlightord.FlightordModMain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class FlightordRegister {
    private static int ENTITY_NEXT_ID = 1;

    public static void registerEntities()
    {
        EntityRegistry.registerModEntity(new ResourceLocation(FlightordModMain.MODID, "flightord"), FlightordSwordEntity.class,"flightord",1, FlightordModMain.instance, 50, 1, true);
        if (!FlightordModMain.proxy.isServer()){
            RenderingRegistry.registerEntityRenderingHandler(FlightordSwordEntity.class, RenderZFP::new);
        }
    }
}
