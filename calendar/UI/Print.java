package calendar.UI;

import calendar.Date;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

/**
 *
 * @author j0ker
 */
public abstract class Print implements Printable {

    Date[] d;

    public Print(Date[] in) {
        d = in;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g = (Graphics2D) graphics;
        g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        buildImage(g);
        return PAGE_EXISTS;
    }

    abstract void buildImage(Graphics2D g);

    public void printIt() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        job.setJobName("Calendar");
        if (job.printDialog() == true) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Printing Failed! " + ex, "Failed to print Calendar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
