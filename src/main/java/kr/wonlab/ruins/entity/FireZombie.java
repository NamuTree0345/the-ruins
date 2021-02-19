package kr.wonlab.ruins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class FireZombie extends ZombieEntity {
    public FireZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }
}
