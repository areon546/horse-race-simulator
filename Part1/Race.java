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
    private Horse[] horses; // TODO [x] finish implementation of horses array - had issues determining when
                            // the
                            // race is over
    private Horse winner;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int numHorses) {
        // initialise instance variables
        this.raceLength = distance;
        this.horses = new Horse[numHorses];
        this.numberHorses = numHorses;
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse   the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber) { // TODO [x] add functionality for more than 3 lanes
        if (laneNumber > this.numberHorses || laneNumber == 0) {
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

        // reset all the lanes (all horses not fallen and back to 0).
        for (Horse h : this.horses) {
            if (h != null) {
                h.goBackToStart();
            }
        }

        // print the initial state
        printRace();

        while (!finished) {
            boolean allFallen = true, won = false;

            // move each horse
            for (Horse h : this.horses) {
                if (h != null) {
                    moveHorse(h);
                }
            }
            // if any of the three horses has won, or all are down, the race is finished
            for (Horse h : this.horses) {
                if (h != null) { // TODO [x] problem: if a lane is empty, the program will crash
                    won = won || raceWonBy(h);
                    allFallen = allFallen && h.hasFallen(); // TODO [x] currently a logic error, if a single horse
                                                            // falls,
                                                            // the game will end, we want to check if all horses fall
                }
            }

            // TODO [x] optimised from an if (x==true) y=true to y=x
            finished = won || allFallen;

            // print the race positions
            printRace(); // TODO [x] make it print what happens to the horses after one wins

            // wait for 100 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
            }
        }

        // TODO [x] output who wins the race
        if (this.winner != null) {
            System.out.printf("And the winner is %s %n", this.winner.getName()); // TODO [x] error if there is no winner
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

        if (!theHorse.hasFallen()) {
            // the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            // the probability that the horse will fall is very small (max is 0.1)
            // but will also will depends exponentially on confidence
            // so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1 * theHorse.getConfidence() * theHorse.getConfidence())) {
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
    private boolean raceWonBy(Horse theHorse) { // TODO [x] make winning increase confidence
        if (theHorse.getDistanceTravelled() == raceLength) {
            theHorse.setConfidence(theHorse.getConfidence() + 0.1);
            this.winner = theHorse;
            return true;
        }

        return false;
    }

    /***
     * Print the race on the terminal
     */
    private void printRace() {

        // TODO make clear terminal window,
        // System.out.print('\u000C'); // clear the terminal window

        System.out.print("\033\143");
        // TODO this works due to terminal escape sequences,
        // it has a weird artifact that i dont the origin of
        // i dont like this fix, mainly because i dont really understand it

        // System.out.print("\033[A");

        multiplePrint('=', raceLength + 3); // top edge of track
        System.out.println();

        for (Horse h : this.horses) { // TODO [x] replaced lane1Horse with horses array
            // TODO [x] fix error if horse is null
            if (h == null) {
                printEmptyLane(); // TODO [x] make it print an empty row if the horse isnt present in that lane
            } else {
                printLane(h);
            }
            System.out.println();
        }

        multiplePrint('=', raceLength + 3); // bottom edge of track
        System.out.println();
    }

    /**
     * print a horse's lane during the race
     * for example
     * | X |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse) {
        // calculate how many spaces are needed before
        // and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        // print a | for the beginning of the lane
        System.out.print('|');

        // print the spaces before the horse
        multiplePrint(' ', spacesBefore);

        // if the horse has fallen then print dead
        // else print the horse's symbol
        if (theHorse.hasFallen()) {
            // System.out.print('\u2322'); // TODO make print X
            System.out.print('X'); // TODO make print X
        } else {
            System.out.print(theHorse.getSymbol());
        }

        // print the spaces after the horse
        multiplePrint(' ', spacesAfter);

        // print the | for the end of the track
        System.out.print('|');

        // TODO [x] print the name and confidence of the horse
        System.out.printf(" %s (Confidence: %.1f)", theHorse.getName(), theHorse.getConfidence());
    }

    private void printEmptyLane() {
        int spaces = this.raceLength + 1;

        // print a | for the beginning of the lane
        System.out.print('|');

        // print the empty lane
        multiplePrint(' ', spaces);

        // print the | for the end of the track
        System.out.print('|');

    }

    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times) {
        int i = 0;
        while (i < times) {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
