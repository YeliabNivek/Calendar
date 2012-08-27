package calendar.UI;

import calendar.Calendar;
import calendar.CellRenderer;
import calendar.Date;
import calendar.Init;
import calendar.UI.Loading.Loading_main;
import calendar.util.Utilities;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author j0ker
 */
public class Window extends javax.swing.JFrame {

    static JPopupMenu jj;
    static int x, y;
    public static ArrayList<Date> special = new ArrayList<>();
    static Date[][] dates;
    public static String currentMonth, currentYear;
    public static boolean goOn = false;
    public static int state = 0;
    public static Date[] week = new Date[7];
    static int week_c = 1;
    public static int h_w;
    public static boolean refresh_board = false;

    public Window() {
        initComponents();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        try {
            setIconImage(ImageIO.read(Window.class.getResource("/calendar/UI/icon.png")));
        } catch (IOException ex) {
        }
        setTitle(Utilities.getCurrentDay() + " - " + Utilities.getCurrentMonth() + " - " + Utilities.getCurrentYear());
        setVisible(true);
        init();
        while (!goOn) {
            System.out.print(""); //I don't know why this is needed
        }
        int c = 1;
        currentMonth = Utilities.getCurrentMonth();
        currentYear = Utilities.getCurrentYear();
        dates = Utilities.genCurrentMonth();
        initTable(dates, currentMonth + "-" + currentYear);
        ActionListener task = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String curr = new SimpleDateFormat("hh:mm a").format(new java.util.Date());
                time.setText(curr);
                Date t = getToday();
                if (t != null) {
                    if (t.check(curr) && t.getEventAtTime(curr).getTimeInstance().remind() && t.getEventAtTime(curr).allow()) {
                        JOptionPane.showMessageDialog(null, "Reminder: " + t.getDescriptionFromTime(curr), "Reminder", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                if (refresh_board) {
                    refresh();
                    refresh_board = false;
                }
            }
        };
        new Timer(100, task).start();
        addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                hideWindow();
            }
        });
    }

    public void hideWindow() {
//        this.setVisible(false);
    }

    public static void setX(int px) {
        x = px;
    }

    public static void setY(int py) {
        y = py;
    }

    public static int x() {
        return x;
    }

    public static int y() {
        return y;
    }

    public static Point getPoint() {
        return new Point(x(), y());
    }

    public static Date getDateAtPoint(Point p) {
        return dates[p.x][p.y];
    }

    public void check() {
        currentMonth = week[0].getMonth();
        currentYear = week[0].getYear();
        week_c = week[0].getDay();
    }

    public void init() {
        Loading_main j = new Loading_main();
        jScrollPane2.setViewportView(j);
        Init i = new Init();
        i.start();
    }

    public void refresh() {
        if (state == 0) {
            try {
                dates = Utilities.genCurrentMonth();
            } catch (Exception e) {
            }
            initTable(dates, currentMonth + "-" + currentYear);
        } else if (state == 1) {
            int b = 0;
            for (int i = 0; i < week.length; i++) {
                if (week[i].getLables().size() > b) {
                    b = week[i].getLables().size();
                }
            }
            h_w = 135 + (b * 20);
            if (h_w < 500) {
                h_w = 500;
            }
            setSize(772, h_w);
            initWeek(week, week[0].getDate() + "-" + week[6].getDate());
        }
    }

    public static Date getToday() {
        for (int i = 0; i < special.size(); i++) {
            if (special.get(i).getDate().compareTo(Utilities.getDate()) == 0) {
                return special.get(i);
            }
        }
        return null;
    }

    public static void initFull() {
    }

    public static void initWeek(Date[] dates, String date) {
        curr.setText(date);
        Weekly w = new Weekly(dates);
        jScrollPane2.setViewportView(w);
    }

    public static void initTable(Date[][] dates, String month) {
        curr.setText(month);
        final JTable jTable1 = new JTable();
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 50));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                dates,
                new String[]{
                    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.setRowHeight(100);
        for (int i = 0; i < dates[0].length; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }
        jScrollPane2.setViewportView(jTable1);
        jj = new JPopupMenu();
        JMenuItem b1 = new JMenuItem("Add Event");
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date temp = getDateAtPoint(new Point(jTable1.rowAtPoint(getPoint()), jTable1.columnAtPoint(getPoint())));
                if (temp.toString().compareTo("") != 0) {
                    temp.addEvent();
                }
            }
        });
        jj.add(b1);
        JMenuItem b2 = new JMenuItem("Get all Events for Day");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date temp = getDateAtPoint(new Point(jTable1.rowAtPoint(getPoint()), jTable1.columnAtPoint(getPoint())));
                if (temp.toString().compareTo("") != 0) {
                    temp.description();
                    temp.refresh();
                }
            }
        });
        jj.add(b2);
        JMenuItem b3 = new JMenuItem("Delete all Events for Day");
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date temp = getDateAtPoint(new Point(jTable1.rowAtPoint(getPoint()), jTable1.columnAtPoint(getPoint())));
                if (temp.toString().compareTo("") != 0) {
                    temp.clearEvents();
                    temp.refresh();
                }
            }
        });
        jj.add(b3);
        jTable1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    setX(e.getX());
                    setY(e.getY());
                    jj.show(jTable1, x(), y());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        jTable1.setRowSelectionAllowed(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        curr = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(602, 620));
        setResizable(false);

        jButton1.setText("◄");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back(evt);
            }
        });

        jButton2.setText("►");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forward(evt);
            }
        });

        curr.setText("Month");

        time.setText("Time");

        jMenu1.setText("File");

        jMenuItem4.setText("New Config");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_config(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Load Config");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Load_Config(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Print");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("View");

        jMenuItem7.setText("Condensed");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                condense(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Weekly");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weekly(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Full");
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(curr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(curr)
                    .addComponent(time))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back
        if (state == 0) {
            currentMonth = Utilities.back(currentMonth);
            currentYear = Utilities.backY(currentYear, currentMonth);
            dates = Utilities.genCurrentMonth();
            initTable(dates, currentMonth + "-" + currentYear);
        } else if (state == 1) {
            check();
            week_c--;
            week = Utilities.genCurrentWeek(week_c);
            int b = 0;
            for (int i = 0; i < week.length; i++) {
                if (week[i].getLables().size() > b) {
                    b = week[i].getLables().size();
                }
            }
            h_w = 135 + (b * 20);
            if (h_w < 500) {
                h_w = 500;
            }
            setSize(772, h_w);
            initWeek(week, week[0].getDate() + "-" + week[6].getDate());
        }
    }//GEN-LAST:event_back

    private void forward(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forward
        if (state == 0) {
            currentMonth = Utilities.forward(currentMonth);
            currentYear = Utilities.forwardY(currentYear, currentMonth);
            dates = Utilities.genCurrentMonth();
            initTable(dates, currentMonth + "-" + currentYear);
        } else if (state == 1) {
            check();
            week_c++;
            week = Utilities.genCurrentWeek(week_c);
            int b = 0;
            for (int i = 0; i < week.length; i++) {
                if (week[i].getLables().size() > b) {
                    b = week[i].getLables().size();
                }
            }
            h_w = 135 + (b * 20);
            if (h_w < 500) {
                h_w = 500;
            }
            setSize(772, h_w);
            initWeek(week, week[0].getDate() + "-" + week[6].getDate());
        }
    }//GEN-LAST:event_forward

    private void Load_Config(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Load_Config
        JFileChooser f = new JFileChooser();
        f.showDialog(this, "Select Calendar Config file");
        try {
            File ff = f.getSelectedFile();
            Calendar.r.read(ff.getAbsolutePath());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Load_Config

    private void new_config(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_config
        Config c = new Config();
        c.setVisible(true);
    }//GEN-LAST:event_new_config

    private void condense(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_condense
        state = 0;
        setSize(602, 620);
        dates = Utilities.genCurrentMonth();
        initTable(dates, currentMonth + "-" + currentYear);
    }//GEN-LAST:event_condense

    private void print(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print
        if (state == 0) {
            JOptionPane.showMessageDialog(null, "The condensed calendar cannot be printed. To change go to View", "Cannot Print", JOptionPane.WARNING_MESSAGE);
        } else if (state == 1) {
//            if (h_w <= 585) {
                Print p = new Print(week) {

                    @Override
                    void buildImage(Graphics2D g) {
                        int w = 585, h = 772;
                        g.setFont(new java.awt.Font("Rod", 0, 90));
                        g.drawLine(0, (int) (((double) h) * (1.0 / 7.0)), Window.h_w, (int) (((double) h) * (1.0 / 7.0)));
                        g.drawLine(0, (int) (((double) h) * (2.0 / 7.0)), Window.h_w, (int) (((double) h) * (2.0 / 7.0)));
                        g.drawLine(0, (int) (((double) h) * (3.0 / 7.0)), Window.h_w, (int) (((double) h) * (3.0 / 7.0)));
                        g.drawLine(0, (int) (((double) h) * (4.0 / 7.0)), Window.h_w, (int) (((double) h) * (4.0 / 7.0)));
                        g.drawLine(0, (int) (((double) h) * (5.0 / 7.0)), Window.h_w, (int) (((double) h) * (5.0 / 7.0)));
                        g.drawLine(0, (int) (((double) h) * (6.0 / 7.0)), Window.h_w, (int) (((double) h) * (6.0 / 7.0)));
                        g.drawLine(50, 0, 50, h);
                        g.drawLine(Window.h_w, 0, Window.h_w, h);
                        AffineTransform at = new AffineTransform();
                        at.setToRotation(-Math.PI / 2.0, w / 2.0, h / 2.0); //rotation of text, sexy stuff
                        g.setTransform(at);
                        for (int i = 0; i < 7; i++) {
                            int x = (int) (-((((double) h) * ((1.0 - i) / 7.0))) * 10) - (4630 + (i * 180)); //this math took a rediculously long time to figure out, you're welcome, thanks caroline <3
                            ArrayList<String> des = d[i].getLables();
                            g.drawString(" " + d[i].getDate(), x, 300);
                            for (int i2 = 0; i2 < des.size(); i2++) {
                                g.drawString(des.get(i2), x, (i2 * 200) + 600);
                            }
                        }
                    }
                };
                p.printIt();
//            } else {
//                Print p = new Print(week) {
//
//                    @Override
//                    void buildImage(Graphics2D g) {
//                        int w = 585, h = 772;
//                        g.setFont(new java.awt.Font("Rod", 0, 90));
//                        g.drawLine(0, (int) (((double) h) * (1.0 / 7.0)), Window.h_w, (int) (((double) h) * (1.0 / 7.0)));
//                        g.drawLine(0, (int) (((double) h) * (2.0 / 7.0)), Window.h_w, (int) (((double) h) * (2.0 / 7.0)));
//                        g.drawLine(0, (int) (((double) h) * (3.0 / 7.0)), Window.h_w, (int) (((double) h) * (3.0 / 7.0)));
//                        g.drawLine(0, (int) (((double) h) * (4.0 / 7.0)), Window.h_w, (int) (((double) h) * (4.0 / 7.0)));
//                        g.drawLine(0, (int) (((double) h) * (5.0 / 7.0)), Window.h_w, (int) (((double) h) * (5.0 / 7.0)));
//                        g.drawLine(0, (int) (((double) h) * (6.0 / 7.0)), Window.h_w, (int) (((double) h) * (6.0 / 7.0)));
//                        g.drawLine(50, 0, 50, h);
//                        AffineTransform at = new AffineTransform();
//                        at.setToRotation(-Math.PI / 2.0, w / 2.0, h / 2.0); //rotation of text, sexy stuff
//                        g.setTransform(at);
//                        for (int i = 0; i < 7; i++) {
//                            int x = (int) (-((((double) h) * ((1.0 - i) / 7.0))) * 10) - (4630 + (i * 180)); //this math took a rediculously long time to figure out, your welcome
//                            ArrayList<String> des = d[i].getLables(1);
//                            g.drawString(" " + d[i].getDate(), x, 300);
//                            for (int i2 = 0; i2 < des.size(); i2++) {
//                                g.drawString(des.get(i2), x, (i2 * 200) + 600);
//                            }
//                        }
//                    }
//                };
//                p.printIt();
//                for (int i = 0; i < h_w / 585; i++) {
//                    final int ww;
//                    final boolean last;
//                    if ((i + 1) * 585 >= h_w) {
//                        ww = ((i * 585) - ((i - 1) * 585));
//                        last = true;
//                    } else {
//                        ww = 585;
//                        last = false;
//                    }
//                    Print p2 = new Print(week) {
//
//                        @Override
//                        void buildImage(Graphics2D g) {
//                            int w = 585, h = 772;
//                            g.setFont(new java.awt.Font("Rod", 0, 90));
//                            g.drawLine(0, (int) (((double) h) * (1.0 / 7.0)), ww, (int) (((double) h) * (1.0 / 7.0)));
//                            g.drawLine(0, (int) (((double) h) * (2.0 / 7.0)), ww, (int) (((double) h) * (2.0 / 7.0)));
//                            g.drawLine(0, (int) (((double) h) * (3.0 / 7.0)), ww, (int) (((double) h) * (3.0 / 7.0)));
//                            g.drawLine(0, (int) (((double) h) * (4.0 / 7.0)), ww, (int) (((double) h) * (4.0 / 7.0)));
//                            g.drawLine(0, (int) (((double) h) * (5.0 / 7.0)), ww, (int) (((double) h) * (5.0 / 7.0)));
//                            g.drawLine(0, (int) (((double) h) * (6.0 / 7.0)), ww, (int) (((double) h) * (6.0 / 7.0)));
//                            if (last) {
//                                g.drawLine(ww, 0, ww, h);
//                            }
//                            AffineTransform at = new AffineTransform();
//                            at.setToRotation(-Math.PI / 2.0, w / 2.0, h / 2.0); //rotation of text, sexy stuff
//                            g.setTransform(at);
//                            for (int i = 0; i < 7; i++) {
//                                int x = (int) (-((((double) h) * ((1.0 - i) / 7.0))) * 10) - (4630 + (i * 180)); //this math took a rediculously long time to figure out, your welcome
//                                ArrayList<String> des = d[i].getLables(i + 2);
//                                for (int i2 = 0; i2 < des.size(); i2++) {
//                                    g.drawString(des.get(i2), x, (i2 * 200));
//                                }
//                            }
//
//                        }
//                    };
//                    p2.printIt();
//                }
//            }
        }
    }//GEN-LAST:event_print

    private void weekly(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weekly
        state = 1;
        week_c = 1;
        for (int i = 0; i < dates[0].length; i++) {
            if (dates[0][i].toString().compareTo(week_c + "") == 0) {
                week = Utilities.genCurrentWeek(-(i + 1) + 2);
            }
        }
        int b = 0;
        for (int i = 0; i < week.length; i++) {
            if (week[i].getLables().size() > b) {
                b = week[i].getLables().size();
            }
        }
        h_w = 135 + (b * 20);
        if (h_w < 500) {
            h_w = 500;
        }
        setSize(772, h_w);
        initWeek(week, week[0].getDate() + "-" + week[6].getDate());
    }//GEN-LAST:event_weekly
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel curr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}