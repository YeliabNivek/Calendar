package calendar;

import java.io.Serializable;

/**
 *
 * @author j0ker
 */
public class event implements Serializable {
    
    private String description;
    private time time;
    private boolean first = true;
    
    public event(String d, time t) {
        description = d;
        time = t;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getTime() {
        return time.getTime();
    }
    
    public time getTimeInstance() {
        return time;
    }
    
    public boolean allow() {
        if (first) {
            first = false;
            return true;
        }
        return first;
    }
    
    public boolean equals(event w) {
        if (description.compareTo(w.getDescription()) == 0 && time.getTime().compareTo(w.getTime()) == 0) {
            return true;
        }
        return false;
    }
}
