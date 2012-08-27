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
public class Config_load_1 extends Thread {

    ArrayList<String> d;
    String t, des;
    boolean a;

    public Config_load_1(ArrayList<String> date, String time, boolean am, String description) {
        d = date;
        t = time;
        a = am;
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
            temp.createEvent(des, new time(t, a));
            Loading_conf.curr = temp.getDate() + " " + ((int) (((double) i / d.size()) * 100) + 1) + "% done";
            temp.refresh();
        }
        stop();
    }
}