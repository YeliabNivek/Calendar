package calendar.UI.Loading;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.JPanel;

/**
 *
 * @author j0ker
 */
public class Loading_main extends JPanel {

    Image i;

    public Loading_main() {
        try {
        URL url = getClass().getResource("spinny.gif");
        i = getToolkit().getImage(url);
        } catch(Exception e) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(i, 190, 300, this);
        g.drawString("Loading Calendar...", 250, 200);
    }
}
