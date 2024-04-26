
public class HorseRacer {

    public static void main(String[] args) {
        simulateRace();
        // testCode();
    }

    public static void testCode() {
        String horseName1 = "Black Beauty";
        char horseSymbol1 = 'H', horseSymbol2 = 'C';
        double confidence1 = 0.8, confidence2 = 0.3;
        Horse horse = new Horse(horseSymbol1, horseName1, confidence1);

        System.out.printf("Expected Value: %s, Test Result: %s %n", horseName1, horse.getName());
        System.out.printf("Expected Value: %s, Test Result: %s %n", horseSymbol1, horse.getSymbol());
        horse.setSymbol(horseSymbol2);
        System.out.println("Setting new symbol to 'C'...");
        System.out.printf("Expected Value: %s, Test Result: %s %n", horseSymbol2, horse.getSymbol());

        horse.moveForward();
        System.out.printf("Expected Value: %d, Test Result: %d %n", 1, horse.getDistanceTravelled());

        System.out.printf("Expected Value: %b, Test Result: %b %n", false, horse.hasFallen());
        horse.fall();
        System.out.println("Horse Fell, Decrementing Confidence ...");
        System.out.printf("Expected Value: %b, Test Result: %b %n", true, horse.hasFallen());
        System.out.printf("Expected Value: %.1f, Test Result: %.1f %n", 0.7, horse.getConfidence());

        horse.won(100.0);
        System.out.println("Horse Won, Incrementing Confidence ...");
        System.out.printf("Expected Value: %.1f, Test Result: %.1f %n", 0.8, horse.getConfidence());

        System.out.println("Setting new confidence to 0.3 ...");
        horse.setConfidence(confidence2);
        System.out.printf("Expected Value: %.1f, Test Result: %.1f %n", 0.3, horse.getConfidence());
    }

    public static void simulateRace() {
        Race r = new Race(2);
        Horse a = new Horse('a', "A", 0.7), b = new Horse('b', "B", 0.8), c = new Horse('c', "C", 0.8);

        Horse[] horses = { a, b, c };

        for (int i = 0; i < 3; i++) {
            r.addHorse(horses[i], i + 1);
        }

        r.startRace();

        // System.out.println();
    }

}
