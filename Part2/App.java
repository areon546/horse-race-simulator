public class App {

    // Main driver method
    public static void main(String[] args) {
        startRace();
    }

    private static void startRace() {
        Race r = new Race(2);
        Horse a = new Horse('a', "A", 0.7), b = new Horse('b', "B", 0.8), c = new Horse('c', "C", 0.8);

        Horse[] horses = { a, b, c };

        for (int i = 0; i < 3; i++) {
            r.addHorse(horses[i], i + 1);
        }

        GUI.setRace(r);

        GUI.home();
        // GUI.goToAdjustHorses();
    }
}