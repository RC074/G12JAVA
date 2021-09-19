import java.util.Random;
import java.util.Scanner;

public class CrapsMain {

    // Global variables so all methods can easily access

    // scanner object in to read input
    static Scanner in = new Scanner(System.in);

    // int bank which keeps track of the total money
    static int bank = 1000;

    // random object rand for simulating random dice rolls
    static Random rand = new Random();

    // an array of random messages
    static String[] messages = { "C’mon, take a chance!", "Quit while you're ahead" };

    // this method asks the user for the bet and returns it
    public static int getUserBetAmount() {

        // default bet to be higher than bank so it enters the while loop (I just don't
        // like do while)
        int bet = bank + 1;
        while (bet > bank || bet <= 0) {
            System.out.print("\nInput the amount you want to bet | $" + String.valueOf(bank) + " in bank: ");
            try {
                bet = in.nextInt();
            } catch (Exception InputMismatchException) {
                // if the user enters anything else other than an int, then re asks
                in.next();
                continue;
            }
        }
        return bet;
    }

    // this method just prints the random messages from the global var messages
    public static void randomMessage() {

        // the idea is to generate a random number between 0 - 5 inclusive the chance of
        // getting
        // a 0 or 1 is 1/3 which means the chance of output a random message is 1/3 when
        // this function
        // is called
        int randNum = rand.nextInt(6);
        if (randNum <= 1) {
            System.out.print("\n" + messages[randNum]);
        }
    }

    // when called, the user have to press enter to pass this function
    public static void enterToRoll() {
        System.out.print("\nPress enter to roll: ");
        in.nextLine();
    }

    // ask the user whether they want to play another round, returns either y or n
    public static char getUserChoiceToContinue() {
        while (true) {
            System.out.print("\nWould you like to continue playing? (y/n): ");
            String placeholder = in.nextLine().toLowerCase();

            // just checking whether the user entered properly or not
            if (placeholder.length() != 1 || (placeholder.charAt(0) != 'y' && placeholder.charAt(0) != 'n')) {
                continue;
            } else {
                return placeholder.charAt(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        char choiceToContinue = 'y'; // decides whether to play again
        // defaults to yes to enter first loop

        // main game loop
        while (choiceToContinue == 'y') {

            // 1/3 chance of output message
            randomMessage();

            int bet = getUserBetAmount();
            System.out.println("\nyour bet for this round is $" + String.valueOf(bet));
            in.nextLine();

            // keep track win or lose only for this round
            boolean win;

            // the point variable used in point phase
            int point = 0;

            int turn = 0;
            while (true) {
                turn++;
                enterToRoll();

                // generate two numbers randomly between 1 - 6 inclusive
                int num1 = rand.nextInt(6) + 1;
                int num2 = rand.nextInt(6) + 1;

                // their total
                int total = num1 + num2;

                System.out.println("\nrolled: " + String.valueOf(num1) + " and " + String.valueOf(num2) + " -> "
                        + String.valueOf(total));

                // checking whether this is the first turn in this round, this if statement will
                // be only
                // entered once in a round
                if (turn == 1) {
                    // set the point var
                    point = total;

                    // condition checking with regards to game rules, break when win or lose
                    if (total == 7 || total == 11) {
                        win = true;
                        break;
                    }
                    if (total == 2 || total == 3 || total == 12) {
                        win = false;
                        break;
                    }

                    // this else will be entered in point phase, break when win or lose
                } else {
                    if (total == point) {
                        win = true;
                        break;
                    }
                    if (total == 7) {
                        win = false;
                        break;
                    }
                }
            }
            // more conditional checking, if win or lose, add or subtract the bet from bank
            if (win) {
                System.out.println("\nYou Won! + $" + String.valueOf(bet));
                bank += bet;
            } else {
                System.out.println("\nYou Lost! - $" + String.valueOf(bet));
                bank -= bet;
            }
            if (bank == 0) {
                System.out.println("\nYou lost all your money, game over!");
                choiceToContinue = 'n';
            } else {
                // again, 1/3 chance out put message
                randomMessage();
                // asking whether to continue
                choiceToContinue = getUserChoiceToContinue();
            }
        }
        // checks if all money is lost, if not, tell the user all much they earned or
        // lost accordingly
        if (bank > 0) {
            if (bank < 1000) {
                System.out.println("\nSadly, you have lost $" + String.valueOf(1000 - bank));
            } else if (bank > 1000) {
                System.out.println("\nCongratulations, you have earned $" + String.valueOf(bank - 1000));
            } else {
                System.out.println("\nYou didn't earn or lose any money");
            }
        }
    }
}
