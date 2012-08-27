package calendar.UI;

import calendar.util.Utilities;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author j0ker
 */
public class MultipleDateGrabber extends javax.swing.JFrame {

    ArrayList<String> list_1 = new ArrayList<>();
    ArrayList<String> list_2 = new ArrayList<>();
    int loc = -1, end = -1, year = -1, end_y = -1;
    ArrayList<String> all = new ArrayList<>();
    ArrayList<String> months_no = new ArrayList<>();
    ArrayList<Integer> days_no = new ArrayList<>();

    public MultipleDateGrabber() {
        initComponents();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        try {
            setIconImage(ImageIO.read(Window.class.getResource("/calendar/UI/icon.png")));
        } catch (IOException ex) {
        }
        setTitle("Setup Dates");
    }

    public void refreshList_1() {
        final String[] str = new String[list_1.size()];
        for (int i = 0; i < list_1.size(); i++) {
            str[i] = list_1.get(i);
        }
        jList1.setModel(new javax.swing.AbstractListModel() {

            String[] strings = str;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public void refreshList_2() {
        final String[] str2 = new String[list_2.size()];
        for (int i = 0; i < list_2.size(); i++) {
            str2[i] = list_2.get(i);
        }
        jList2.setModel(new javax.swing.AbstractListModel() {

            String[] strings = str2;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    public String nextMonth() {
        String r = "";
        if (loc == 13) {
            loc = 1;
            year++;
        }
        if (loc == -1) {
            loc = Integer.parseInt((String) jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
            try {
                year = Integer.parseInt(jTextField1.getText());
                end_y = Integer.parseInt(jTextField2.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Sorry, I couldn't read on of the dates, Try again", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
            end = Integer.parseInt((String) jComboBox3.getItemAt(jComboBox3.getSelectedIndex()));
            if (year == end_y) {
                if (loc < end) {
                    ArrayList<String> temp = Utilities.genBlankMonth(Utilities.getMonth(loc), year + "", Integer.parseInt((String) jComboBox2.getItemAt(jComboBox2.getSelectedIndex())));
                    for (int i = 0; i < temp.size(); i++) {
                        all.add(temp.get(i));
                    }
                } else {
                    return "done";
                }
            } else {
                if (year < end_y) {
                    ArrayList<String> temp = Utilities.genBlankMonth(Utilities.getMonth(loc), year + "", Integer.parseInt((String) jComboBox2.getItemAt(jComboBox2.getSelectedIndex())));
                    for (int i = 0; i < temp.size(); i++) {
                        all.add(temp.get(i));
                    }
                } else {
                    return "done";
                }
            }
            loc++;
        }
        if (year == end_y) {
            if (loc < end) {
                r = Utilities.getMonth(loc);
                loc++;
                return r;
            }
        } else {
            if (year < end_y) {
                r = Utilities.getMonth(loc);
                loc++;
                return r;
            }
        }
        return "done";
    }

    public void fill_monthArr() {
        if (jCheckBox8.isSelected()) {
            months_no.add(jCheckBox8.getText());
        }
        if (jCheckBox9.isSelected()) {
            months_no.add(jCheckBox9.getText());
        }
        if (jCheckBox10.isSelected()) {
            months_no.add(jCheckBox10.getText());
        }
        if (jCheckBox11.isSelected()) {
            months_no.add(jCheckBox11.getText());
        }
        if (jCheckBox12.isSelected()) {
            months_no.add(jCheckBox12.getText());
        }
        if (jCheckBox13.isSelected()) {
            months_no.add(jCheckBox13.getText());
        }
        if (jCheckBox14.isSelected()) {
            months_no.add(jCheckBox14.getText());
        }
        if (jCheckBox15.isSelected()) {
            months_no.add(jCheckBox15.getText());
        }
        if (jCheckBox16.isSelected()) {
            months_no.add(jCheckBox16.getText());
        }
        if (jCheckBox17.isSelected()) {
            months_no.add(jCheckBox17.getText());
        }
        if (jCheckBox18.isSelected()) {
            months_no.add(jCheckBox18.getText());
        }
        if (jCheckBox19.isSelected()) {
            months_no.add(jCheckBox19.getText());
        }
    }

    public void fill_daysArr() {
        if (jCheckBox1.isSelected()) {
            days_no.add(1);
        }
        if (jCheckBox2.isSelected()) {
            days_no.add(2);
        }
        if (jCheckBox3.isSelected()) {
            days_no.add(3);
        }
        if (jCheckBox4.isSelected()) {
            days_no.add(4);
        }
        if (jCheckBox5.isSelected()) {
            days_no.add(5);
        }
        if (jCheckBox6.isSelected()) {
            days_no.add(6);
        }
        if (jCheckBox7.isSelected()) {
            days_no.add(7);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jCheckBox1.setText("Sunday's");

        jCheckBox2.setText("Monday's");

        jCheckBox3.setText("Tuesday's");

        jCheckBox4.setText("Wednesday's");

        jCheckBox5.setText("Thursday's");

        jCheckBox6.setText("Friday's");

        jCheckBox7.setText("Saturday's");

        jLabel1.setText("Except (all of the below will not be included)");

        jCheckBox8.setText("January");

        jCheckBox9.setText("February");

        jCheckBox10.setText("March");

        jCheckBox11.setText("April");

        jCheckBox12.setText("May");

        jCheckBox13.setText("June");

        jCheckBox14.setText("July");

        jCheckBox15.setText("August");

        jCheckBox16.setText("September");

        jCheckBox17.setText("October");

        jCheckBox18.setText("November");

        jCheckBox19.setText("December");

        jLabel2.setText("From");

        jLabel3.setText("To");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel4.setText("/");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel5.setText("Warning, non-existent dates will not show up on the calendar");

        jLabel6.setText("/");

        jTextField1.setText("year");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel7.setText("/");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel8.setText("/");

        jTextField2.setText("year");

        jLabel9.setText("Example: 07/23/1992");

        jLabel11.setText("Specific dates:");

        jScrollPane1.setViewportView(jList1);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel12.setText("/");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel13.setText("/");

        jTextField3.setText("year");

        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_1(evt);
            }
        });

        jLabel14.setText("An array of Dates:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel15.setText("/");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel16.setText("/");

        jTextField4.setText("year");

        jLabel17.setText("to");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel18.setText("/");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel19.setText("/");

        jTextField5.setText("year");

        jButton2.setText("add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_2(evt);
            }
        });

        jScrollPane2.setViewportView(jList2);

        jButton3.setText("Done");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                done(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox6)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox3)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox14)
                                    .addComponent(jCheckBox13)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox9)
                                            .addComponent(jCheckBox8)
                                            .addComponent(jCheckBox10)
                                            .addComponent(jCheckBox11)
                                            .addComponent(jCheckBox12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox17)
                                            .addComponent(jCheckBox15)
                                            .addComponent(jCheckBox16)
                                            .addComponent(jCheckBox18)
                                            .addComponent(jCheckBox19)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addComponent(jLabel11)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox12)
                    .addComponent(jCheckBox19))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_1
        try {
            int check = Integer.parseInt(jTextField3.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry, I couldn't read on of the dates, Try again", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        list_1.add(Utilities.getMonth(Integer.parseInt((String) jComboBox5.getItemAt(jComboBox5.getSelectedIndex()))) + "/" + jComboBox6.getItemAt(jComboBox6.getSelectedIndex()) + "/" + jTextField3.getText());
        refreshList_1();
    }//GEN-LAST:event_add_1

    private void add_2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_2
        try {
            int check = Integer.parseInt(jTextField4.getText());
            int check2 = Integer.parseInt(jTextField5.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry, I couldn't read on of the dates, Try again", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        int c = Utilities.getNumberOfMonthsDifference(Integer.parseInt((String) jComboBox7.getItemAt(jComboBox7.getSelectedIndex())),
                Integer.parseInt(jTextField4.getText()), Integer.parseInt((String) jComboBox9.getItemAt(jComboBox9.getSelectedIndex())),
                Integer.parseInt(jTextField5.getText()));
        if (c == 0) {
            ArrayList<String> temp = Utilities.genBlankMonth(Utilities.getMonth(Integer.parseInt((String) jComboBox7.getItemAt(jComboBox7.getSelectedIndex()))),
                    jTextField4.getText(), Integer.parseInt((String) jComboBox8.getItemAt(jComboBox8.getSelectedIndex())),
                    Integer.parseInt((String) jComboBox10.getItemAt(jComboBox10.getSelectedIndex())));
            for (int i = 0; i < temp.size(); i++) {
                list_2.add(temp.get(i));
            }
        } else if (c == 1) {
            ArrayList<String> temp_1 = Utilities.genBlankMonth(Utilities.getMonth(Integer.parseInt((String) jComboBox7.getItemAt(jComboBox7.getSelectedIndex()))),
                    jTextField4.getText(), Integer.parseInt((String) jComboBox8.getItemAt(jComboBox8.getSelectedIndex())));
            ArrayList<String> temp_2 = Utilities.genBlankMonth(Utilities.getMonth(Integer.parseInt((String) jComboBox9.getItemAt(jComboBox9.getSelectedIndex()))),
                    jTextField4.getText(), 1, Integer.parseInt((String) jComboBox10.getItemAt(jComboBox10.getSelectedIndex())));
            for (int i = 0; i < temp_1.size(); i++) {
                list_2.add(temp_1.get(i));
            }
            for (int i = 0; i < temp_2.size(); i++) {
                list_2.add(temp_2.get(i));
            }
        } else if (c > 2) {
             ArrayList<String> temp_1 = Utilities.genBlankMonth(Utilities.getMonth(Integer.parseInt((String) jComboBox7.getItemAt(jComboBox7.getSelectedIndex()))),
                    jTextField4.getText(), Integer.parseInt((String) jComboBox8.getItemAt(jComboBox8.getSelectedIndex())));
            for (int i = 0; i < temp_1.size(); i++) {
                list_2.add(temp_1.get(i));
            }
            ArrayList<Point> temp = Utilities.getAllMonths(Integer.parseInt((String) jComboBox7.getItemAt(jComboBox7.getSelectedIndex())),
                    Integer.parseInt(jTextField4.getText()), Integer.parseInt((String) jComboBox9.getItemAt(jComboBox9.getSelectedIndex())),
                    Integer.parseInt(jTextField5.getText()));
            for (int i = 0; i < temp.size(); i++) {
                System.out.println(temp.get(i).x + " " + temp.get(i).y);
            }
            for (int i = 1; i < temp.size() - 1; i++) { //skip the first and last ones
                ArrayList<String> temp_l = Utilities.genBlankMonth(Utilities.getMonth(temp.get(i).x), temp.get(i).y + "", 1);
                for (int i2 = 0; i2 < temp_l.size(); i2++) {
                    list_2.add(temp_l.get(i2));
                }
            }
            ArrayList<String> temp_2 = Utilities.genBlankMonth(Utilities.getMonth(Integer.parseInt((String) jComboBox9.getItemAt(jComboBox9.getSelectedIndex()))),
                    jTextField5.getText(), 1, Integer.parseInt((String) jComboBox10.getItemAt(jComboBox10.getSelectedIndex())));
            for (int i = 0; i < temp_2.size(); i++) {
                list_2.add(temp_2.get(i));
            }
        }
        refreshList_2();
    }//GEN-LAST:event_add_2

    private void done(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_done
        fill_monthArr();
        fill_daysArr();
        String s = nextMonth();
        while (s.compareTo("done") != 0) {
            boolean go = true;
            for (int i = 0; i < months_no.size(); i++) {
                if (months_no.get(i).compareTo(s) == 0) {
                    go = false;
                    break;
                }
            }
            if (go) {
                ArrayList<String> temp = Utilities.genBlankMonth(s, year + "", 1);
                for (int i = 0; i < temp.size(); i++) {
                    all.add(temp.get(i));
                }
            }
            s = nextMonth();
        }
        if (year <= end_y) {
            if (loc <= end) {
                ArrayList<String> temp = Utilities.genBlankMonth(Utilities.getMonth(end), end_y + "", 1, Integer.parseInt((String) jComboBox4.getItemAt(jComboBox4.getSelectedIndex())));
                for (int i = 0; i < temp.size(); i++) {
                    all.add(temp.get(i));
                }
            }
        }
        for (int i = all.size() - 1; i >= 0; i--) {
            String temp_2 = all.get(i);
            boolean got_it = false;
            for (int i2 = 0; i2 < list_1.size(); i2++) {
                if (!got_it && list_1.get(i2).compareTo(temp_2) == 0) {
                    all.remove(i);
                    got_it = true;
                    break;
                }
            }
            for (int i2 = 0; i2 < list_2.size(); i2++) {
                if (got_it) {
                    break;
                }
                if (!got_it && list_2.get(i2).compareTo(temp_2) == 0) {
                    all.remove(i);
                    got_it = true;
                    break;
                }
            }
            for (int i2 = 0; i2 < days_no.size(); i2++) {
                if (got_it) {
                    break;
                }
                if (days_no.get(i2) == Utilities.getDayfromDate(temp_2.substring(0, temp_2.indexOf("/")), Integer.parseInt(temp_2.substring(temp_2.indexOf("/") + 1, temp_2.lastIndexOf("/"))), temp_2.substring(temp_2.lastIndexOf("/") + 1))) {
                    all.remove(i);
                    got_it = true;
                    break;
                }
            }
            if (!got_it) {
                Config.dates.add(temp_2);
            }
        }
        Config.refreshList();
        dispose();
    }//GEN-LAST:event_done
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}