import java.util.LinkedList;

public class Breed {
    private String breed;
    private String size;
    private String coatColour, maneColour, tailColour, colourVariant;

    public Breed(String breed, String size, String coatColour, String colourVariant, String maneColour,
            String tailColour) {
        this.breed = breed;
        this.size = size;
        this.coatColour = coatColour;
        this.colourVariant = colourVariant;
        this.maneColour = maneColour;
        this.tailColour = tailColour;
    }

    // use the given article as a basis for this class and horse variants
    // https://horseracingsense.com/12-common-horse-colors-patterns-pictures/

    public String toString() {
        return this.colourVariant + " " + this.coatColour + " " + this.breed + " Mane Colour: " + this.maneColour
                + " Tail Colour: " + this.tailColour + " Size: " + this.size;
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

    public String getSize() {
        return this.size;
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
        String[] sizes = { "13 hands", "14 hands", "15 hands", "16 hands", "17 hands", "18 hands" };

        for (int i = 0; i < 50; i++) {
            String coatColour = getRandomElement(coatColours);
            String colourVariant = getRandomElementNormalDistribution(colourVariants);
            String maneColour = getRandomElement(hairColours);
            String tailColour = getRandomElement(hairColours);
            String size = getRandomElementNormalDistribution(sizes);

            String breed = ""; // TODO implement breed
            // TODO make this a hash map or something else, this shouldn't be a linked list

            breedList.add(new Breed(breed, size, coatColour, maneColour, tailColour));
        }
        return breedList;
    }

    private static <T> T getRandomElement(T[] array) {
        return array[(int) (Math.random() * array.length)];
    }

    private static <T> T getRandomElementNormalDistribution(T[] array) { // TODO implement normal distribution
        return array[(int) (Math.random() * array.length)];
    }

}
