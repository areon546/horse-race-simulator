
public class HorseRacer {

    public static void main(String[] args) {
        simulateRace();
        // testCode();
    }

    public static void testCode() {
        Horse a = new Horse('a', "A", 0.7);
        Equipment.generateStaticData();

        a.buyEquipment();

    }

    public static void simulateRace() {
        Race r = new Race(10, 3, "meters"), r2 = new Race(25, 5, "meters");
        Horse a = new Horse('a', "A", 0.7), b = new Horse('b', "B", 0.8), c = new Horse('c', "C", 0.8);

        Horse[] horses = { a, b, c };

        for (int i = 0; i < 3; i++) {
            r.addHorse(horses[i], i + 1);
        }

        r.startRace();

        for (int i = 0; i < 3; i++) {
            r2.addHorse(horses[i], i + 1);
        }

        r2.startRace();
    }
}
