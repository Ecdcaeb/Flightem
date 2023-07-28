package com.Hileb.lnrepair_2317;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = IdlFramework.MODID)
public class Tooltip {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltp(ItemTooltipEvent event){
        if (event.getEntityPlayer()==null || event.getEntityPlayer().world==null || !event.getEntityPlayer().world.isRemote)return;
        List<String> s1=new ArrayList<>();
        for(String s2:event.getToolTip()){
            String s3="";
             for(int i=0;i<s2.length();i++){
                 if (s2.toCharArray()[i]=='\\' && s2.toCharArray()[i+1]=='n' && i<s2.length()-1){
                     s1.add(s3);
                     s3="";
                     i++;
                     continue;
                 }
                 if (s2.toCharArray()[i]=='\n'){
                     s1.add(s3);
                     s3="";
                     continue;
                 }
                 s3=s3+s2.toCharArray()[i];
             }
             s1.add(s3);
        }
        event.getToolTip().clear();
        for(String s4:s1){
            event.getToolTip().add(s4);
        }
    }
}
