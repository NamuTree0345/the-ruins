package kr.wonlab.ruins.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityMixinAccessor {

    private boolean isFrozen = false;

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        isFrozen = tag.getBoolean("Frozen");
    }

    /*
    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void tickMovement(CallbackInfo ci) {
        if(isFrozen) {
            return;
        }
    }
     */

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("Frozen", isFrozen);
    }

    @Override
    public boolean isFrozen() {
        return isFrozen;
    }

    @Override
    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }
}
