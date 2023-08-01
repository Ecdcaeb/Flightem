package com.Hileb.flightem.mods;

import com.Hileb.flightem.mods.eeb.FlightemEEBModInteractionMain;
import committee.nova.plr.elytraBombing.ElytraBombing;
import net.minecraftforge.fml.common.Loader;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/31 16:46
 **/
public class FlightemModsInteractionHandler {
    public static void handle(){
        if (Loader.isModLoaded(ElytraBombing.MOD_ID)){
            FlightemEEBModInteractionMain.init();
        }
    }
}
