import java.util.Random;

public class defaults {

    /**
     * returns a value within a normal distribution defied by standardDeviation and
     * mean
     * 
     * @param standardDeviation
     * @param mean
     * @param min               min and max provide a range of acceptable values,
     *                          and while i dont like them, they make it easier to
     *                          work with this function
     * @param max
     * @return the number returned is a value within the given normal distribution,
     *         within the given range
     * 
     */
    public static double getValueInNormalDistribution(double standardDeviation, int mean, int min, int max) {
        Random r = new Random();
        double number = mean;
        // System.out.println(number);
        do {
            number = (r.nextGaussian() * standardDeviation) + mean;
            number = Math.round(number); // TODO without this, its slightly biased towards lower indeces, with this its
                                         // biased towards larger indeces, dont really care rn but its not fully a
                                         // normal distribution as a result, in fact it might be because max is
                                         // exclusive, and min is inclusive, that this problem exists
            // System.out.println(number + " " + ((int) number < max && (int) number >=
            // min));
        } while (!((int) number < max && (int) number > min));

        return number;
    }

    public static <T> T getRandomElement(T[] array) {
        return array[(int) (Math.random() * array.length)];
    }

    public static <T> T getRandomElementNormalDistribution(T[] array) { // TODO implement normal distribution
        return array[(int) defaults.getValueInNormalDistribution(1, (array.length / 2), 0, array.length)];
    }
}
