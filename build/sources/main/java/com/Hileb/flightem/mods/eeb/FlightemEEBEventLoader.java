package com.Hileb.flightem.mods.eeb;

import com.Hileb.flightem.entity.FlightemEntity;
import committee.nova.plr.elytraBombing.ElytraBombing;
import committee.nova.plr.elytraBombing.common.tools.player.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.MessageFormat;

/**
 * @Project Flightord
 * @Author Hileb
 * @Date 2023/7/31 16:37
 **/

public class FlightemEEBEventLoader {
    public FlightemEEBEventLoader(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Optional.Method(modid= ElytraBombing.MOD_ID)
    @SubscribeEvent
    public void onAssessmentIsFlying(FlightemAPIEEBIsFlyingEvent event){
        final EntityPlayer player = event.getEntityPlayer();
        if (player!=null && player.isRiding() && player.getRidingEntity() instanceof FlightemEntity){
            event.isFlying=true;
        }
    }
    @Optional.Method(modid= ElytraBombing.MOD_ID)
    @SubscribeEvent
    public void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
        final EntityPlayer player = event.getEntityPlayer();
        FlightemAPIEEBIsFlyingEvent event1=new FlightemAPIEEBIsFlyingEvent(player);
        MinecraftForge.EVENT_BUS.post(event1);
        if (!event1.isFlying || player.isElytraFlying()) {
            return;
        }
        final ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) {
            return;
        }
        final Item igniter = stack.getItem();
        if (!(igniter == Items.FLINT_AND_STEEL) && !(igniter == Items.FIRE_CHARGE)) {
            return;
        }
        final ItemStack tnt = PlayerHandler.searchFor(player, new ItemStack(Blocks.TNT).getItem(), 0);
        if (tnt.isEmpty()) {
            return;
        }
        final Item tntItem = tnt.getItem();
        if (player.getCooldownTracker().hasCooldown(tntItem)) {
            final int cd = (int) (player.getCooldownTracker().getCooldown(tntItem, 0) * 60);
            final boolean isPlural = cd > 1;
            if (!player.world.isRemote) {
                player.sendMessage(new TextComponentString(
                        MessageFormat.format(new TextComponentTranslation("msg.ebb.cd").getFormattedText(),
                                cd + "",
                                isPlural ? new TextComponentTranslation("msg.ebb.unit.plural_suffix").getFormattedText() : "")
                ));
            }
            return;
        }
        PlayerHandler.launchTnt(player, stack, tnt);
    }
}
