
package projekt1;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Projekt1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override 
            public void run(){
                JFrame pn=new MainFrame();
            }
        });
    }
}
