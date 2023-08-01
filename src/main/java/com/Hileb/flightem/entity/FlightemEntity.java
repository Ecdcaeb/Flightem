package com.Hileb.flightem.entity;

import com.Hileb.flightem.FlightemModMain;
import com.Hileb.flightem.config.FlightemConfigAdjustment;
import com.Hileb.flightem.network.packet.FlightemPacketPlayerButtonState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class FlightemEntity extends EntityLiving {
    public static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(FlightemEntity.class, DataSerializers.ITEM_STACK);

    public FlightemEntity(World worldIn) {
        super(worldIn);
        this.setSize(0.98F, 0.7F);
    }

    public FlightemEntity setItemStack(ItemStack stack){
        this.getDataManager().set(ITEM, stack);
        this.getDataManager().setDirty(ITEM);
        return this;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
    }

    public ItemStack getItemStack(){
        return this.getDataManager().get(ITEM);
    }

    @Override
    protected void entityInit(){
        super.entityInit();
        this.getDataManager().register(ITEM, ItemStack.EMPTY);
    }
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        NBTTagCompound stackNBT = compound.getCompoundTag("Item");
        setItemStack(new ItemStack(stackNBT));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setTag("Item", getItemStack().writeToNBT(new NBTTagCompound()));
    }

    private int movT=0;
    private int movF=0;
    public void onMessage(FlightemPacketPlayerButtonState message){
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
        motionY=0;

        if (motionX*motionX + motionZ*motionZ <=100){
            motionX+=xF*movT*0.1;
            motionZ+=zF*movT*0.1;
        }
        if (motionY*motionY<=36){
            motionY+=movF*0.1;
        }
        if (motionX*motionX>=25){
            motionX=motionX>=0?5:-5;
        }
        if (motionZ*motionZ>=16){
            motionZ=motionZ>=0?5:-5;
        }
        if (motionY*motionY>=16){
            motionY=motionY>=0?5:-5;
        }
        //move end
        double d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        super.onUpdate();
        double d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        //ride
        if (isBeingRidden()){
            if (hasDriver()){
                Entity driver=this.getDriver();
                this.rotationYaw=driver.rotationYaw;
            }
            if (this.collidedHorizontally && !this.world.isRemote)
            {
                double d3 = d8 - d11;
                float f5 = (float)(d3 * 10.0D - 3.0D);

                if (f5 > 0.0F)
                {
                    this.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 1.0F, 1.0F);
                    this.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
                }
            }
        }
    }


    @Override
    public double getMountedYOffset() {
        if (FlightemConfigAdjustment.REGISTERS.containsKey(getItemStack().getItem())){
            return FlightemConfigAdjustment.REGISTERS.get(getItemStack().getItem()).adjustY;
        }else return 0d;
    }
    @Override
    public boolean shouldRiderSit() {
        if (FlightemConfigAdjustment.REGISTERS.containsKey(getItemStack().getItem())){
            return FlightemConfigAdjustment.REGISTERS.get(getItemStack().getItem()).shouldRiderSit==1f;
        }else return false;
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return !isBeingRidden() || getPassengers().size()<2;
    }
    public boolean hasDriver(){
        return getPassengers().size()>=1;
    }
    public Entity getDriver(){
        return getPassengers().get(0);
    }
    public boolean hasPassenger(){
        return getPassengers().size()>=2;
    }
    public Entity getPassenger(){
        return getPassengers().get(1);
    }
    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 2;
    }
    @Override
    public boolean canBePushed() {
        return true;
    }
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeAttackedWithItem() {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer) source.getTrueSource();
            if (!player.world.isRemote){
                if (player.getHeldItemMainhand().isEmpty()){
                    player.setHeldItem(EnumHand.MAIN_HAND,getItemStack().copy());
                    this.setDead();
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (player.world.isRemote)return true;
        else {
            if (player.isSneaking()){
                if (player.getHeldItem(hand).isEmpty()){
                    player.setHeldItem(hand,getItemStack().copy());
                    this.setDead();
                    return true;
                }
            }
            if (canBeRidden(player)){
                player.startRiding(this);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean canRiderInteract() {
        return true;
    }
}
