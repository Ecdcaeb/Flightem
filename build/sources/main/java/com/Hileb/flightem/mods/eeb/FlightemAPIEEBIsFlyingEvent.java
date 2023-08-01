package com.Hileb.flightem.mods.eeb;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/31 16:39
 **/
public class FlightemAPIEEBIsFlyingEvent extends PlayerEvent {
    public boolean isFlying=false;
    public FlightemAPIEEBIsFlyingEvent(EntityPlayer player) {
        super(player);
    }
}
