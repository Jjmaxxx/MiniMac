package tools;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
    private Set<Subscriber> subscribers = new HashSet<Subscriber>();
    public void notifySubscribers(){
        for(Subscriber s: subscribers) s.update();
    }
    public void subscribe(Subscriber s) { subscribers.add(s); }
    public void unsubscribe(Subscriber s) { subscribers.remove(s); }
}
