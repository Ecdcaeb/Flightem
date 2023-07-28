package com.Hileb.dlightord;


import com.Hileb.dlightord.config.FlightordModConfig;
import com.Hileb.dlightord.config.FlightordModConfigHelper;
import com.Hileb.dlightord.entity.FlightordRegister;
import com.Hileb.dlightord.proxy.ProxyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.command.NumberInvalidException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = FlightordModMain.MODID, name = FlightordModMain.NAME, version = FlightordModMain.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class FlightordModMain {
    public static final String MODID = "dlightord";
    public static final String NAME = "Flightord";
    public static final String VERSION = "1.0.0.0";
    public static Logger logger= LogManager.getLogger(MODID);

    public static boolean isClient=false;
    @SidedProxy(modId = MODID,clientSide = "com.Hileb.dlightord.proxy.ClientProxy",serverSide = "com.Hileb.dlightord.proxy.ServerProxy")
    public static ProxyBase proxy;
    @Mod.Instance
    public static FlightordModMain instance;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }
    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        FlightordRegister.registerEntities();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        try {
            FlightordModConfigHelper.registerAllItem();
        } catch (FlightordModConfigHelper.ConfigError e) {
            logger.error("error at config!");
            logger.error("config item:"+ e.name);
            logger.error("primer exception!:");
            logger.error(e.exception);
            throw new RuntimeException(e);
        }
    }
}