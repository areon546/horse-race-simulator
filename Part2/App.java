
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class App {

    // Main driver method
    public static void main(String[] args) {
        GUI.home();
        // SimpleTimer.main(null);
        // GUI.outputTxt(new String[][] { { "test", "output" } }, "test");
    }
}

class SimpleTimer extends JFrame {
    private JLabel label;
    private Timer timer;
    private int counter = 3; // the duration
    private int delay = 1000; // every 1 second
    private static final long serialVersionUID = 1L;
    private Color c = Color.RED;
    private boolean red = true;
    private boolean stop = false;
    int i = counter;

    public SimpleTimer() {
        super("Simple Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Wait for " + counter + " sec", JLabel.CENTER);
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.add(label, BorderLayout.CENTER);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pack();

        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();
        setVisible(true);
    }

    ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (i == 0) {
                timer.stop();
                stop = true;
                i = counter;
                timer = new Timer(delay, action);
                timer.setInitialDelay(0);
                timer.start();
            } else {
                c = red ? Color.GREEN : Color.RED;
                red = !red;
                label.setBackground(c);
                label.setOpaque(true);
                label.setText("Wait for " + i + " sec");
                i--;
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleTimer();
            }
        });
    }
}