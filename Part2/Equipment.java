import java.util.*;

public class Equipment {
    private String name, colour, category;
    private double speedModifier, weight, cost, wearAndTear;
    private Size equipmentSize;

    private static LinkedList<Equipment> equipmentList = null;
    private static HashMap<Size, Double> sizeWeightCoefficient = null;
    private static HashMap<String, Double> categoryWeightCoefficient = null, categoryWeightings = null;
    private final static String[] categories = { "Saddle", "Reins", "Bridle", "Horseshoes", "Blanket", "Stirrups" },
            colours = { "Black", "Brown", "Gray", "Orange", "Red" };

    public Equipment(String colour, String category, double speedModifier, Size size) {
        double sizeWeightFactor = sizeWeightCoefficient.get(size),
                categoryWeightFactor = categoryWeightCoefficient.get(category),
                baseWeight = categoryWeightings.get(category);

        this.category = category;
        this.colour = colour;
        this.equipmentSize = size;
        this.speedModifier = speedModifier;
        this.wearAndTear = 1.0;

        this.weight = baseWeight * sizeWeightFactor;
        this.cost = ((100 + baseWeight * speedModifier * speedModifier) * sizeWeightFactor * categoryWeightFactor);

        this.name = this.toString();
    }

    public Equipment(String colour, String category, Size size) {
        this(colour, category, 1, size);
    }

    public String toString() {
        return String.format("%s %s %s, Weight: %.01fkg, Cost: %.02f", equipmentSize, colour, category, weight, cost);
    }

    private static LinkedList<Equipment> generateEquipment(String category, String color, Size size) {
        // generates a list of equipment to buy from

        // if 'ANY' entered, add all possible combinations for that category
        if (category.equals("ANY")) {
            // loop through categories and create that equipment
            for (String c : categories) {
                generateEquipment(c, color, size);
            }
        } else if (color.equals("ANY")) {
            // loop through colours and create said equipment
            for (String c : colours) {
                generateEquipment(category, c, size);
            }
        } else if (!defaults.stringInArray(categories, category) || !defaults.stringInArray(colours, color)) {
            System.out.println("No such equipment exists. Please type the words correctly. \n");
        } else {
            // create the given peice of equipment
            equipmentList.add(new Equipment(color, category, size));
            // DONE: add these to a list, and then print this list, and then have the user
        }

        return equipmentList;
    }

    public static Equipment pickEquipmentItem(String category, String color, Size size) {

        // sets up required info
        equipmentList = new LinkedList<Equipment>();
        equipmentList = generateEquipment(category, color, size);

        // loop through equipment list
        Iterator<Equipment> it = equipmentList.iterator();
        HashMap<Integer, Equipment> equipmentHashMap = new HashMap<Integer, Equipment>();
        Equipment chosenItem;
        Integer i = 0, equipmentListSize = equipmentList.size();

        if (equipmentListSize == 0) {
            return null;
        } else if (equipmentListSize == 1) {
            return it.next();
        }

        while (it.hasNext()) {
            Equipment item = it.next();
            System.out.printf("%d: %s%n", i, item.toString());

            equipmentHashMap.put(i, item);
            i++;
        }

        // DONE: choose an item based on what is printed
        chosenItem = equipmentHashMap.get(defaults.inputInt("Please choose one of the above: \n"));

        return chosenItem;
    }

    /******************************************************************************************************/
    // Static methods

    /**
     * Generates the static data required for doing calculations based on Equipment
     * weight and category.
     */
    public static void generateStaticData() {
        Equipment.getSizeWeightFactor();
        Equipment.getCategoryWeightFactor();
        Equipment.getCategoryWeightings();
    }

    /**
     * Generates and returns the scaling of Equipment's weight based on their size
     */
    private static HashMap<Size, Double> getSizeWeightFactor() {
        if (sizeWeightCoefficient == null) {
            sizeWeightCoefficient = new HashMap<Size, Double>();
            sizeWeightCoefficient.put(Size.Small, 1.0);
            sizeWeightCoefficient.put(Size.Medium, 1.25);
            sizeWeightCoefficient.put(Size.Large, 1.5);
        }

        return sizeWeightCoefficient;
    }

    /**
     * Generates and returns a list of weights for each category of Equipment that
     * can be purchased.
     * 
     * @return
     */
    private static HashMap<String, Double> getCategoryWeightings() {
        if (categoryWeightings == null) {
            categoryWeightings = new HashMap<String, Double>();
            categoryWeightings.put("Saddle", 15.0);
            categoryWeightings.put("Reins", 1.0);
            categoryWeightings.put("Bridle", 2.0);
            categoryWeightings.put("Horseshoes", 4.0);
            categoryWeightings.put("Blanket", 8.0);
            categoryWeightings.put("Stirrups", 5.0);
        }

        return categoryWeightings;
    }

    /**
     * Generates and returns a list of coefficients for how quickly each category of
     * equipment scales with Size
     * 
     * @return
     */
    private static HashMap<String, Double> getCategoryWeightFactor() {
        if (categoryWeightCoefficient == null) {
            categoryWeightCoefficient = new HashMap<String, Double>();
            categoryWeightCoefficient.put("Saddle", 4.0);
            categoryWeightCoefficient.put("Reins", 1.0);
            categoryWeightCoefficient.put("Bridle", 1.2);
            categoryWeightCoefficient.put("Horseshoes", 0.5);
            categoryWeightCoefficient.put("Blanket", 1.5);
            categoryWeightCoefficient.put("Stirrups", 2.5);
        }

        return categoryWeightCoefficient;
    }

    /******************************************************************************************************/
    // Getters / Accessors

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public Size getEquipmentSize() {
        return equipmentSize;
    }

    public String getEquipmentSizeName() {
        return equipmentSize.name();
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public double getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public static String[] getCategories() {
        return categories;
    }

    public static String[] getColours() {
        return colours;
    }

}
