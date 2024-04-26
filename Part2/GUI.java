import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {

    private static Race race;
    private static int numberOfHorses = 0;

    // swing attributes
    private static JFrame homeFrame, prevFrame, currentFrame;
    private static JFrame trackFrame, horseFrame;

    private static Color blue = new Color(0, 191, 255), white = new Color(255, 255, 255),
            offBlue = new Color(128, 128, 255);
    private static Font mFont = new Font("Segoe print", Font.BOLD, 18);
    private static JLabel txtOutLabel = new JLabel();
    private static JTextArea raceTrackLabel;

    // standard attributes
    private static int pWidth = 580, pHeight = 400;

    // TIMER TEST ATTRTIBUTES

    private static Timer timer;
    private static int delay = 1000; // every 1 second
    private static boolean started = false, raceFinished = false;

    private static ActionListener action = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (GUI.race != null) { // if there is a race that you can start
                if (!started) { // if the race hasnt started yet
                    // creates the timer
                    timer = new Timer(delay, action);
                    started = true;
                    timer.setInitialDelay(0);
                    timer.start();
                } else if (raceFinished) { // if the race has finished, stop updating
                    timer.stop();
                } else {
                    // write the race
                    raceTrackLabel.setOpaque(true);
                    if (!race.getFinished()) {
                        raceTrackLabel.setText(race.getNextFrame());
                    }

                    raceFinished = race.getFinished();
                }

            } else {
                raceTrackLabel.setText("You need to create a race first. \n\n\n");
            }
        };
    };

    // END OF TIMER TEST ATTRIBUTES

    public static void setRace(Race r) {
        GUI.race = r;
    }

    // methods start here
    public static void home() {
        // remove previous frame
        currentFrame = homeFrame;

        // creates the panel to put stuff on
        JPanel mPanel = createPanel(new BorderLayout(), offBlue);

        // creates the buttons on the main menu
        JButton horseButton = createButton("Adjust Horses", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToAdjustHorses();
            }
        });

        // creates the buttons on the main menu
        JButton trackButton = createButton("Adjust Track", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToAdjustTrack();
            }
        });

        JPanel raceTrackPanel = createPanel(new BorderLayout());
        raceTrackLabel = createTextArea("", mFont);
        raceTrackPanel.add(raceTrackLabel, BorderLayout.NORTH);

        if (GUI.race == null) {
            raceTrackLabel.setText("Please create a Race before running\n\n\n");
        }

        // set up timer stuff
        timer = new Timer(delay, action);
        timer.setInitialDelay(0);

        JButton startRaceButton = createButton("Start Race", white, mFont, action);

        raceTrackPanel.add(startRaceButton, BorderLayout.SOUTH);

        // adds buttons
        JPanel buttonPanel = createPanel(new GridLayout(1, 2, 5, 50));

        buttonPanel.add(horseButton);
        // buttonPanel.add(trackButton);

        mPanel.add(raceTrackPanel, BorderLayout.NORTH);
        mPanel.add(buttonPanel, BorderLayout.SOUTH);

        homeFrame = createFrame("Home", new JPanel[] { mPanel }, WindowConstants.HIDE_ON_CLOSE);
        homeFrame.setVisible(true);
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

    public static void goToAdjustHorses() {
        // TODO make it let the user adjust the horses

        // remove previous frame
        hidePreviousFrame(prevFrame);
        prevFrame = horseFrame;

        // assigns the frame

        // creates the panel to put stuff on
        JPanel mPanel = createPanel(new BorderLayout(), offBlue);
        JPanel customisationOptions = createPanel(new GridLayout(5, 3, 5, 5));

        JLabel horseNameLabel = createLabel("Horse Name", mFont);
        JTextField horseName = createTextField("", mFont);

        JLabel horseSymbolLabel = createLabel("Horse Symbol", mFont);
        JTextField horseSymbol = createTextField("", mFont);

        JLabel horseConfidenceLabel = createLabel("Horse Confidence: Input a decimal", mFont);
        JTextField horseConfidence = createTextField("", mFont);

        JLabel horseListLabel = createLabel("Select a Horse", mFont);
        JComboBox horseList; // combo box if there are horses
        // creates the buttons on the main menu
        if (race != null && !race.noHorses()) {

            // add horses to the list
            horseList = createComboBox(mFont);
            Horse[] horses = race.getHorses();

            // loop through horses
            for (Horse horse : horses) {
                if (horse != null) {
                    horseList.addItem(horse.getName());
                }
            }

            horseList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (horseList.getSelectedItem() != null) {
                        // set values based on the horse object
                        Horse selectedHorse = GUI.race.getHorses()[GUI.race
                                .findHorse((String) horseList.getSelectedItem())];
                        horseName.setText(selectedHorse.getName());
                        horseSymbol.setText(selectedHorse.getSymbol() + "");
                        horseConfidence.setText(selectedHorse.getConfidence() + "");
                    }
                }

            });
            customisationOptions.add(horseListLabel); // add combo box to the panel
            customisationOptions.add(horseList); // add combo box to the panel
        }

        JButton newHorse = createButton("New Horse", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int emptyIndex = GUI.race.firstEmptyHorse();
                GUI.race.addHorse(new Horse(horseSymbol.getText().charAt(0), horseName.getText(), 0.3), emptyIndex);
            }
        });

        JButton saveHorse = createButton("Save Horse", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int emptyIndex = GUI.race.findHorse(horseName.getText());
                GUI.race.addHorse(new Horse(horseSymbol.getText().charAt(0), horseName.getText(), 0.3), emptyIndex);
            }
        });

        JButton backB = createButton("Back", white, mFont, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleBetweenFrames(horseFrame, prevFrame);
            }
        });

        // adds labels and text fields
        customisationOptions.add(horseNameLabel);
        customisationOptions.add(horseName);
        customisationOptions.add(horseSymbolLabel);
        customisationOptions.add(horseSymbol);
        customisationOptions.add(horseConfidenceLabel);
        customisationOptions.add(horseConfidence);

        // adds buttons
        if (GUI.race != null && GUI.race.firstEmptyHorse() != -1) {
            customisationOptions.add(newHorse);
        } else {
            customisationOptions.add(saveHorse);
        }

        JPanel backBPanel = createPanel(new GridLayout(1, 1, 5, 5));
        backBPanel.add(backB);

        mPanel.add(customisationOptions, BorderLayout.NORTH);
        mPanel.add(backBPanel, BorderLayout.SOUTH);

        horseFrame = createFrame("Settings", new JPanel[] { mPanel }, WindowConstants.EXIT_ON_CLOSE);
        horseFrame.setVisible(true);

    }

    public static void goToAdjustTrack() {

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

    private static void goBack() {
        toggleBetweenFrames(currentFrame, prevFrame);
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

    private static JTextArea createTextArea(String text, Font font) {
        JTextArea label = new JTextArea(text);
        label.setFont(font);
        return label;
    }

    private static JTextField createTextField(String text, Font font) {
        JTextField label = new JTextField(text);
        label.setFont(font);
        return label;
    }

    private static JComboBox createComboBox(Font font) {
        JComboBox box = new JComboBox();
        box.setFont(font);
        return box;
    }

}