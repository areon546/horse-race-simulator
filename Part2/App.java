
// to display the message “GFG WEB Site Click”
import java.io.*;
import javax.swing.*;

public class App {

    // Main driver method
    public static void main(String[] args) {
        HorseRace r = new HorseRace(400, 400);

        // adding button in JFrame
        r.addButtons(r.createButton());

        r.setVisible(true);
    }
}
