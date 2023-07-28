package com.Hileb.dlightord.config;

import com.Hileb.dlightord.FlightordModMain;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


@Config(modid = FlightordModMain.MODID, category = "")
public class FlightordModConfig {
    @Mod.EventBusSubscriber(modid = FlightordModMain.MODID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(FlightordModMain.MODID)) {
                ConfigManager.sync(FlightordModMain.MODID, Config.Type.INSTANCE);
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
        public String configPathAngle = "";
        // item,angle;

        @Config.LangKey("")
        @Config.Comment("")
        @Config.RequiresMcRestart
        public  String configPathAccept = "";
        // item,item
    }

}
