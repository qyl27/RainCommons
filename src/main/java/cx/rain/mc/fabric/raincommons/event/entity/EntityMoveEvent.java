package cx.rain.mc.fabric.raincommons.event.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMoveEvent {
    public Entity entity;
    public World world;
    public Vec3d direction;
    public MovementType type;

    public EntityMoveEvent(Entity entityIn, World worldIn, Vec3d directionIn, MovementType typeIn) {
        entity = entityIn;
        world = worldIn;
        direction = directionIn;
        type = typeIn;
    }
}
