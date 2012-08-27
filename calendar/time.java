package calendar;

import java.io.Serializable;

/**
 *
 * @author j0ker
 */
public class time implements Serializable{
    
    private boolean am, exists;
    private String time;
    
    public time(String t, boolean a) {
        am = a;
        time = t;
        exists = true;
    }
    
    public time() {
        exists = false;
    }
    
    public String getTime() {
        if (time != null) {
            if (am) {
                return time + " " + "AM";
            } else {
                return time + " " + "PM";
            }
        }
        return null;
    }
    
    public boolean remind() {
        return exists;
    }
}