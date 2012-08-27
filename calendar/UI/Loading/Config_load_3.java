package calendar.UI.Loading;

import calendar.Date;
import calendar.UI.Window;
import calendar.time;
import calendar.util.Utilities;
import java.util.ArrayList;

/**
 *
 * @author j0ker
 */
public class Config_load_3 extends Thread {
    
    ArrayList<String> d;
    String des;

    public Config_load_3(ArrayList<String> date, String description) {
        d = date;
        des = description;
    }

    @Override
    public void run() {
        for (int i = 0; i < d.size(); i++) {
            Date temp;
            try {
                temp = Window.special.get(Utilities.processDate(new Date(d.get(i), Integer.parseInt(d.get(i).substring(d.get(i).indexOf("/") + 1, d.get(i).lastIndexOf("/"))))));
            } catch (Exception e) {
                temp = new Date(d.get(i), Integer.parseInt(d.get(i).substring(d.get(i).indexOf("/") + 1, d.get(i).lastIndexOf("/"))));
            }
            temp.createEvent(des, new time());
            Loading_conf.curr = temp.getDate() + " " + ((int) (((double) i / d.size()) * 100) + 1) + "% done";
            temp.refresh();
        }
        stop();
    }
}