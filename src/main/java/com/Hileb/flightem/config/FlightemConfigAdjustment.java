package com.Hileb.flightem.config;

import com.Hileb.flightem.entity.FlightemSpecialRender;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/29 16:39
 **/
public class FlightemConfigAdjustment {
    public static Map<Item, FlightemConfigAdjustment> REGISTERS=new HashMap<>();

    public static void register(Item item, FlightemConfigAdjustment angle){
        REGISTERS.put(item,angle);
    }

    public float size;
    public float angleX;
    public float angleY;
    public float angleZ;
    public float adjustY;
    public float shouldRiderSit;
    public FlightemSpecialRender sr;
    public FlightemConfigAdjustment(){
        size=0;
        adjustY=0;
        angleX=0;
        angleY=0;
        angleZ=0;
        shouldRiderSit=0f;
        sr=null;
    }

    public static FlightemConfigAdjustment getFromString(String s) throws NBTException {
        NBTTagCompound tagCompound=JsonToNBT.getTagFromJson(s);
        FlightemConfigAdjustment flightemConfigAdjustment =new FlightemConfigAdjustment();
        flightemConfigAdjustment.size=getFloatSafe(tagCompound,"size",1f);
        flightemConfigAdjustment.angleX=getFloatSafe(tagCompound,"angleX",0f);
        flightemConfigAdjustment.angleY=getFloatSafe(tagCompound,"angleY",0f);
        flightemConfigAdjustment.angleZ=getFloatSafe(tagCompound,"angleZ",0f);
        flightemConfigAdjustment.adjustY=getFloatSafe(tagCompound,"adjustY",0f);
        flightemConfigAdjustment.shouldRiderSit=getFloatSafe(tagCompound,"shouldRiderSit",0f);

        return flightemConfigAdjustment;
    }
    public static float getFloatSafe(NBTTagCompound nbtTagCompound,String key,float defalt){
        if (nbtTagCompound.hasKey(key)){
            return nbtTagCompound.getFloat(key);
        }else return defalt;
    }
}
