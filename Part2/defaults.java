/*
* Author   : Artur Baran
* Stud ID  : 230518733
* Version  : y
* Date     : xxxx.xx.xx
* 
* Stores a variety of frequently used procedures and functions that I don't wanna rewrite. 
*/

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class defaults {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        // System.out.println(br.readLine().equals("")); // readLine returns a line

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value
        // System.out.println(br.read()); // read returns a character's int value

        // System.out.println(br.read()); // read returns a character's int value

        // return;
    }

    ///////////////////////////////////////////////////////////////////
    /* Misc */

    // prints the text input
    //
    public static void print(String message) {
        System.out.println(message);
        return;
    } // END print

    // prints the absolute difference between two integers
    //
    public static void printDifferenceInt(int num1, int num2) {
        System.out.printf("Difference: %d %n", Math.abs(num1 - num2));
        return;
    } // END printDifference

    // outputs the day of the week of the month based on the two inputs
    //
    public static String dateConverter(int dayIndex, int monthIndex) {

        String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
                months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September",
                        "October",
                        "November", "December" };
        String day = daysOfWeek[dayIndex - 1], month = months[monthIndex - 1];

        return String.format("A %s in %s", day, month);
    } // END dateConverter

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /* Inputing Data */

    // calls Scanner to receive a kb input
    //
    public static String inputString(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);

        return sc.nextLine();
    } // END inputString

    // inputs a string form the user, while also communicating to them that there
    // has been an error
    //
    public static String inputStringWithErrorMessage(String message, String errorMessage) {
        System.out.println(message);

        return inputString(errorMessage);
    }

    // gets a character input from the user
    //
    public static char inputChar(String message) {
        System.out.println("The first character inputted will be considered your input. ");
        return inputString(message).charAt(0);
    } // END inputChar

    public static boolean inputContinue(String message) {
        return (inputChar((message + " y/N\n")) == 'y');
    }

    // gets an input from the user, and if it is an integer, it will return it
    // otherwise it will ask again. repeatedly.
    //
    public static int inputInt(String message) { // TODO make an inputDigit method, like inputInt but only for first
                                                 // char
        String input = inputString(message);
        int n;

        // checks if the inputted text is an integer
        while (!isInt(input, true)) {

            input = inputStringWithErrorMessage(message,
                    "Please input an integer, without any decimals, numbers, or special characters. ");

        }
        n = Integer.parseInt(input);
        System.out.println(n);

        return n;
    } // END inputInt

    // gets an input from the user, and if it is an doublw, it will return it
    // otherwise it will ask again. repeatedly.
    //
    public static double inputDouble(String message) {
        String input = inputString(message);
        double n;

        // checks if the inputted text is an integer
        while (!isDouble(input)) {

            input = inputStringWithErrorMessage(message,
                    "Please input an integer, without any decimals, numbers, or special characters. ");

        }
        n = Double.parseDouble(input);

        return n;
    } // END inputInt

    //////////////////////////////////////////////////////////////////////////////
    /* Input Validation */

    // converts to an integer
    //
    public static int convInt(String s) {
        return Integer.parseInt(s);
    } // END convInt

    // converts to a double
    //
    public static double convDouble(String s) {
        return Double.parseDouble(s);
    } // END convDouble

    // checks if the inputted string can be converted into an integer
    //
    public static boolean isInt(String s, boolean canBeNegative) {
        char c = s.charAt(0);
        boolean isInt = isInt(c) || (canBeNegative && (c == '+' || c == '-'));

        if (s.length() == 1) { // 1 digit
            return isInt;

        } else if (canBeNegative) { // you only want to check if the first digit is a '+' or a '-', this is how you
                                    // can do it recursively
            return (isInt && isInt(s.substring(1), false));

        } else { // remaining digits
            return (isInt && isInt(s.substring(1), false));

        }
    } // END isInt

    // checks if a given character is an integer character
    //
    public static boolean isInt(char c) {
        return (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
                || c == '9' || c == '.');
    } // END isInt

    // converts a character to an integer
    //
    public static int convertInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    } // END convertInt

    // converts a string to an integer
    //
    public static int convertInt(String s) {
        return Integer.parseInt(s);
    } // END convertInt

    // checks if the inputted string can be converted into a Double
    //
    public static boolean isDouble(String possibleDouble) {
        try {
            double n = Double.parseDouble(possibleDouble);

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    } // END isDouble

    //////////////////////////////////////////////////////////////////
    /* Generating Random Numbers */

    // creates a random integer between the bounds. min inclusive
    //
    public static int getRandomInt(int min, int max) { // max is inclusive
        Random rand = new Random();
        int randNum = rand.nextInt(max + 1 - min) + min;

        return randNum;
    } // END getRandomInt

    // creates a random index from to to arrayLength-1, inclusive
    //
    public static int getRandomIndex(int arrayLength) {
        Random rand = new Random();
        int randNum = rand.nextInt(arrayLength - 1);

        return randNum;
    } // END getRandomIndex

    /////////////////////////////////////////////////////
    /* Arrays */

    // creates an integer array
    //
    public static int[] makeIntArr(int lengthOfArray) {
        int[] arr = new int[lengthOfArray];

        for (int i = 0; i < lengthOfArray; i++) {
            arr[i] = inputInt(String.format("Please input the value for index %s: ", i));
        }

        return arr;
    } // END makeIntArr

    // print an integer array
    //
    public static void printIntArr(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d %n", arr[i]);
        }

        return;
    } // END printIntArr

    // creates a String array
    //
    public static String[] makeStringArray(int arrayLength) {
        String[] arr = new String[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            arr[i] = inputString(String.format("Please input the value for index %s", i));
        }

        return arr;
    } // END makeStingArray

    // print a String array
    //
    public static void printStringArr(String[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%s %n", arr[i]);
        }

        return;
    } // END printStringArr

    // creates an double array
    //
    public static double[] makeDoubleArr(int lengthOfArray) {
        double[] arr = new double[lengthOfArray];

        for (int i = 0; i < lengthOfArray; i++) {
            arr[i] = inputDouble(String.format("Please input the value for index %s: ", i));
        }

        return arr;
    } // END makeDoubleArr

    // print an array of doubles
    //
    public static void printDoubleArr(Double[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d %n", arr[i]);
        } // END i

        return;
    } // END printDoubleArr

    //////////////////////////////////////////////////////////////////////////////////
    /* Sort Int Arr */

    /* Bubble Sort */
    // performs bubble sort on an integer array, sorting it to be in ASCENDING order
    //
    public static int[] bubbleSortAsc(int[] arr) {
        final int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; i < arrLength - i - 1; i++) {
                // swap check (next has to be bigger)
                if (arr[j + 1] > arr[j]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            } // END j
        } // END i

        return arr;
    } // END bubbleSortAsc

    // performs bubble sort on an integer array, sorting it to be in DESCENDING
    // order
    //
    public static int[] bubbleSortDesc(int[] arr) {
        final int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; i < arrLength - i - 1; i++) {
                // swap check (current has to be bigger)
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            } // END j
        } // END i

        return arr;
    } // END bubbleSortDesc

    /* Insertion Sort */

    /* Quick Sort */

    /* Merge Sort */

    /* Tim Sort */

    //////////////////////////////////////////////////////////////////////////
    /* Search Algorithms */

    /* Linear Search */
    public static int linearSearchInt(int[] arr, int searchKey) {
        final int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            if (arr[i] == searchKey) {
                return i;
            }
        } // END i

        return -1;
    } // END linearSearchInt

    // searches for a specific String in a String array
    public static int linearSearchString(String[] arr, String searchKey) {
        final int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            if (arr[i] == searchKey) {
                return i;
            }
        } // END for

        return -1;
    } // END linearSearchString

    public static boolean stringInArray(String[] arr, String searchKey) {
        final int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            // System.out.println(arr[i] + ": " + searchKey);
            if (arr[i].equals(searchKey)) {
                return true;
            }
        } // END for

        return false;
    }

    // removes a specific index from a String array, and shifts the remaining
    // elements back
    public static String[] removeStringFromArrAndShift(String[] arr, String searchKey) {
        final int arrLength = arr.length;
        int startSwapping = arrLength;
        boolean found = false;

        for (int i = 0; i < arrLength; i++) {
            if (arr[i] == searchKey) {
                arr[i] = null;
                startSwapping = i;
                found = true;
            }

            if (i > startSwapping) {
                arr[i - 1] = arr[i];
            }

        } // END for

        if (found) {
            arr[arrLength - 1] = null;
        }

        return arr;
    } // END removeStringFromArrAndShift

    /* Binary Search */

    /* Fibonacci Search */

    ////////////////////////////////////////////////////////////////
    /* Weird Stuff */

    public static void funStuff() {
        int c = 5;
        boolean run = true;

        for (int i = 0; i < (5 + c) && run; i++) {
            System.out.println(i);

            if (i == 3) {
                c += 10;
            }

            if (i == 7) {
                run = false;
            }
        }

        for (String input = ""; !input.equals("Yes"); input = inputString("Do you want to stop? ")) {
            System.out.println("Hi friend");
        }

        System.out.println("Bye Friend. See you later");

    }

    //////////////////////////////////////////////////
    /* File R+W */

    // reads all of the lines of a file, and returns them in a string array
    //
    public static String[] readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] s = new String[countLines(fileName)];
        int index = 0;
        String fileInput = br.readLine();

        if (fileInput == null) {
            br.close();
            return null;
        }

        // loops through every line in the file and adds it to the array
        while (fileInput != null) {
            s[index] = fileInput;

            fileInput = br.readLine();
            index++;
        } // END while

        br.close();
        return s;
    } // END readFile

    // counts the number of lines in a given file
    //
    public static int countLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        String line = br.readLine();

        while (line != null && !line.equals("")) {
            line = br.readLine();
            lines++;
        } // END while

        br.close();

        return lines;
    } // END countLines

    // writes a given string into a given file
    //
    public static void writeFile(String fileName, String s, boolean append) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fileName, append));

        pw.println(s);

        pw.close();
        return;
    } // END writeFile

    // writes a given string array into a given file
    //
    public static void writeFile(String fileName, String[] s) throws IOException {

        writeFile(fileName, s[0], false);
        for (int i = 1; i < s.length; i++) {
            writeFile(fileName, s[i], true);
        } // END for

        return;
    } // END writeFile

    // removes a specific index from a String array
    //
    public static String[] removeStringFromArr(String[] arr, String searchKey) {
        final int arrLength = arr.length;
        boolean found = false;
        String[] newArr = new String[arrLength - 1];
        int i = 0;

        if (arrLength == 1) {
            return null;
        }

        for (int newArrIndex = 0; (newArrIndex < arrLength - 1) && i < arrLength; newArrIndex++) {
            if (arr[i].equals(searchKey) && !found) {
                i++;
                found = true;
            }

            newArr[newArrIndex] = arr[i];

            i++;

        } // END for

        return newArr;
    } // END removeStringFromArr

    // removes a specified line from a file
    //
    public static void removeStringFromFile(String fileName, String stringToRemove) throws IOException {

        // load file as String[]
        String[] fileContents = readFile(fileName);

        // search through String[] and remove string to remove
        fileContents = removeStringFromArr(fileContents, stringToRemove);

        // write to file as String[]
        if (fileContents == null) {
            writeFile(fileName, "", false);
        } else {
            writeFile(fileName, fileContents);
        }

        return;
    } // END removeStringFromFile

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
} // END defaults
