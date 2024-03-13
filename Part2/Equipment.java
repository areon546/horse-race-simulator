import java.util.LinkedList;

public class Equipment {
    private String name, colour, size;
    private double speedModifier, weight;

    public static LinkedList<Equipment> equipmentList = null;

    public Equipment(String name, String colour, String size, double speedModifier, double weight) {
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.speedModifier = speedModifier;
        this.weight = weight;
    }

    // Getters / Accessors

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public String getSize() {
        return size;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public double getWeight() {
        return weight;
    }

    // Generate Equipment

    public static LinkedList<Equipment> getEquipment() {
        if (Equipment.equipmentList == null) {
            Equipment.equipmentList = generateEquipment();
        }

        return Equipment.equipmentList;
    }

    public static LinkedList<Equipment> generateEquipment() {
        LinkedList<Equipment> equipmentList = new LinkedList<Equipment>();

        String[] types = { "Saddle", "Reins", "Bridle", "Horseshoes", "Blanket", "Stirrups" };
        String[] colours = { "Gray", "Black", "Brown", "Dark Gray" };

        // String[] colours = { "Black", "Brown", "Red", "Blue", "Green", "Yellow" };
        String[] sizes = { "Small", "Medium", "Large" }; // Small = 13 -> 14 hands, horse = 15->16,
        double[] speedModifiers = { 0.8, 0.85, 0.9, 0.95, 1.0 };
        double[] weights = { 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5 }; // kg

        String name = ""; // colour variant + colour + type + size +

        for (String size : sizes) {

        }

        return equipmentList;
    }

}
