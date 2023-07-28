package com.Hileb.dlightord.entity;

import com.Hileb.dlightord.network.packet.PacketPlayerButtonState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class FlightordSwordEntity extends Entity {
    public ItemStackHandler itemSword=new ItemStackHandler();
    public FlightordSwordEntity(World worldIn) {
        super(worldIn);
    }
    @Override
    protected void entityInit() {

    }
    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        itemSword.deserializeNBT(compound.getCompoundTag("item"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setTag("item",itemSword.serializeNBT());
    }

    private int movT=0;
    private int movF=0;
    public void onMessage(PacketPlayerButtonState message){
        int vx=0;
        int vy=0;

        if (message.wB) vx++;
        if (message.sB) vx--;
        if (message.aB) vy--;
        if (message.dB) vy++;

        movT=vx;
        movF=vy;
    }

    @Override
    public void onUpdate() {
        //pre move
        float yaw=this.rotationYaw;
        Vec3d vec=Vec3d.fromPitchYaw(0,yaw);
        double xF=vec.x/vec.lengthVector();
        double zF=vec.z/vec.lengthVector();

        if (motionX*motionX + motionZ*motionZ <=100){
            motionX+=xF*movT*1;
            motionZ+=zF*movT*1;
        }
        if (motionY*motionY<=36){
            motionY+=movF;
        }
        motionY++;
        //move end
        super.onUpdate();
        //ride
        if (isBeingRidden()){
            Entity driver=getPassengers().get(0);
            this.rotationYaw=driver.rotationYaw;
        }
    }
}
