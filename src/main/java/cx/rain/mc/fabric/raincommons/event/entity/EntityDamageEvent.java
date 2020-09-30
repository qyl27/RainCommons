package cx.rain.mc.fabric.raincommons.event.entity;

import cx.rain.mc.fabric.raincommons.event.EventBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

public class EntityDamageEvent extends EventBase {
    public DamageSource source;
    public Entity entity;
    public float damage;

    public EntityDamageEvent(DamageSource sourceIn, Entity entityIn, float damageIn) {
        source = sourceIn;
        entity = entityIn;
        damage = damageIn;
    }
}
