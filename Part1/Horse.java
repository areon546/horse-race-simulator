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
    private LinkedList<Equipment> inventory = new LinkedList<Equipment>(), equiped = new LinkedList<Equipment>();
    private double capital = 700.0;

    // private Rider r; // TODO should horses have riders?

    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.setConfidence(horseConfidence);

        this.breed = new Breed("Black Horse", 14, "Black", "Solid", "Gray");
    }

    public String toString() {
        return String.format("", this.name); // name, confidence, breed, total distance travelled?, equipment list?,
                                             // symbol?
    }

    // Other methods of class Horse
    public void fall() { // DONE: make confidence decrease

        this.setConfidence(this.confidence - 0.1);
        fallen = true;
    }

    // DONE: if multiple races occur, all fall eventually
    public void goBackToStart() {
        this.totalDistanceTravelled += this.distanceTravelled;
        this.distanceTravelled = 0;
        this.fallen = false;
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

    public boolean canBuy(Equipment e) {

        if (e == null) {
            return false;
        }

        if (capital > e.getCost()) {
            return true;
        }

        return false;
    }

    public void buyItem(Equipment e) {
        this.capital -= e.getCost();

        // ask if the user wants to equip this or not
        if (defaults.inputContinue("Do you want to equip this item or not?")) {
            equipItem(e);
        }

        addToInventory(e);
    }

    private void equipItem(Equipment e) {
        equiped.add(e);
    }

    private void unequipItem(Equipment e) {
        equiped.remove(e);
    }

    private void addToInventory(Equipment e) {
        inventory.add(e);
    }

    public void won(double earnings) {
        setConfidence(confidence + 0.1);

        capital += earnings;
    }

    /**
     * This method lets users customise the horse, by buying equipment,
     */
    public void customiseHorse() { // TODO make customise horses

        // buying equipment
        buyEquipment();
        // selling equipment
        sellEquipment();
        // equipping equipment
        // unequipping equipment
    }

    /**
     * This method lets users buy equipment for their horses
     */
    public void buyEquipment() { // DONE: finish customise horse
        String selectedCategory = "", selectedColour = "";
        boolean moreEquipment = true;
        // this method lets users customise horse breed and equipment

        while (this.getCapital() > 0 && moreEquipment) {

            // output horse details
            System.out.printf("%s   %n", this.toString());

            // select a category and colour for customising the horse
            selectedCategory = defaults.inputString(generateCategoryMessage());
            selectedColour = defaults.inputString(generateColourMessage());

            // print the equipment and get the user to choose an item
            Equipment itemChosen = Equipment.pickEquipmentItem(selectedCategory, selectedColour, getBreedSize());

            if (this.canBuy(itemChosen)
                    && defaults.inputContinue(String.format("Do you want to buy this piece of equipment? %n%s%n",
                            itemChosen.toString()))) {
                // DONE: bought item -> inv, equiped item -> equiped
                System.out.printf("You have bought %s, you have %d remaining. %n", itemChosen.toString(), capital);
                this.buyItem(itemChosen);
            }

            moreEquipment = defaults.inputContinue("Do you want to buy more equipment?");
        }

        // DONE: print EQUIPPED and INVENTORY

        printEquipment();
    }

    public void sellEquipment() {
        // show all equipment

        // sell it for cost * wearAndTear
    }

    public String generateColourMessage() {
        String message = "Please select a colour from the listed below";
        String[] colours = Equipment.getColours();
        // DONE: generate a message to select a colour

        message += "\nANY: All colours";

        for (int i = 0; i < colours.length; i++) {
            message += String.format("%n%s", colours[i]);
        }

        message += "\n\n";

        return message;
    }

    public String generateCategoryMessage() {
        String message = "Please select a category from the listed below. ";
        String[] categories = Equipment.getCategories();
        // DONE: generate a message to select a category

        message += "\nANY: All categories";

        for (int i = 0; i < categories.length; i++) {
            message += String.format("%n%s", categories[i]);
        }

        message += "\n\n";

        return message;
    }

    public boolean equipmentFits(Equipment e) { // checks if the equipment fits a horse based on it's size
        Size horseSize = this.breed.getBreedSize();

        return (e.getEquipmentSize() == horseSize);
    }

    public void printEquipment() {
        System.out.println("You have: ");

        for (Equipment e : inventory) {
            String output = e.toString();

            if (equiped.contains(e)) {
                output += ", equiped";
            }

            System.out.println(output);
        }

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

    public int getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }

    public LinkedList<Equipment> getInventory() {
        return inventory;
    }

    public LinkedList<Equipment> getEquiped() {
        return equiped;
    }

    public double getCapital() {
        return capital;
    }

    public Size getBreedSize() {
        return this.breed.getBreedSize();
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

}
