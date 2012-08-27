package calendar;

import calendar.UI.Description;
import calendar.UI.Event;
import calendar.UI.Window;
import calendar.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author j0ker
 */
public class Date implements Serializable {

    private String date;
    private boolean event = false;
    private int days;
    CopyOnWriteArrayList<event> events = new CopyOnWriteArrayList<>();

    public Date(String day, int cal) {
        setDate(day); //mine as well use the methods we make
        days = cal;
    }

    public Date() {
        days = -1;
    }

    public void setDate(String day) {
        date = day;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return date.substring(0, date.indexOf("/"));
    }

    public String getYear() {
        return date.substring(date.lastIndexOf("/") + 1);
    }

    public int getDay() {
        return days;
    }

    @Override
    public String toString() {
        if (days != -1) {
            return days + "";
        }
        return "";
    }

    public boolean hasEvent() {
        return event;
    }

    public void setEvent(boolean b) {
        event = b;
    }

    public void addEvent() {
        Event v = new Event(this);
        v.setVisible(true);
    }

    public void addEvent(event e) {
        if (!exists(e)) {
            events.add(e);
            setEvent(true);
        }
    }

    public void createEvent(String description, time t) {
        event e = new event(description, t);
        if (!exists(e)) {
            events.add(e);
            setEvent(true);
        }
    }

    public boolean exists(event e) {
        return (events.indexOf(e) >= 0);
    }

    public void refresh() {
        Date d = this;
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
        Calendar.r.write("settings.cal", Window.special);
        Window.refresh_board = true;
    }

    public void description() {
        Description d = new Description(date, events);
        d.setVisible(true);
    }

    public boolean check(String time) {
        for (int i = 0; i < events.size(); i++) {
            try {
                if (events.get(i).getTime().compareTo(time) == 0) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public String getDescriptionFromTime(String time) {
        for (int i = 0; i < events.size(); i++) {
            try {
                if (events.get(i).getTime().compareTo(time) == 0) {
                    return events.get(i).getDescription();
                }
            } catch (Exception e) {
            }
        }
        throw new java.lang.ArrayStoreException("Nothing at the time");
    }

    public event getEventAtTime(String time) {
        for (int i = 0; i < events.size(); i++) {
            try {
                if (events.get(i).getTime().compareTo(time) == 0) {
                    return events.get(i);
                }
            } catch (Exception e) {
            }
        }
        throw new java.lang.ArrayStoreException("Nothing at the time");
    }

    public CopyOnWriteArrayList<event> getAllEvents() {
        return events;
    }

    public ArrayList<String> getLables() {
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            String curr = events.get(i).getDescription();
            if (curr.length() <= 14) {
                s.add("•" + curr);
            } else {
                s.add("•" + curr.substring(0, 14));
                curr = curr.substring(14);
                while (curr.length() > 14) {
                    s.add(curr.substring(0, 15));
                    curr = curr.substring(15);
                }
                s.add(curr);
            }
        }
        return s;
    }

    public ArrayList<String> getLables(int index) {
        ArrayList<String> s = new ArrayList<>();
        for (int i = getNum2(index); i < getNum(index); i++) {
            try {
                String curr = events.get(i).getDescription();
                if (curr.length() <= 14) {
                    s.add("•" + curr);
                } else {
                    s.add("•" + curr.substring(0, 14));
                    curr = curr.substring(14);
                    while (curr.length() > 14) {
                        s.add(curr.substring(0, 15));
                        curr = curr.substring(15);
                    }
                    s.add(curr);
                }
            } catch (Exception e) {
                break;
            }
        }
        return s;
    }

    private int getNum(int in) {
        if (in == 1) {
            return 23;
        }
        return 50;
    }

    private int getNum2(int in) {
        if (in == 1) {
            return 0;
        }
        return 23 + ((in - 2) * 50);
    }

    public void clearEvents() {
        events.clear();
        setEvent(false);
    }
}