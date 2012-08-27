package calendar.UI.Loading;

import calendar.Calendar;
import calendar.Date;
import calendar.time;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author j0ker
 */
public class Config_load_4 extends Thread{
    
    ArrayList<String> d;
    String des;

    public Config_load_4(ArrayList<String> date, String description) {
        d = date;
        des = description;
    }

    @Override
    public void run() {
        ArrayList<Date> write = new ArrayList<>();
        for (int i = 0; i < d.size(); i++) {
            Date temp = new Date(d.get(i), Integer.parseInt(d.get(i).substring(d.get(i).indexOf("/") + 1, d.get(i).lastIndexOf("/"))));
            temp.createEvent(des, new time());
            Loading_conf.curr = temp.getDate() + " " + ((int) (((double) i / d.size()) * 100) + 1) + "% done";
            write.add(temp);
        }
        Calendar.r.write(des + ".cal", write);
        JOptionPane.showMessageDialog(null, "Config File created to same directory as this file.", "Config file generated", JOptionPane.INFORMATION_MESSAGE);
        stop();
    }
}