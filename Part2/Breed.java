import java.util.LinkedList;
import java.util.Random;

public class Breed {
    private String breed;
    private int breedSize; // measured in hands

    private String coatColour, maneColour, tailColour, colourVariant;

    public Breed(String breed, int size, String coatColour, String colourVariant, String maneColour,
            String tailColour) {
        this.breed = breed;
        this.breedSize = size;
        this.coatColour = coatColour;
        this.colourVariant = colourVariant;
        this.maneColour = maneColour;
        this.tailColour = tailColour;
    }

    // use the given article as a basis for this class and horse variants
    // https://horseracingsense.com/12-common-horse-colors-patterns-pictures/

    public String toString() {
        return this.colourVariant + " " + this.coatColour + " " + this.breed + " Mane Colour: " + this.maneColour
                + " Tail Colour: " + this.tailColour + " Size: " + this.breedSize + " hands";
    }

    // Generate breeds

    public static LinkedList<Breed> generateBreeds() {
        LinkedList<Breed> breedList = new LinkedList<Breed>();

        // String[] breeds = { "Thoroughbred", "Quarter Horse", "Arabian", "Appaloosa",
        // "Paint", "Mustang", "Morgan",
        // "Tennessee Walking Horse", "Standardbred", "Friesian", "Percheron",
        // "Clydesdale", "Shire" };

        String[] coatColours = { "Black", "Brown", "Orange" };
        String[] colourVariants = { "Spotted", "Solid", "Patchwork" };
        String[] hairColours = { "Black", "Brown", "Red" };
        Integer[] sizes = { 13, 14, 15, 16, 17, 18 };

        for (int i = 0; i < 50; i++) {
            String coatColour = defaults.getRandomElement(coatColours);
            String colourVariant = defaults.getRandomElementNormalDistribution(colourVariants);
            String maneColour = defaults.getRandomElement(hairColours);
            String tailColour = defaults.getRandomElement(hairColours);
            int size = defaults.getRandomElementNormalDistribution(sizes);

            String breed = ""; // TODO implement breed
            // TODO make this a hash map or something else, this shouldn't be a linked list

            // breedList.add(new Breed(breed, size, coatColour, maneColour, tailColour));
        }
        return breedList;
    }

    // Getters / Accessors

    public String getCoatColour() {
        return coatColour;
    }

    public String getManeColour() {
        return maneColour;
    }

    public String getTailColour() {
        return tailColour;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getBreedSize() {
        return this.breedSize;
    }

}
