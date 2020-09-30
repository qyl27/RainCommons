package cx.rain.mc.fabric.raincommons.event.entity.player;

import cx.rain.mc.fabric.raincommons.event.EventBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class PlayerChatEvent extends EventBase {
    public PlayerEntity player;
    public Text text;

    public PlayerChatEvent(PlayerEntity playerIn, Text textIn) {
        text = textIn;
        player = playerIn;
    }
}
