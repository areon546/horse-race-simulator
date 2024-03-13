
public class HorseRacer {

    public static void main(String[] args) {
        simulateRace();
        // testCode();
    }

    public static void testCode() {

        System.out.println("AAAAAAAAAAAAAAAAA\r");

        // System.out.print("\033\143");
        System.out.print("\033[A");

        System.out.println("CCBB");

    }

    public static void simulateRace() {
        Race r = new Race(10, 3, "mets");
        Horse a = new Horse('a', "A", 0.7), b = new Horse('b', "B", 0.8), c = new Horse('c', "C", 0.8);

        r.addHorse(a, 1);
        // r.addHorse(b, 2);
        r.addHorse(c, 3);

        r.startRace();
    }
}
