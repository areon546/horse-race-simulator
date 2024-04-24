import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame {

    // swing attributes
    private static JFrame alphabetFrame, mainMFrame, settingsFrame;

    private static Color blue = new Color(0, 191, 255), white = new Color(255, 255, 255),
            offBlue = new Color(128, 128, 255);
    private static Font mFont = new Font("Segoe print", Font.BOLD, 18);
    private static JLabel txtOutLabel = new JLabel();

    // standard attributes
    private static int pWidth = 580, pHeight = 400;

    // methods start here
    public static void mainMenu(JFrame prevF) {
        // remove previous frame
        hidePreviousFrame(prevF);

        // creates the panel to put stuff on
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.setBackground(offBlue);

        // creates the buttons on the main menu
        JButton settingsB = createButton("Settings", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings(mainMFrame);
            }
        });

        // adds buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 50));
        buttonPanel.add(settingsB);

        mPanel.add(buttonPanel, BorderLayout.WEST);

        JPanel[] panels = { mPanel, buttonPanel };

        mainMFrame = createFrame("Home", panels, WindowConstants.HIDE_ON_CLOSE);
        mainMFrame.setVisible(true);
    }

    public static void settings(JFrame prevF) {
        // remove previous frame
        hidePreviousFrame(prevF);

        // assigns the frame

        // creates the panel to put stuff on
        JPanel mPanel = createPanel(offBlue, new BorderLayout());
        mPanel.setLayout(new BorderLayout());
        mPanel.setBackground(offBlue);

        // creates the buttons on the main menu
        JButton defAcc = new JButton("Default Accounts");
        defAcc.setFont(mFont);
        defAcc.setBackground(white);
        defAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton ciphers = new JButton("Change Cipher");
        ciphers.setFont(mFont);
        ciphers.setBackground(white);
        ciphers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton backB = new JButton("Back");
        backB.setFont(mFont);
        backB.setBackground(white);
        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleBetweenFrames(settingsFrame, prevF);
            }
        });

        // adds buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 5, 5));
        buttonPanel.add(defAcc);
        buttonPanel.add(ciphers);
        buttonPanel.add(backB);

        JPanel backBPanel = new JPanel();
        backBPanel.setLayout(new GridLayout(1, 2, 5, 5));
        backBPanel.add(backB);

        mPanel.add(buttonPanel, BorderLayout.WEST);
        mPanel.add(backBPanel, BorderLayout.SOUTH);

        settingsFrame = createFrame("Settings", new JPanel[] { mPanel }, WindowConstants.EXIT_ON_CLOSE);
        settingsFrame.setVisible(true);

    }

    public static void outputTxt(JFrame prevF, String[][] textData, String name) {
        // hide previous frame
        hidePreviousFrame(prevF);

        // build current frame
        JFrame textOutFrame = new JFrame();

        // buttons
        JButton homeB = createButton("Home", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        // adds buttons
        JPanel buttonPanel = createPanel(new GridLayout(1, 2, 5, 50));

        buttonPanel.add(homeB);

        // output text data
        JPanel textOutPanel = createPanel(new BorderLayout(), offBlue);

        // txtOutLabel - adds stuff to output string
        String text = "";
        for (int i = 0; i < textData.length; i++) {
            System.out.println(textData[i][0] + textData[i][1]);
            text += "<html>" + textData[i][0] + textData[i][1] + " <br>"; // uses html stuff because of how JLabels work
            // System.out.println(text);
        }

        txtOutLabel.setText(text);
        txtOutLabel.setFont(mFont);

        // builds the frame by adding the stuff on it
        textOutPanel.add(txtOutLabel);
        textOutPanel.add(buttonPanel, BorderLayout.SOUTH);

        textOutFrame.add(textOutPanel);

        // personalises and turns on the logged in frame
        textOutFrame.setTitle(("FenceEncode: " + name));
        textOutFrame.setSize(500, 400);
        textOutFrame.setMinimumSize(new Dimension(pWidth, pHeight));
        textOutFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textOutFrame.setVisible(true);
    }

    private static void hidePreviousFrame(JFrame prevFrame) {
        if (prevFrame != null) {
            prevFrame.setVisible(false);
        }
    }

    private static void toggleBetweenFrames(JFrame frameToHide, JFrame frameToShow) {
        hidePreviousFrame(frameToHide);
        frameToShow.setVisible(true);
    }

    private static JFrame createFrame(String title, JPanel[] panels, int closeOperation) {
        JFrame frame = new JFrame();

        // Add the panels
        for (JPanel panel : panels) {
            frame.add(panel);
        }

        frame.setTitle(title);
        frame.setSize(pWidth, pHeight);
        frame.setMinimumSize(new Dimension(pWidth, pHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private static JButton createButton(String text, Color color, Font font, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(color);
        button.addActionListener(actionListener);
        return button;
    }

    private static JPanel createPanel(LayoutManager layout) {
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        return panel;
    }

    private static JPanel createPanel(LayoutManager layout, Color color) {
        JPanel panel = createPanel(layout);
        panel.setBackground(color);
        return panel;
    }

    private static JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private static JTextField createTextField(Font font) {
        JTextField textField = new JTextField();
        textField.setFont(font);
        return textField;
    }

    private static JPasswordField createPasswordField(Font font) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(font);
        return passwordField;
    }
}