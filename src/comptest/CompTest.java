package comptest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author masuda
 */
public class CompTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CompTest().start();
    }
    
    private void start() {
        
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(200, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroller = new JScrollPane(panel);
        frame.add(scroller, BorderLayout.CENTER);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        for (int cnt = 0; cnt < 10; ++cnt) {
            calcAddTime(panel);
            calcRemoveTime(panel);
        }
        panel.repaint();
        
        long addTime = 0;
        long removeTime = 0;
        int loopCnt = 50;
        for (int cnt = 0; cnt < 50; ++cnt) {
            addTime += calcAddTime(panel);
            removeTime += calcRemoveTime(panel);
        }
        panel.repaint();
        System.out.println(String.format("add   : %d [msec]", addTime / loopCnt));
        System.out.println(String.format("remove: %d [msec]", removeTime / loopCnt));
        
        frame.dispose();
    }
    
    private long calcAddTime(JPanel panel) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i) {
            panel.add(new JLabel(String.valueOf(i)));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
    
    private long calcRemoveTime(JPanel panel) {
        long start = System.currentTimeMillis();
        panel.removeAll();
        long end = System.currentTimeMillis();
        return end - start;
    }
    
}
