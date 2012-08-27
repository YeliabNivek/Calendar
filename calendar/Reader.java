package calendar;

import calendar.UI.Window;
import calendar.util.Utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author j0ker
 */
public class Reader {

    private String working = "";

    public void add(int e) {
        working = working + e + "";
    }

    public void read(String file) {
        try {
            FileInputStream saveFile = new FileInputStream(file);
            ObjectInputStream save = new ObjectInputStream(saveFile);
            int a = (int) save.readObject();
            for (int i = 0; i < a; i++) {
                Date d = (Date) save.readObject();
                Date t;
                try {
                    int te = Utilities.processDate(d);
                    t = Window.special.get(te);
                    Window.special.remove(te);
                } catch (Exception e) {
                    t = d;
                }
                CopyOnWriteArrayList<event> evt = d.getAllEvents();
                for (event w : evt) {
                    t.addEvent(w);
                }
                Window.special.add(t);
            }
            save.close();
        } catch (Exception e) {
        }
    }

    public void write(String file, ArrayList<Date> special) {
        File f = new File(file);
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException ex) {
        }
        try {
            FileOutputStream saveFile = new FileOutputStream(file);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(special.size());
            for (Date temp : special) {
                save.writeObject(temp);
            }
            save.close();
        } catch (Exception e) {
        }
    }

    public String getLine() {
        working = "";
        return working;
    }
}