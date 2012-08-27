package calendar.UI;

import calendar.Date;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author j0ker
 */
public class Weekly extends JPanel {

    private final Date[] date;
    int x_t;

    public Weekly(Date[] dates) {
        date = dates;
        setBackground(Color.WHITE);
        final JPanel jp = this;
        final JPopupMenu j = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("Add Event");
        m1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getDateAtX(x_t).addEvent();
            }
        });
        j.add(m1);
        JMenuItem m2 = new JMenuItem("Get all Events for Day");
        m2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getDateAtX(x_t).description();
                getDateAtX(x_t).refresh();
            }
        });
        j.add(m2);
        JMenuItem m3 = new JMenuItem("Delete all Events for Day");
        m3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getDateAtX(x_t).clearEvents();
                getDateAtX(x_t).refresh();
            }
        });
        j.add(m3);
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    x_t = e.getX();
                    j.show(jp, x_t, e.getY());
                }
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new java.awt.Font("Rod", 0, 12));
        g.drawLine((int) (((double) getWidth()) * (1.0 / 7.0)), 0, (int) (((double) getWidth()) * (1.0 / 7.0)), getHeight());
        g.drawLine((int) (((double) getWidth()) * (2.0 / 7.0)), 0, (int) (((double) getWidth()) * (2.0 / 7.0)), getHeight());
        g.drawLine((int) (((double) getWidth()) * (3.0 / 7.0)), 0, (int) (((double) getWidth()) * (3.0 / 7.0)), getHeight());
        g.drawLine((int) (((double) getWidth()) * (4.0 / 7.0)), 0, (int) (((double) getWidth()) * (4.0 / 7.0)), getHeight());
        g.drawLine((int) (((double) getWidth()) * (5.0 / 7.0)), 0, (int) (((double) getWidth()) * (5.0 / 7.0)), getHeight());
        g.drawLine((int) (((double) getWidth()) * (6.0 / 7.0)), 0, (int) (((double) getWidth()) * (6.0 / 7.0)), getHeight());
        g.drawLine(0, 50, getWidth(), 50);
        for (int i = 0; i < 7; i++) {
            int x = (int) ((((double) getWidth()) * (1.0 / 7.0) * i));
            ArrayList<String> des = date[i].getLables();
            g.drawString(" " + date[i].getDate(), x, 25);
            for (int i2 = 0; i2 < des.size(); i2++) {
                g.drawString(des.get(i2), x, (i2 * 20) + 60);
            }
        }
    }

    private Date getDateAtX(int xValue) {
        return date[(int) (xValue / (getWidth() / 7.0))];
    }
}
