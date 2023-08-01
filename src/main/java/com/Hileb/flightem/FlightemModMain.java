package com.Hileb.flightem;


import com.Hileb.flightem.config.FlightemModConfigHelper;
import com.Hileb.flightem.entity.FlightemRegister;
import com.Hileb.flightem.mods.FlightemModsInteractionHandler;
import com.Hileb.flightem.network.FlightemNetworkHandler;
import com.Hileb.flightem.proxy.FlightemProxyBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = FlightemModMain.MODID, name = FlightemModMain.NAME, version = FlightemModMain.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class FlightemModMain {
    public static final String MODID = "flightem";
    public static final String NAME = "Flightem";
    public static final String VERSION = "1.0.0.0";
    public static Logger logger= LogManager.getLogger(MODID);

    public static boolean isClient=false;
    @SidedProxy(modId = MODID,clientSide = "com.Hileb.flightem.proxy.FlightemClientProxy",serverSide = "com.Hileb.flightem.proxy.FlightemServerProxy")
    public static FlightemProxyBase proxy;
    @Mod.Instance
    public static FlightemModMain instance;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }
    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        FlightemRegister.registerEntities();
        FlightemNetworkHandler.init();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        tryConfig();
        FlightemModsInteractionHandler.handle();
    }
    public static void tryConfig(){
        try {
            FlightemModConfigHelper.reloadConfig();
        } catch (FlightemModConfigHelper.ConfigError e) {
            logger.error("error at config!");
            logger.error("config item:"+ e.name);
            logger.error("primer exception!:");
            logger.error(e.exception);
            throw new RuntimeException(e);
        }
        logger.info("Flightord finished its pee-work!");
    }
}