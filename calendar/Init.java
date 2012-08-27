package calendar;

import calendar.UI.Window;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author j0ker
 */
public class Init extends Thread {

    @Override
    public void run() {
        File f = new File("settings.cal");
        if (!f.exists()) {
            try {
                f.createNewFile();
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }
                JOptionPane.showMessageDialog(null, "Welcome New user! A new Calendar will be generated\nDo not delete the \"settings.cal\" file!", 
                        "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                Calendar.r.write(f.getName(), Window.special);
            } catch (IOException ex) {
            }
        }
        Calendar.r.read(f.getName());
        Window.goOn = true;
        stop();
    }
}