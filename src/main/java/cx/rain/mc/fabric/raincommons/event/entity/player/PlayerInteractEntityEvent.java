package cx.rain.mc.fabric.raincommons.event.entity.player;

import cx.rain.mc.fabric.raincommons.event.EventBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class PlayerInteractEntityEvent extends EventBase {
    public Entity target;
    public PlayerEntity player;
    public Hand hand;

    public PlayerInteractEntityEvent(Entity targetIn, PlayerEntity playerIn, Hand handIn) {
        target = targetIn;
        player = playerIn;
        hand = handIn;
    }
}
