package com.Hileb.flightem.config;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/31 10:04
 **/
public class FlightemRegisterEvent extends Event {
    public FlightemRegisterEvent(){

    }
    public void registerAdjustment(Item item, FlightemConfigAdjustment adjustment){
        FlightemConfigAdjustment.register(item,adjustment);
    }
    public void registerItemAccess(Item item){
        FlightemModConfigHelper.acceptExtra.add(item);
    }
    @Override
    public boolean isCancelable() {
        return false;
    }
}
