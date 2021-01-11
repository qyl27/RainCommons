package cx.rain.mc.fabric.raincommons.event;

import cx.rain.mc.fabric.raincommons.event.args.EventArgs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CommonsEvent implements IEventPipeline {
    private static final List<Method> SUBSCRIBER_HIGHEST = new ArrayList<>();
    private static final List<Method> SUBSCRIBER_HIGH = new ArrayList<>();
    private static final List<Method> SUBSCRIBER_DEFAULT = new ArrayList<>();
    private static final List<Method> SUBSCRIBER_LOW = new ArrayList<>();
    private static final List<Method> SUBSCRIBER_LOWEST = new ArrayList<>();

    private CommonsEvent() {
    }

    @Override
    public void subscribe(IEventListener obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                EventSubscriber subscriber = method.getAnnotation(EventSubscriber.class);
                internalSubscribe(method, subscriber.priority());
            }
        }
    }

    private void internalSubscribe(Method method, SubscriberPriority priority) {
        checkSubscribe(method);

        switch (priority) {
            case HIGHEST:
                SUBSCRIBER_HIGHEST.add(method);
                break;
            case HIGH:
                SUBSCRIBER_HIGH.add(method);
                break;
            case DEFAULT:
                SUBSCRIBER_DEFAULT.add(method);
                break;
            case LOW:
                SUBSCRIBER_LOW.add(method);
                break;
            case LOWEST:
                SUBSCRIBER_LOWEST.add(method);
                break;
            default:
        }
    }

    private void checkSubscribe(Method method) {
        Class<?>[] params = method.getParameterTypes();
        if (params.length != 2) {
            throw new IllegalArgumentException("Method " + method + "is an event subscriber but has "
                    + params.length + " argument(s). It should be 2 (Object, EventArgs). ");
        }

        if (params[0] != Object.class) {
            throw new IllegalArgumentException("Method " + method +
                    "is an event subscriber but the first parameter is"
                    + params[0] + " . It should be Object.");
        }

        if (params[1] != EventArgs.class) {
            throw new IllegalArgumentException("Method " + method +
                    "is an event subscriber but the second parameter is"
                    + params[1] + " . It should be Object.");
        }
    }

    @Override
    public boolean invoke(Object sender, EventArgs event) {

        return false;
    }
}
