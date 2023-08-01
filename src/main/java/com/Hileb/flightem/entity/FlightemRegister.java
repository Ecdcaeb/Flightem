package com.Hileb.flightem.entity;

import com.Hileb.flightem.FlightemModMain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid= FlightemModMain.MODID,value = Side.CLIENT)
public class FlightemRegister {
    public static void registerEntities()
    {
        EntityRegistry.registerModEntity(new ResourceLocation(FlightemModMain.MODID, "flightord"), FlightemEntity.class,"flightord",1, FlightemModMain.instance, 50, 1, true);
        //EntityRegistry.registerModEntity(new ResourceLocation(FlightemModMain.MODID, "aa"), EntityItemFrameTest.class,"aa",1, FlightemModMain.instance, 50, 2, true);

    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        RenderingRegistry.registerEntityRenderingHandler(FlightemEntity.class, FlightemEntityRender::new);
    }
}
