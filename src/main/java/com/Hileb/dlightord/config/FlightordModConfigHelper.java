package com.Hileb.dlightord.config;

import net.minecraft.command.CommandBase;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightordModConfigHelper {
    public static List<Item> acceptExtra=new ArrayList<>();

    public static boolean acceptItem(Item item){
        return (acceptExtra.contains(item) || item instanceof ItemSword);
    }
    public static List<String> getConfigAccept(){
        return Arrays.asList(FlightordModConfig.instance.configPathAccept.split(";"));
    }
    public static List<String> getConfigAngle(){
        return Arrays.asList(FlightordModConfig.instance.configPathAngle.split(";"));
    }
    public static void registerAllItem() throws ConfigError {
        List<String> extraItems=getConfigAccept();
        List<String> adjustItems=getConfigAngle();

        for(String extra:extraItems){
            try{
                Item item=Item.getByNameOrId(extra);
                acceptExtra.add(item);
            }catch (Exception e){
                throw new ConfigError(e,extra);
            }
        }
        for(String adjust:adjustItems){
            try{
                String[] context=adjust.split(",");
                if (context.length==2){
                    Item item=Item.getByNameOrId(context[0]);
                    double angle= CommandBase.parseDouble(context[1]);
                    FlightordAngleAdjust.register(item,angle);
                }
            } catch (Exception e){
                throw new ConfigError(e,adjust);
            }
        }
    }
    public static class ConfigError extends Exception{
        public Exception exception;
        public String name;
        public ConfigError(Exception exceptionIn,String nameIn){
            exception=exceptionIn;
            name=nameIn;
        }
    }
}
