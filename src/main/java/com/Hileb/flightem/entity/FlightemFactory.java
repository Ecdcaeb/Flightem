package com.Hileb.flightem.entity;

import com.Hileb.flightem.FlightemModMain;
import com.Hileb.flightem.config.FlightemModConfigHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/28 16:32
 **/
@Mod.EventBusSubscriber(modid= FlightemModMain.MODID)
public class FlightemFactory {
    @SubscribeEvent
    public static void onItemEntitySpawn(EntityJoinWorldEvent event){
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getEntity() instanceof EntityItem){
                EntityItem entityItem=(EntityItem) event.getEntity();
                ItemStack stack=entityItem.getItem().copy();
                if (!stack.isEmpty()){
                    if (FlightemModConfigHelper.acceptItem(stack.getItem())){
                        entityItem.setItem(ItemStack.EMPTY);
                        entityItem.setDead();

                        FlightemEntity entity=new FlightemEntity(world);
                        entity.setItemStack(stack);
                        entity.setLocationAndAngles(entityItem.posX,entityItem.posY,entityItem.posZ,entityItem.rotationYaw,entityItem.rotationPitch);
                        world.spawnEntity(entity);
                    }
                }
            }
        }
    }
}
