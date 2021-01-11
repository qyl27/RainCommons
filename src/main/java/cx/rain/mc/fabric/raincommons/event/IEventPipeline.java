package cx.rain.mc.fabric.raincommons.event;

import cx.rain.mc.fabric.raincommons.event.args.EventArgs;

public interface IEventPipeline {
    public void subscribe(IEventListener obj);

    public boolean invoke(Object sender, EventArgs event);
}
