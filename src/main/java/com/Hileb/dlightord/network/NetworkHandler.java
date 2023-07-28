package com.Hileb.dlightord.network;


import com.Hileb.dlightord.FlightordModMain;
import com.Hileb.dlightord.network.packet.PacketPlayerButtonState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid=FlightordModMain.MODID)
public class NetworkHandler {
    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(FlightordModMain.MODID);

    static int id = 0;
    public static void init()
    {
        channel.registerMessage(PacketPlayerButtonState.Handler.class,PacketPlayerButtonState.class, id++, Side.SERVER);
    }

    @SideOnly(Side.CLIENT)
    public static void sendToServerWhenOnClient()
    {
        PacketPlayerButtonState message=new PacketPlayerButtonState();
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
