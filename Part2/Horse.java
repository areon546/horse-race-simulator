import java.util.LinkedList;

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
    private int distanceTravelled = 0, totalDistanceTravelled = 0;
    private boolean fallen = false;
    private double confidence;
    private Breed breed;
    private LinkedList<Equipment> inventory, equiped;

    // private Rider r; // TODO should horses have riders?

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
        this.totalDistanceTravelled += this.distanceTravelled;
        this.distanceTravelled = 0;
    }

    public void moveForward() {
        this.distanceTravelled++;
    }

    public boolean canMove() {
        return Math.random() < (this.confidence);
    }

    public boolean canFall() {
        return Math.random() < (0.1 * this.confidence * this.confidence);
    }

    public void customiseHorse() {
        // this method lets users customise horse breed and equipment
        double availableCapital = 700.0;

        // display a list of equipment
        Equipment.printEquipment(this);

        // bought equipment goes to inventory
        // equiped equipment goes to equiped

        // show all equipment available
    }

    public boolean equipmentFits(Equipment e) { // checks if the equipment fits a horse based on it's size
        int horseSize = this.breed.getBreedSize();

        switch (e.getEquipmentSize()) {
            case "Small":
                return (horseSize == 13 || horseSize == 14);
            case "Medium":
                return (horseSize == 15 || horseSize == 16);
            case "Large":
                return (horseSize == 17 || horseSize == 18);
            default:
                return false;
        }
    }

}
