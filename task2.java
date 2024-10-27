import java.util.Random;
import java.util.Scanner;

/**
 * This program prompts the user for a number of random values (0-999),
 * stores them in an array, and then sorts them into even numbers (ascending)
 * and odd numbers (descending). The program handles invalid inputs and memory errors.
 * @author Nahid Alam
 * @version 1.0
 */
public class Main {

    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0-999 are desired?";
    static final String RANDOM_NUMBERS_LIST = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED = "Here are the random numbers arranged:";
    static final String NO_ODD_NUM = "No Odd Numbers";
    static final String NO_EVEN_NUM = "No Even Numbers";
    static final String INVALID_INPUT = "Invalid Input";
    static final int MAX_RAND = 1000;  // Max random number (0 - 999)

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int numRand;

        // Step 1: Get user input for the number of random numbers
        System.out.println(USER_INPUT_PROMPT);
        try {
            String input = sc.nextLine();
            numRand = Integer.parseInt(input);

            if (numRand < 1) {
                System.out.println(INVALID_INPUT);
                return;
            }

            // Step 2: Try generating random numbers and catch OutOfMemoryError
            try {
                int[] randNums = new int[numRand];

                // Step 3: Generate random numbers between 0 and 999
                System.out.println(RANDOM_NUMBERS_LIST);
                for (int i = 0; i < numRand; i++) {
                    randNums[i] = rand.nextInt(MAX_RAND);
                    System.out.print(randNums[i] + " ");
                }
                System.out.println();

                // Step 4: Separate even and odd numbers into temporary arrays
                int[] evenNums = new int[numRand];
                int[] oddNums = new int[numRand];
                int evenCount = 0;
                int oddCount = 0;

                for (int num : randNums) {
                    if (num % 2 == 0) {
                        evenNums[evenCount++] = num;
                    } else {
                        oddNums[oddCount++] = num;
                    }
                }

                // Step 5: Sort even numbers in ascending order (bubble sort)
                bubbleSortAsc(evenNums, evenCount);

                // Step 6: Sort odd numbers in descending order (bubble sort)
                bubbleSortDesc(oddNums, oddCount);

                // Step 7: Print sorted numbers
                System.out.println(RANDOM_NUMBERS_SORTED);

                if (evenCount > 0) {
                    for (int i = 0; i < evenCount; i++) {
                        System.out.print(evenNums[i] + " ");
                    }
                } else {
                    System.out.print(NO_EVEN_NUM);
                }

                System.out.print(" - ");  // Separator between even and odd numbers

                if (oddCount > 0) {
                    for (int i = 0; i < oddCount; i++) {
                        System.out.print(oddNums[i] + " ");
                    }
                } else {
                    System.out.print(NO_ODD_NUM);
                }

                System.out.println();

                // Step 8: Print count of even and odd numbers
                System.out.println(
                    "Of the above " + numRand + " numbers, " + evenCount + " were even and " + oddCount + " odd."
                );

            } catch (OutOfMemoryError e) {
                System.out.println(
                    "The system cannot handle this number of random numbers "
                    + "due to memory limitations."
                );
            }

        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT);  // Handle non-integer input
        } finally {
            sc.close();
        }
    }

    /**
     * Sorts an array in ascending order using bubble sort.
     * @param arr the array to sort
     * @param len the length of the valid portion of the array to sort
     */
    private static void bubbleSortAsc(final int[] arr, final int len) {
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array in descending order using bubble sort.
     * @param arr the array to sort
     * @param len the length of the valid portion of the array to sort
     */
    private static void bubbleSortDesc(final int[] arr, final int len) {
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

