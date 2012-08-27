package calendar.util;

import calendar.UI.Window;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author j0ker
 */
public class Utilities {

    public static String getCurrentDay() {
        switch (new SimpleDateFormat("E").format(new Date())) {
            case "Mon":
                return "Monday";
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Fri":
                return "Friday";
            case "Sat":
                return "Saturday";
            default:
                return "Thursday";
        }
    }

    public static String getCurrentMonth() {
        switch (Integer.parseInt(new SimpleDateFormat("M").format(new Date()))) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }

    public static String getCurrentYear() {
        return new SimpleDateFormat("y").format(new Date());
    }

    public static String getDate() {
        return getCurrentMonth() + "/" + new SimpleDateFormat("d").format(new Date()) + "/" + getCurrentYear();
    }

    public static calendar.Date[][] genCurrentMonth() {
        int c = getCounter1(), cc = getCounter2();
        calendar.Date[][] dates = new calendar.Date[5][7];
        for (int i = 0; i < dates.length; i++) {
            for (int ii = 0; ii < dates[0].length; ii++) {
                if (c <= cc && c > 0) {
                    try {
                        dates[i][ii] = Window.special.get(processDate(new calendar.Date(Window.currentMonth + "/" + c + "/" + Window.currentYear, c)));
                    } catch (Exception e) {
                        dates[i][ii] = new calendar.Date(Window.currentMonth + "/" + c + "/" + Window.currentYear, c);
                    }
                } else {
                    dates[i][ii] = new calendar.Date();
                }
                c++; //programming humor
            }
        }
        return dates;
    }

    public static ArrayList<String> genBlankMonth(String month, String year, int start) {
        int cc = getCounter2(month, year);
        ArrayList<String> dates = new ArrayList<>();
        for (int i = start; i <= cc; i++) {
            dates.add(month + "/" + i + "/" + year);
        }
        return dates;
    }

    public static ArrayList<String> genBlankMonth(String month, String year, int start, int stop) {
        ArrayList<String> dates = new ArrayList<>();
        for (int i = start; i <= stop; i++) {
            dates.add(month + "/" + i + "/" + year);
        }
        return dates;
    }

    public static calendar.Date[] genCurrentWeek(int start) {
        calendar.Date[] dates = new calendar.Date[7];
        int c = getCounter2(), cc = getCounter3();
        for (int i = 0; i < 7; i++) {
            if (start > 0 && start <= c) {
                try {
                    dates[i] = Window.special.get(processDate(new calendar.Date(Window.currentMonth + "/" + start + "/" + Window.currentYear, start)));
                } catch (Exception e) {
                    dates[i] = new calendar.Date(Window.currentMonth + "/" + start + "/" + Window.currentYear, start);
                }
            } else if (start <= 0) {
                try {
                    dates[i] = Window.special.get(processDate(new calendar.Date(back(Window.currentMonth) + "/" + (start + cc) + "/" + backY(Window.currentYear, back(Window.currentMonth)), (start + cc))));
                } catch (Exception e) {
                    dates[i] = new calendar.Date(back(Window.currentMonth) + "/" + (start + cc) + "/" + backY(Window.currentYear, back(Window.currentMonth)), (start + cc));
                }
            } else if (start > c) {
                try {
                    dates[i] = Window.special.get(processDate(new calendar.Date(forward(Window.currentMonth) + "/" + (start - c) + "/" + forwardY(Window.currentYear, forward(Window.currentMonth)), (start - c))));
                } catch (Exception e) {
                    dates[i] = new calendar.Date(forward(Window.currentMonth) + "/" + (start - c) + "/" + forwardY(Window.currentYear, forward(Window.currentMonth)), (start - c));
                }
            }
            start++;
        }
        return dates;
    }

    //index of where the first day of the month starts 0 = Sunday, 1 = Monday , 6 = Saturday
    private static int getCounter1() {
        Calendar temp = new GregorianCalendar(Integer.parseInt(Window.currentYear), getMonth(Window.currentMonth) - 1, 1);
        return (-temp.get(Calendar.DAY_OF_WEEK)) + 2;
    }

    //Last day of the month
    private static int getCounter2() {
        Calendar temp = new GregorianCalendar(Integer.parseInt(Window.currentYear), getMonth(Window.currentMonth) - 1, 1);
        return temp.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //index of where the first day of the month starts 0 = Sunday, 1 = Monday , 6 = Saturday
    private static int getCounter1(String month, String year) {
        Calendar temp = new GregorianCalendar(Integer.parseInt(year), getMonth(month) - 1, 1);
        return (-temp.get(Calendar.DAY_OF_WEEK)) + 2;
    }

    //Last day of the month
    private static int getCounter2(String month, String year) {
        Calendar temp = new GregorianCalendar(Integer.parseInt(year), getMonth(month) - 1, 1);
        return temp.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //Last day of month before
    private static int getCounter3() {
        Calendar temp = new GregorianCalendar(Integer.parseInt(Window.currentYear), getMonth(Window.currentMonth) - 2, 1);
        return temp.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int processDate(calendar.Date d) {
        for (int i = 0; i < Window.special.size(); i++) {
            if (d.getDate().compareTo(Window.special.get(i).getDate()) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static String back(String m) {
        switch (m) {
            case "January":
                return "December";
            case "February":
                return "January";
            case "March":
                return "February";
            case "April":
                return "March";
            case "May":
                return "April";
            case "June":
                return "May";
            case "July":
                return "June";
            case "August":
                return "July";
            case "September":
                return "August";
            case "October":
                return "September";
            case "November":
                return "October";
            default:
                return "November";
        }
    }

    public static String forward(String m) {
        switch (m) {
            case "January":
                return "February";
            case "February":
                return "March";
            case "March":
                return "April";
            case "April":
                return "May";
            case "May":
                return "June";
            case "June":
                return "July";
            case "July":
                return "August";
            case "August":
                return "September";
            case "September":
                return "October";
            case "October":
                return "November";
            case "November":
                return "December";
            default:
                return "January";
        }
    }

    public static int getMonth(String m) {
        switch (m) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            default:
                return 12;
        }
    }

    public static String getMonth(int m) {
        switch (m) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }

    public static String backY(String year, String month) {
        if (month.compareTo("December") == 0) {
            return (Integer.parseInt(year) - 1) + "";
        }
        return year;
    }

    public static String forwardY(String year, String month) {
        if (month.compareTo("January") == 0) {
            return (Integer.parseInt(year) + 1) + "";
        }
        return year;
    }

    public static int getDayfromDate(String month, int day, String year) {
        Calendar date = new GregorianCalendar(Integer.parseInt(year), getMonth(month) - 1, day);
        return date.get(Calendar.DAY_OF_WEEK);
    }

    public static int getNumberOfMonthsDifference(int month_s, int year_s, int month_e, int year_e) {
        int r = 0;
        if (year_s == year_e) {
            if (month_s < month_e) {
                while (month_s < month_e) {
                    r++;
                    month_s++;
                }
            }
        } else if (year_s < year_e) {
            while (year_s < year_e) {
                while (month_s < 12) {
                    r++;
                    month_s++;
                }
                year_s++;
                month_s = 1;
            }
            while (month_s < month_e) {
                r++;
                month_s++;
            }
        }
        return r;
    }

    public static ArrayList<Point> getAllMonths(int month_s, int year_s, int month_e, int year_e) {
        ArrayList<Point> temp = new ArrayList<>();
        if (year_s == year_e) {
            if (month_s < month_e) {
                while (month_s < month_e) {
                    temp.add(new Point(month_s, year_s));
                    month_s++;
                }
            }
        } else if (year_s < year_e) {
            System.out.println("here");
            while (year_s < year_e) {
                while (month_s < 13) {
                    temp.add(new Point(month_s, year_s));
                    month_s++;
                }
                year_s++;
                month_s = 1;
            }
            while (month_s <= month_e) {
                temp.add(new Point(month_s, year_s));
                month_s++;
            }
        }
        return temp;
    }
}