package cx.rain.mc.fabric.raincommons.mixin;

import cx.rain.mc.fabric.raincommons.event.callback.entity.EntityMoveCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerMoveCallback;
import cx.rain.mc.fabric.raincommons.event.entity.EntityMoveEvent;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerMoveEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public World world;

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void beforeMove(MovementType type, Vec3d movement, CallbackInfo ci) {
        if (EntityMoveCallback.EVENT.invoker()
                .accept(new EntityMoveEvent((Entity)(Object) this, world, movement, type)) == ActionResult.FAIL) {
            ci.cancel();
        }

        if (((Entity)(Object) this) instanceof PlayerEntity) {
            if (PlayerMoveCallback.EVENT.invoker()
                    .accept(new PlayerMoveEvent((PlayerEntity)(Object) this, world, movement, type))
                            == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }
}
