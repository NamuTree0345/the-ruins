package kr.wonlab.ruins.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.util.Identifier;

public class FireZombieRenderer extends ZombieBaseEntityRenderer<FireZombie, FireZombieModel<FireZombie>> {

    public FireZombieRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new FireZombieModel<>(0.0F, 0.0F, 64, 64), new FireZombieModel<>(0.5F, true), new FireZombieModel<>(1.0F, true));
    }

    @Override
    public Identifier getTexture(FireZombie entity) {
        return new Identifier("ruins", "textures/entity/fire_zombie/fire_zombie.png");
    }
}
