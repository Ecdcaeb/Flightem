package com.Hileb.dlightord.config;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class FlightordAngleAdjust{
    public static Map<Item,Double> REGISTERS=new HashMap<>();

    public static void register(Item item,double angle){
        REGISTERS.put(item,angle);
    }
}
