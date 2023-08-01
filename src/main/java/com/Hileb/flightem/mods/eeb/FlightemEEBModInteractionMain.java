package com.Hileb.flightem.mods.eeb;

import net.minecraftforge.common.MinecraftForge;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/31 16:35
 **/
public class FlightemEEBModInteractionMain {
    public static void init(){
        new FlightemEEBEventLoader();
    }
}
