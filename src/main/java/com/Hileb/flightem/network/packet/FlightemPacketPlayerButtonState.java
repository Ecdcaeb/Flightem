package com.Hileb.flightem.network.packet;

import com.Hileb.flightem.entity.FlightemEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FlightemPacketPlayerButtonState implements IMessage {
    public boolean wB=false;
    public boolean sB=false;
    public boolean aB=false;
    public boolean dB=false;




    public FlightemPacketPlayerButtonState(){

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        wB=buf.readBoolean();
        sB=buf.readBoolean();
        aB=buf.readBoolean();
        dB=buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(wB);
        buf.writeBoolean(sB);
        buf.writeBoolean(aB);
        buf.writeBoolean(dB);
    }

    public static class Handler implements IMessageHandler<FlightemPacketPlayerButtonState, IMessage> {

        @Override
        public IMessage onMessage(FlightemPacketPlayerButtonState message, MessageContext ctx) {
            EntityPlayerMP playerMP=ctx.getServerHandler().player;
            if (playerMP!=null && playerMP.isRiding() && playerMP.getRidingEntity() instanceof FlightemEntity){
                FlightemEntity entity=(FlightemEntity) playerMP.getRidingEntity();
                entity.onMessage(message);
            }
            return null;
        }
    }
}
