package cx.rain.mc.fabric.raincommons.event.entity.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class PlayerInteractAtEntityEvent extends PlayerInteractEntityEvent {
    public Vec3d hitPos;

    public PlayerInteractAtEntityEvent(Entity target, PlayerEntity playerIn, Vec3d hitPosIn, Hand handIn) {
        super(target, playerIn, handIn);
        hitPos = hitPosIn;
    }
}
