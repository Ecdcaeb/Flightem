package com.Hileb.flightem.config;

import com.Hileb.flightem.FlightemModMain;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = FlightemModMain.MODID, category = "")
public class FlightemModConfig {
    @Mod.EventBusSubscriber(modid = FlightemModMain.MODID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(FlightemModMain.MODID)) {
                ConfigManager.sync(FlightemModMain.MODID, Config.Type.INSTANCE);
                FlightemModMain.tryConfig();
            }
        }
    }

    @Config.LangKey("")
    @Config.Comment("")
    @Config.RequiresMcRestart
    public static FlightordModConfigInstance instance=new FlightordModConfigInstance();

    public static class FlightordModConfigInstance {
        @Config.LangKey("")
        @Config.Comment("")
        @Config.RequiresMcRestart
        public String configPathAngle =
                "minecraft:bed@{size:3f,angleX:-90f,adjustY:1.5f,shouldRiderSit:1f}"
                +";minecraft:iron_sword@{size:1.2f,angleX:90f,angleZ:135f,adjustY:0.8f}"
                +";minecraft:grass@{size:3f,angleX:180f,adjustY:2.8f}";
        // item@adjustment;

        @Config.LangKey("")
        @Config.Comment("")
        @Config.RequiresMcRestart
        public  String configPathAccept = "minecraft:bed;minecraft:iron_sword;minecraft:grass";
        // item;item
    }

}
