import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * This class is used to represent horses, and uses a vari
 * 
 * @author Artur Baran
 * @version v1.7 2023.04.26
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
     * 
     * @param horseSymbol     The symbol that represents the horse during a Race.
     * @param horseName       The name of the horse.
     * @param horseConfidence The confidence of the horse, which represents their
     *                        speed and probability to fall.
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.setConfidence(horseConfidence);

        this.breed = new Breed("Black Horse", 14, "Black", "Solid", "Gray");
    }

    /**
     * Constructor for objects of class Horse
     * 
     * @param horseSymbol     The symbol that represents the horse during a Race.
     * @param horseName       The name of the horse.
     * @param horseConfidence The confidence of the horse, which represents their
     *                        speed and probability to fall.
     * @param horseBreed      The breed of the horse, which represents their
     *                        colours. Not currently displayed.
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence, Breed horseBreed) {
        this(horseSymbol, horseName, horseConfidence);
        this.breed = horseBreed;
    }

    /**
     * To string method for the Horse class, there to have an easy way to output the
     * horse's name.
     */
    public String toString() {
        return String.format("%s", this.name); // name, confidence, breed, total distance travelled?, equipment list?,
                                               // symbol?
    }

    // Other methods of class Horse

    /**
     * This method simulates a horse falling down, and decrease the horse's
     * confidence as a result.
     */
    public void fall() { // DONE: make confidence decrease
        this.setConfidence(this.confidence - 0.1);
        fallen = true;
    }

    /**
     * This method resets the distance travelled for the horse, and whether it's
     * fallen yet or not.
     */
    public void goBackToStart() {
        // DONE: if multiple races occur, all fall eventually
        this.totalDistanceTravelled += this.distanceTravelled;
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    /**
     * Moves the horse forward by one unit, and increases the distance travelled by
     * 0.1.
     */
    public void moveForward() {
        this.distanceTravelled++;
    }

    /**
     * This method checks if the horse has moved forward based on a random number.
     * 
     * @return boolean: Whether the horse has moved forward.
     */
    public boolean canMove() {
        return Math.random() < (this.confidence);
    }

    /**
     * This method checks if the horse can fall based on a random number.
     * 
     * @return boolean: Whether the horse has fallen.
     */
    public boolean canFall() {
        return Math.random() < (0.1 * this.confidence * this.confidence);
    }

    /**
     * This method is called if a horse wins a race, and it increases the amount of
     * capital they have based on the earnings of that race for winning.
     * 
     * @param earnings: The amount of money the horse has earned by winning.
     */
    public void won(double earnings) {
        setConfidence(confidence + 0.1);

        capital += earnings;
    }

    // Equipment methods

    /**
     * This method checks if the horse can buy a piece of equipment based on the
     * price of that equipment.
     * 
     * @param e: The equipment for the horse that the user wants to buy.
     * @return boolean: Whether the horse can buy the equipment or not.
     */
    public boolean canBuy(Equipment e) {

        if (e == null) {
            return false;
        }

        if (capital > e.getCost()) {
            return true;
        }

        return false;
    }

    /**
     * This method lets the user buy a piece of equipment for the horse, and then if
     * they want to, they can later on equip said equipment.
     * When bought, it gets added to the horse's inventory.
     * 
     * @param e: The equipment in question
     */
    public void buyItem(Equipment e) {
        this.capital -= e.getCost();

        System.out.printf("You have bought: %s, you have %.2f remaining. %n", e.toString(), capital);

        // ask if the user wants to equip this or not
        if (defaults.inputContinue("Do you want to equip this item or not?")) {
            equipItem(e);
        }

        addToInventory(e);
    }

    private void equipItem() {
        // DONE: ask the user which item to equip
        Map<Integer, Equipment> equipmentListMap = displayEquipment(this.inventory);
        Integer itemChosen = defaults.inputInt("Please choose an item to equip: "); // ask the user to input a number
        Equipment e = equipmentListMap.get(itemChosen);

        System.out.println(e.toString());
        this.equipItem(e);
    }

    private void unequipItem() {
        // DONE: ask the user which item to equip
        Map<Integer, Equipment> equipmentListMap = displayEquipment(this.equiped);
        Integer itemChosen = defaults.inputInt("Please choose an item to unequip: "); // ask the user to input a number
        Equipment e = equipmentListMap.get(itemChosen);

        System.out.println(e.toString());
        this.unequipItem(e);
    }

    private void equipItem(Equipment e) {
        if (e != null && equipmentFits(e)) {
            equiped.add(e);
            System.out.printf("You have equiped %s. %n", e.toString());
        }
    }

    private void unequipItem(Equipment e) {
        if (e != null && equipmentFits(e)) {
            equiped.remove(e);
            System.out.printf("You have unequiped %s. %n", e.toString());
        }
    }

    private void addToInventory(Equipment e) {
        inventory.add(e);
    }

    /**
     * This method lets users customise the horse, by buying equipment,
     */
    public void customiseHorse() { // TODO make customise horses fully functional

        if (defaults.inputContinue("Do you want to BUY equipment for your horse?")) {
            // buying equipment
            buyEquipment();
        }

        if (defaults.inputContinue("Do you want to SELL equipment for your horse?")) {
            // selling equipment
            sellEquipment();
        }

        if (defaults.inputContinue("Do you want to EQUIP equipment for your horse?")) {
            // equipping equipment
            equipItem();
        }

        if (defaults.inputContinue("Do you want to UNEQUIP equipment for your horse?")) {
            // unequipping equipment
            unequipItem();
        }

    }

    /**
     * This method lets users buy equipment for their horses
     */
    public void buyEquipment() { // DONE: finish customise horse
        String selectedCategory = "", selectedColour = "";
        boolean moreEquipment = true;

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
                this.buyItem(itemChosen);
            } else {
                System.out.println("You cannot buy this item.");
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

        if (inventory.size() > 0) {
            System.out.println("You have: ");

            for (Equipment e : inventory) {
                String output = e.toString();

                if (equiped.contains(e)) {
                    output += ", equiped";
                }

                System.out.println(output);
            }
        } else {
            System.out.println("You have no equipment.");
        }
    }

    public Map<Integer, Equipment> displayEquipment(LinkedList<Equipment> equipmentList) {
        Map<Integer, Equipment> equipmentHashMap = new HashMap<Integer, Equipment>();
        Iterator<Equipment> it = equipmentList.iterator();
        Integer i = 0;

        while (it.hasNext()) {
            Equipment item = it.next();
            System.out.printf("%d: %s%n", i, item.toString());

            equipmentHashMap.put(i, item);
            i++;
        }

        return equipmentHashMap;
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
