
/**
 * Write a description of class Horse here.
 * 
 * @author Artur Baran
 * @version v1 2023.03.10
 */
public class Horse {
    // Fields of class Horse
    private String name;
    private char symbol;
    private int distanceTravelled = 0;
    private boolean fallen = false;
    private double confidence;

    // Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.setConfidence(horseConfidence);
    }

    // Getters / Accessors

    public double getConfidence() {
        return this.confidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean hasFallen() {
        return this.fallen;
    }

    // Setters / Mutators

    public void setConfidence(double newConfidence) {
        if (newConfidence > 0.0 && newConfidence < 1.0) {
            this.confidence = newConfidence;
            return;
        }
        System.out.println("ERROR: Incompatible Confidence");
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }

    // Other methods of class Horse
    public void fall() { // TODO [x] make confidence decrease

        this.setConfidence(this.confidence - 0.1);

        fallen = true;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
    }

    public void moveForward() {
        this.distanceTravelled++;
    }

}
