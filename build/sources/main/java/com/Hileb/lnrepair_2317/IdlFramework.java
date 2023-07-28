package com.Hileb.lnrepair_2317;

import com.Hileb.lnrepair_2317.proxy.ProxyBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class IdlFramework {
    public static final String MODID = "lnrepair_2317";
    public static final String NAME = "\\n Repair";
    public static final String VERSION = "1.0.0.0";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = "com.Hileb.lnrepair_2317.proxy.ClientProxy", serverSide ="com.Hileb.lnrepair_2317.proxy.ServerProxy")
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        new Tooltip();

    }
    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }
    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }
    public static void Log(String str) {
        logger.info(str);
    }
    public static void Log(String str, Object... args) {
        logger.info(String.format(str, args));
    }
}