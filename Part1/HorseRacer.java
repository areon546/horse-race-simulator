
public class HorseRacer {

    public static void main(String[] args) {
        simulateRace();
        // testCode();
    }

    public static void testCode() {
        Horse a = new Horse('a', "A", 0.7);
        Equipment.generateStaticData();

        a.customiseHorse();
    }

    public static void simulateRace() {
        Race r = new Race(1);
        Horse a = new Horse('a', "A", 0.7), b = new Horse('b', "B", 0.8), c = new Horse('c', "C", 0.8);

        Horse[] horses = { a, b, c };

        for (int i = 0; i < 3; i++) {
            r.addHorse(horses[i], i + 1);
        }

        r.startRace();

        System.out.println();
    }
}
