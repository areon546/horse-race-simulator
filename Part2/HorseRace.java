
import javax.swing.*;

public class HorseRace extends JFrame {
    private JFrame frame;
    private JPanel outerJPanel;
    private int screenWidth, screenHeight;

    public HorseRace(int scWidth, int scHeight) {
        screenWidth = scWidth;
        screenHeight = scHeight;

        // Creating instance of JFrame
        this.frame = new JFrame();
        this.outerJPanel = new JPanel();

        // 400 width and 500 height
        frame.setSize(screenWidth, screenHeight);

        // using no layout managers
        frame.setLayout(null);

    }

    public <T extends JButton> void addButtons(T[] buttons) {
        for (T button : buttons) {
            this.frame.add(button);
        }
    }

    public <T extends JButton> void addButtons(T button) {
        this.frame.add(button);
    }

    public void setVisible(boolean visible) {
        // making the frame visible
        this.frame.setVisible(visible);
    }

    public JButton createButton() {
        // Creating instance of JButton
        JButton button = new JButton(" GFG WebSite Click");
        final int buttonWidth = 150, buttonHeight = 50;
        final int buttonX = 0, // ((screenWidth - 150 - buttonCentre) * 1 / 3)
                buttonY = 0;

        // x axis, y axis, width, height
        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        return button;
    }
}
