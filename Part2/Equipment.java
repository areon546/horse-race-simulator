import java.util.*;

public class Equipment {
    private String name, colour, equipmentSize, category;
    private double speedModifier, weight, cost;

    public static LinkedList<Equipment> equipmentList = null;

    private static HashMap<String, Double> sizeWeightCorrelation = null;

    public Equipment(String size, String colour, String category, double speedModifier, double baseWeight) {
        double sizeWeightFactor = sizeWeightCorrelation.get(size);

        this.category = category;
        this.colour = colour;
        this.equipmentSize = size;
        this.speedModifier = speedModifier;

        this.weight = baseWeight * sizeWeightFactor;
        this.cost = ((100 + baseWeight * speedModifier) * sizeWeightFactor) - baseWeight;

        this.name = this.toString();
    }

    public String toString() {
        return String.format("%s %s %s, Weight: %.01fkg, Cost: %.02f", equipmentSize, colour, category, weight, cost);
    }

    // Getters / Accessors

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public String getEquipmentSize() {
        return equipmentSize;
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

    private static LinkedList<Equipment> generateEquipment() {
        LinkedList<Equipment> equipmentList = new LinkedList<Equipment>();

        String[] categories = { "Saddle", "Reins", "Bridle", "Horseshoes", "Blanket", "Stirrups" };
        int[] baseWeights = { 15, 1, 2, 4, 8, 5 };
        String[] colours = { "Gray", "Black", "Brown", "Orange", "Red" };
        String[] sizes = { "Small", "Medium", "Large" };
        double[] speedModifiers = { 0.8, 0.85, 0.9, 0.95, 1.0 };
        double[] weights = { 0, 5, 10, 15, 20, 25, 30, 35 };

        for (int i = 0; i < categories.length; i++) {
            for (String size : sizes) {
                for (String colour : colours) {
                    equipmentList.add(new Equipment(size, colour, categories[i], 1, baseWeights[i]));
                }
            }
        }
        return equipmentList;
    }

    public static void printEquipment(Horse h) {
        Iterator<Equipment> it = Equipment.equipmentList.iterator();
        while (it.hasNext()) {
            Equipment item = it.next();

            if (h.equipmentFits(item)) {
                System.out.println(item.getName());
            }
        }
    }

    public static HashMap<String, Double> getSizeWeightFactor() {
        if (Equipment.sizeWeightCorrelation == null) {
            Equipment.sizeWeightCorrelation = generateSizeWeightFactor();
        }

        return Equipment.sizeWeightCorrelation;
    }

    private static HashMap<String, Double> generateSizeWeightFactor() {
        HashMap<String, Double> correlation = new HashMap<String, Double>();
        correlation.put("Small", 1.0);
        correlation.put("Medium", 1.25);
        correlation.put("Large", 1.5);

        return correlation;
    }

    public static void generateStaticData() {
        Equipment.getSizeWeightFactor();
        Equipment.getEquipment();
    }

}
