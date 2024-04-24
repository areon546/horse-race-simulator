import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame {

    // swing attributes
    public static JFrame alphabetFrame, loggedInFrame, failedLoginFrame,
            mainMFrame, settingsFrame, cipherCFrame,
            defaultAccFrame, addingDefAFrame, editingDefAFrame,
            textOutFrame = new JFrame();

    static String cipher = "";

    private static JTextField TFusrnme;
    private static JPasswordField TFpsswrd;

    private static Color blue = new Color(0, 191, 255), white = new Color(255, 255, 255),
            offBlue = new Color(128, 128, 255);
    private static Font mFont = new Font("Segoe print", Font.BOLD, 18);
    private static JLabel txtOutLabel = new JLabel();

    // standard attributes
    private static int pWidth = 580, pHeight = 400;

    // methods start here
    public static void start() {

        alphabetFrame = new JFrame();

        /* creates the main panel */
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.setBackground(new Color(128, 128, 255));

        /* login Panel */
        JLabel JLusername, JLpassword;
        JLusername = new JLabel("Username: ");
        JLusername.setFont(mFont);
        TFusrnme = new JTextField();
        TFusrnme.setFont(mFont);
        JLpassword = new JLabel("Password: ");
        JLpassword.setFont(mFont);
        TFpsswrd = new JPasswordField();
        TFpsswrd.setFont(mFont);

        /* username and password panel */
        JPanel authenticationPanel = new JPanel(); // this adds the username and password text and input fields
        authenticationPanel.setLayout(new GridLayout(4, 1, 5, 5));
        authenticationPanel.add(JLusername);
        authenticationPanel.add(TFusrnme);
        authenticationPanel.add(JLpassword);
        authenticationPanel.add(TFpsswrd);

        /* Buttons */
        // this determines what happens when the 'Submit' button is pressed
        JButton submitB = new JButton("Submit");
        submitB.setFont(mFont);
        submitB.setBackground(white);
        submitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // this part allows me to put buttons on the GUI
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(submitB);

        // adds stuff to the panel
        mPanel.add(authenticationPanel, BorderLayout.NORTH);
        mPanel.add(buttonPanel, BorderLayout.SOUTH);
        alphabetFrame.add(mPanel);

        // these lines are used to display the frame and customise it
        alphabetFrame.setTitle("FenceEncode: Login Screen");
        alphabetFrame.setSize(500, 400);
        alphabetFrame.setMinimumSize(new Dimension(pWidth, pHeight));
        alphabetFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        alphabetFrame.setVisible(true);

    }

    public static void mainMenu(JFrame prevF) {
        // remove previous frame
        hidePreviousFrame(prevF);

        // assigns the frame
        mainMFrame = new JFrame();

        // creates the panel to put stuff on
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.setBackground(new Color(128, 128, 255));

        // creates the buttons on the main menu
        JButton settingsB = new JButton("Settings");
        settingsB.setFont(mFont);
        settingsB.setBackground(white);
        settingsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings(mainMFrame);
            }
        });

        JButton encodeB = new JButton(("Encode: " + cipher));
        encodeB.setFont(mFont);
        encodeB.setBackground(white);
        encodeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton decodeB = new JButton("Decode: " + cipher);
        decodeB.setFont(mFont);
        decodeB.setBackground(white);
        decodeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // adds buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 5, 50));
        buttonPanel.add(settingsB);
        buttonPanel.add(encodeB);
        buttonPanel.add(decodeB);

        mPanel.add(buttonPanel, BorderLayout.WEST);

        mainMFrame.add(mPanel);

        mainMFrame.setTitle("FenceEncode: Home");
        mainMFrame.setSize(580, 400);
        mainMFrame.setMinimumSize(new Dimension(pWidth, pHeight));
        mainMFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMFrame.setVisible(true);

    }

    public static void settings(JFrame prevF) {
        // remove previous frame
        hidePreviousFrame(prevF);

        // assigns the frame
        settingsFrame = creatFrame("Settings", null);

        // creates the panel to put stuff on
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.setBackground(new Color(128, 128, 255));

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

        settingsFrame.add(mPanel);

        settingsFrame.setTitle("FenceEncode: Settings");
        settingsFrame.setSize(500, 400);
        settingsFrame.setMinimumSize(new Dimension(pWidth, pHeight));
        settingsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settingsFrame.setVisible(true);

    }

    public static void outputTxt(JFrame prevF, String[][] textData, String name) {
        // buttons: home back

        // hide previous frame
        hidePreviousFrame(prevF);

        // build current frame
        JFrame textOutFrame = new JFrame();

        // buttons
        JButton homeB = new JButton("Home");
        homeB.setFont(mFont);
        homeB.setBackground(white);
        homeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        // adds buttons

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 50));
        buttonPanel.add(homeB);

        // output text data
        JPanel textOutPanel = new JPanel();
        textOutPanel.setLayout(new BorderLayout());
        textOutPanel.setBackground(offBlue);

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

    private static JFrame creatFrame(String title, JPanel[] panels) {
        JFrame frame = new JFrame();

        // Add the panels

        frame.setTitle(title);
        frame.setSize(pWidth, pHeight);
        frame.setMinimumSize(new Dimension(pWidth, pHeight));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

}