import java.util.concurrent.TimeUnit;
import java.lang.Math;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell, Artur Baran
 * @version 2.0 2023.03.11
 */
public class Race {
    private int raceLength, numberHorses;
    private Horse[] horses; // DONE: finish implementation of horses array - had issues determining when
                            // the
                            // race is over
    private String[] raceTrack;

    private Horse winner;
    private double earnings = 100;

    private static char boundaryChar = '=', emptyLane = ' ', start = '|', stop = '|';

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param raceTrackLength the length of the racetrack in metres
     * @param numLanes        the number of lanes that horses can run on, a upper
     *                        value to the number of horses that can be added
     * @param unit            the unit of the racetrack length, measured in meters,
     *                        however it accepts the value "yards", upon which the
     *                        race length will be the equivalent in meters
     */
    public Race(int raceTrackLength, int numLanes, String unit) {
        // initialise instance variables
        this.horses = new Horse[numLanes];
        this.numberHorses = numLanes;
        this.raceTrack = initialiseRaceTrack();

        // calculates race length in meters
        if (unit.equals("yards")) {
            this.raceLength = (int) Math.ceil(raceTrackLength * 0.9144);
        } else if (unit.equals("meters")) {
            this.raceLength = raceTrackLength;
        } else {
            this.raceLength = 0;
        }
    }

    // a default constructor for the Race class
    /**
     * The default constructor for the race class if the number of lanes in a race
     * is not specified. It also assumes the units to be meters.
     * 
     * @param raceTrackLength the length of the racetrack in metres
     */
    public Race(int raceTrackLength) {
        this(raceTrackLength, 3, "meters");
    }

    /**
     * A constructor for the race class if the unit is not specified. It assumes the
     * unit is in meters.
     * 
     * @param raceTrackLength the length of the racetrack in metres
     * @param numLanes        the number of lanes that horses can run on, a upper
     *                        value to the number of horses that can be added
     */
    public Race(int raceTrackLength, int numLanes) {
        this(raceTrackLength, numLanes, "meters");
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse   the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber) { // DONE: add functionality for more than 3 lanes
        if (laneNumber > this.numberHorses || laneNumber <= 0) {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }

        this.horses[laneNumber - 1] = theHorse;
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */
    public void startRace() {
        // declare a local variable to tell us when the race is finished
        boolean finished = false;

        if (this.raceLength < 1) { // DONE: if race length is less than 1, the race continues until horses fall
            System.out.println("Race too short or incorrect unit entered. ");
            // DONE: common alternative solution is to assign all values with setters,
            // which can then have the validation inbuilt to them, so the programmer can
            // just call the setter instead of writing the validation every time
            return;
        }

        if (this.noHorses()) { // prevents a race from starting if there are no horses
            System.out.println("No horses have been added to this race.");
            return;
        }

        // resets all the lanes (all horses not fallen and back to 0).
        for (Horse h : this.horses) {
            if (h != null) {
                h.goBackToStart();
            }
        }

        while (!finished) {
            boolean allFallen = true, won = false;

            for (Horse h : this.horses) {
                if (h != null) {

                    // move each horse
                    moveHorse(h);

                    // DONE: problem: if a lane is empty, the program will crash
                    // if any of the three horses has won, or all are down, the race is finished
                    won = won || raceWonBy(h);
                    allFallen = allFallen && h.hasFallen(); // DONE: currently a logic error, if a single horse
                                                            // falls,
                                                            // the game will end, we want to check if all horses fall
                }
            }

            // DONE: optimised from an if (x==true) y=true to y=x
            finished = won || allFallen;

            // print the race positions
            printRace(); // DONE: make it print what happens to the horses after one wins

            // wait for 100 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
            }
        }

        // DONE: output who wins the race
        if (this.winner != null) {
            System.out.printf("And the winner is %s %n", this.winner.getName()); // DONE: error if there is no winner
        } else {
            System.out.println("All of the horses fell, the race cannot continue. ");
        }

    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) {
        // if the horse has fallen it cannot move,
        // so only run if it has not fallen

        if (!theHorse.hasFallen()) { // DONE: made it easier to read this code and adjust the probability of
                                     // moving vs fallen
            // the probability that the horse will move forward depends on the confidence;
            if (theHorse.canMove()) {
                theHorse.moveForward();
            }

            // the probability that the horse will fall is very small (max is 0.1)
            // but will also will depends exponentially on confidence
            // so if you double the confidence, the probability that it will fall is *2
            if (theHorse.canFall()) {
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse) { // DONE: make winning increase confidence
        if (theHorse.getDistanceTravelled() == raceLength) {
            theHorse.won(earnings);

            this.winner = theHorse;
            return true;
        }

        return false;
    }

    /***
     * Print the race on the terminal
     */
    private void printRace() {

        clearTerminalWindow();

        for (int i = 0; i < this.numberHorses + 2; i++) {
            printLane(i);
        }

        // multiplePrint(boundaryChar, raceLength + 3); // top edge of track
        // System.out.println();

        // for (Horse h : this.horses) { // DONE: replaced lane1Horse with horses array
        // // DONE: fix error if horse is null
        // if (h == null) {
        // printEmptyLane(); // DONE: make it print an empty row if the horse isnt
        // present in that lane
        // } else {
        // printLane(h);
        // }
        // System.out.println();
        // }

        // multiplePrint(boundaryChar, raceLength + 3); // bottom edge of track
        // System.out.println();
    }

    private void clearTerminalWindow() {
        // clear the terminal window
        System.out.print("\033\143");

        // other attemps
        // System.out.print('\u000C');
        // TODO make clear terminal window,

        // System.out.print("\r");
        // TODO this works due to terminal escape sequences,
        // it has a weird artifact that i dont the origin of
        // i dont like this fix, mainly because i dont really understand it

        // System.out.print("\u000C");

    }

    private void printLane(int laneNumber) {
        if (laneNumber == 0 || laneNumber == this.raceLength + 2) { // if there is a boundary
            System.out.println(raceTrack[laneNumber]);
            return;
        } else if (horses[laneNumber - 1] == null) { // if the horse isnt present in that lane
            System.out.println(raceTrack[laneNumber]);
            return;
        } else { // if there is a horse
            Horse theHorse = horses[laneNumber - 1];

            recreateLane(laneNumber - 1);
            System.out.print(raceTrack[laneNumber]);
            System.out.printf(" %s (Confidence: %.1f)%n", theHorse.getName(),
                    theHorse.getConfidence());
        }

        // printLane(horses[laneNumber - 1]);
    }

    private String concatMultiple(char aChar, int times) {
        String output = "";
        for (int i = 0; i < times; i++) {
            output += aChar;
        }

        return output;
    }

    /**
     * Check if there are any horses in horses array.
     * 
     * @return bool: false if there is a single horse in the race
     */
    private boolean noHorses() {
        for (Horse h : this.horses) {
            if (h != null) {
                return false;
            }
        }

        return true;
    }

    private String[] initialiseRaceTrack() {
        String[] track = new String[this.numberHorses + 2];

        track[0] = createRaceTrackBoundary();
        track[track.length - 1] = createRaceTrackBoundary();

        for (int i = 0; i < this.numberHorses; i++) {
            track[i + 1] = createLane(i);
        }

        return track;
    }

    private String createLane(int horseLane) {
        String output = "" + Race.start;

        if (this.horses[horseLane] == null) { // if there is no horse, create an empty lane
            output += concatMultiple(emptyLane, this.raceLength + 3);
        } else { // if there is a horse, create a lane with the horse
            Horse theHorse = this.horses[horseLane];
            // output += this.horses[horseLane].getSymbol();
            // output += concatMultiple(emptyLane, this.raceLength - 3);

            // calculate how many spaces are needed before
            // and after the horse
            int spacesBefore = theHorse.getDistanceTravelled();
            int spacesAfter = this.raceLength - theHorse.getDistanceTravelled();

            // print a | for the beginning of the lane

            // print the spaces before the horse
            output += concatMultiple(Race.emptyLane, spacesBefore);

            // if the horse has fallen then print dead
            // else print the horse's symbol
            if (theHorse.hasFallen()) {
                output += 'X';
            } else {
                output += theHorse.getSymbol();
            }

            // print the spaces after the horse
            output += concatMultiple(Race.emptyLane, spacesAfter);
        }

        output += Race.stop;
        return output;
    }

    private void recreateLane(int horseLane) {
        this.raceTrack[horseLane + 1] = createLane(horseLane);
    }

    private String createRaceTrackBoundary() {
        return concatMultiple(boundaryChar, this.raceLength + 5);
    }

}
