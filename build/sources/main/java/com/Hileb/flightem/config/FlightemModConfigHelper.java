package com.Hileb.flightem.config;

import com.Hileb.flightem.FlightemModMain;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(modid=FlightemModMain.MODID)
public class FlightemModConfigHelper {
    public static List<Item> acceptExtra=NonNullList.create();

    public static boolean acceptItem(Item item){
        return (acceptExtra.contains(item));
    }
    public static String getProtectedString(String s){
        return s.replaceAll("\\s*|\t|\r|\n","");
    }
    public static List<String> getConfigAccept(){
        return Arrays.asList(getProtectedString(FlightemModConfig.instance.configPathAccept).split(";"));
    }
    public static List<String> getConfigAngle(){
        return Arrays.asList(getProtectedString(FlightemModConfig.instance.configPathAngle).split(";"));
    }
    public static void reloadConfig() throws ConfigError {
        List<String> extraItems=getConfigAccept();
        List<String> adjustItems=getConfigAngle();
        FlightemModConfigHelper.acceptExtra.clear();
        FlightemConfigAdjustment.REGISTERS.clear();

        MinecraftForge.EVENT_BUS.post(new FlightemRegisterEvent());

        if (!extraItems.isEmpty()) FlightemModMain.logger.info("extra Flightords register begin");
        for(String extra:extraItems){
            try{
                FlightemModMain.logger.info("accept Flightord:"+extra);
                Item item=Item.getByNameOrId(extra);
                if (item!=null){
                    acceptExtra.add(item);
                }
            }catch (Exception e){
                throw new ConfigError(e,extra);
            }
        }
        FlightemModMain.logger.info("register "+FlightemModConfigHelper.acceptExtra.size()+"extra Flightord(s)");



        if (!adjustItems.isEmpty()) FlightemModMain.logger.info("extra Flightords adjustments register begin");
        for(String adjust:adjustItems){
            try{
                FlightemModMain.logger.info("accept Adjustment Flightord:"+adjust);
                String[] context=adjust.split("@");
                if (context.length==2){
                    Item item=Item.getByNameOrId(context[0]);
                    FlightemConfigAdjustment flightemConfigAdjustment = FlightemConfigAdjustment.getFromString(context[1]);
                    FlightemConfigAdjustment.register(item, flightemConfigAdjustment);
                }
            } catch (Exception e){
                throw new ConfigError(e,adjust);
            }
        }
        FlightemModMain.logger.info("register "+FlightemConfigAdjustment.REGISTERS.size()+"extra Flightord Adjustment(s)");
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
