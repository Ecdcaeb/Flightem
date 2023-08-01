package com.Hileb.flightem.network;


import com.Hileb.flightem.FlightemModMain;
import com.Hileb.flightem.network.packet.FlightemPacketPlayerButtonState;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid= FlightemModMain.MODID,value = Side.CLIENT)
public class FlightemNetworkHandler {
    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(FlightemModMain.MODID);


    public static void init()
    {
        channel.registerMessage(FlightemPacketPlayerButtonState.Handler.class, FlightemPacketPlayerButtonState.class, 1, Side.SERVER);
    }

    @SideOnly(Side.CLIENT)
    public static void sendToServerWhenOnClient()
    {
        FlightemPacketPlayerButtonState message=new FlightemPacketPlayerButtonState();
        message.dB= Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown();
        message.wB= Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown();
        message.sB= Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown();
        message.aB= Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown();

        channel.sendToServer(message);
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onPlayerKeyDown(InputEvent.KeyInputEvent event){
        sendToServerWhenOnClient();
    }
}
