package cx.rain.mc.fabric.raincommons.event.entity.player;

import cx.rain.mc.fabric.raincommons.event.entity.EntityMoveEvent;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PlayerMoveEvent extends EntityMoveEvent {
    public PlayerMoveEvent(PlayerEntity entityIn, World worldIn, Vec3d directionIn, MovementType typeIn) {
        super(entityIn, worldIn, directionIn, typeIn);
    }
}
