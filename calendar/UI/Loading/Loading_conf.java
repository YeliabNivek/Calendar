package calendar.UI.Loading;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author j0ker
 */
public class Loading_conf extends JPanel {
    
    Image i;
    public static String curr = "";

    public Loading_conf() {
        URL url = getClass().getResource("spinny.gif");
        i = getToolkit().getImage(url);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(i, 180, 80, this);
        g.drawString("Generating Configuration...", 225, 50);
        g.drawString(curr, 230, 120);
    }
}
