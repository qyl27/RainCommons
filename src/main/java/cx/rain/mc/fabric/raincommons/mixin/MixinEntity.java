package cx.rain.mc.fabric.raincommons.mixin;

import cx.rain.mc.fabric.raincommons.event.callback.entity.EntityDamageCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.EntityMoveCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerInteractAtEntityCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerInteractEntityCallback;
import cx.rain.mc.fabric.raincommons.event.callback.entity.player.PlayerMoveCallback;
import cx.rain.mc.fabric.raincommons.event.entity.EntityDamageEvent;
import cx.rain.mc.fabric.raincommons.event.entity.EntityMoveEvent;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerInteractAtEntityEvent;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerInteractEntityEvent;
import cx.rain.mc.fabric.raincommons.event.entity.player.PlayerMoveEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
    }

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void beforeMovePlayer(MovementType type, Vec3d movement, CallbackInfo ci) {
        if (((Entity)(Object) this) instanceof PlayerEntity) {
            if (PlayerMoveCallback.EVENT.invoker()
                    .accept(new PlayerMoveEvent((PlayerEntity)(Object) this, world, movement, type))
                    == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void beforeInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> ci) {
        if (PlayerInteractEntityCallback.EVENT.invoker()
                .accept(new PlayerInteractEntityEvent((Entity)(Object) this, player, hand)) == ActionResult.FAIL) {
            ci.cancel();
        }
    }

    @Inject(method = "interactAt", at = @At("HEAD"), cancellable = true)
    public void beforeInteractAt(PlayerEntity player, Vec3d hitPos, Hand hand,
                                 CallbackInfoReturnable<ActionResult> cir) {
        if (PlayerInteractAtEntityCallback.EVENT.invoker()
                .accept(new PlayerInteractAtEntityEvent((Entity)(Object) this, player, hitPos, hand))
                == ActionResult.FAIL) {
            cir.cancel();
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void beforeDamage(DamageSource source, float amount,
                             CallbackInfoReturnable<Boolean> cir) {
        if (EntityDamageCallback.EVENT.invoker()
                .accept(new EntityDamageEvent(source, (Entity)(Object) this, amount)) == ActionResult.FAIL) {
            cir.cancel();
        }
    }
}
