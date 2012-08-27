package calendar;

import java.awt.Component;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author j0ker
 */
public class CellRenderer extends DefaultTableCellRenderer {
    
    String s;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (value != null) {
            if (((Date) value).hasEvent()) {
                s = "/calendar/UI/event.png";
            } else {
                s = "/calendar/UI/blank.png";
            }
            setIcon(getIcon(table, column));
            setText(value + "");
        }
        return this;
    }

    protected Icon getIcon(JTable table, int column) {
        URL url = getClass().getResource(s);
        Image i = getToolkit().getImage(url);
        return new ImageIcon(i);
    }
}
