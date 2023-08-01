package com.Hileb.flightem.config;

import com.Hileb.flightem.FlightemModMain;
import com.Hileb.flightem.entity.FlightemSpecialRender;
import com.google.gson.Gson;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

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
    public float adjustX;
    public float adjustY;
    public float adjustZ;
    public float shouldRiderSit;
    public FlightemSpecialRender sr;
    public ResourceLocation name;
    public FlightemConfigAdjustment(){
        size=0;
        adjustY=0;
        angleX=0;
        angleY=0;
        angleZ=0;
        shouldRiderSit=0f;
        sr=null;
        name=null;
    }

    public static FlightemConfigAdjustment getFromString(String s) throws NBTException {
        NBTTagCompound tagCompound=JsonToNBT.getTagFromJson(s);
        FlightemConfigAdjustment flightemConfigAdjustment =new FlightemConfigAdjustment();

        float defSize=1f;
        float defAngleX=0f;
        float defAngleY=0f;
        float defAngleZ=0f;
        float defAdjustX=0f;
        float defAdjustY=0f;
        float defAdjustZ=0f;
        float defShouldRiderSit=0f;
        String name=getStringSafe(tagCompound,"type",null);
        if (name!=null)flightemConfigAdjustment.name=new ResourceLocation(name);
        String nameP=getStringSafe(tagCompound,"parent",null);
        if (nameP!=null){
            for(FlightemConfigAdjustment t:FlightemConfigAdjustment.REGISTERS.values()){
                if (nameP.equals(String.valueOf(t.name))){
                    defSize=t.size;
                    defAngleX=t.angleX;
                    defAngleY=t.angleY;
                    defAngleZ=t.angleZ;
                    defAdjustX=t.adjustX;
                    defAdjustY=t.adjustY;
                    defAdjustZ=t.adjustZ;
                    defShouldRiderSit=t.shouldRiderSit;
                    break;
                }
            }
        }

        flightemConfigAdjustment.size=getFloatSafe(tagCompound,"size",defSize);
        flightemConfigAdjustment.angleX=getFloatSafe(tagCompound,"angleX",defAngleX);
        flightemConfigAdjustment.angleY=getFloatSafe(tagCompound,"angleY",defAngleY);
        flightemConfigAdjustment.angleZ=getFloatSafe(tagCompound,"angleZ",defAngleZ);
        flightemConfigAdjustment.adjustX=getFloatSafe(tagCompound,"adjustX",defAdjustX);
        flightemConfigAdjustment.adjustY=getFloatSafe(tagCompound,"adjustY",defAdjustY);
        flightemConfigAdjustment.adjustZ=getFloatSafe(tagCompound,"adjustZ",defAdjustZ);
        flightemConfigAdjustment.shouldRiderSit=getFloatSafe(tagCompound,"shouldRiderSit",defShouldRiderSit);

        return flightemConfigAdjustment;
    }
    public static float getFloatSafe(NBTTagCompound nbtTagCompound,String key,float defalt){
        if (nbtTagCompound.hasKey(key)){
            return nbtTagCompound.getFloat(key);
        }else return defalt;
    }
    public static String getStringSafe(NBTTagCompound nbtTagCompound,String key,String defalt){
        if (nbtTagCompound.hasKey(key)){
            return nbtTagCompound.getString(key);
        }else return defalt;
    }
}
