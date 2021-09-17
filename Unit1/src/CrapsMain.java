import java.util.Random;
import java.util.Scanner;

public class CrapsMain {

    static Scanner in = new Scanner(System.in);
    static int bank = 1000;
    static Random rand = new Random();
    static String[] messages = {"C’mon, take a chance!", "Quit while you're ahead"}; 

    public static int getUserBetAmount() {
        int bet = bank + 1;
        while (bet > bank || bet <= 0) {
            System.out.print("\nInput the amount you want to bet | $" + String.valueOf(bank) + " in bank: ");
            try {
                bet = in.nextInt();
            } catch (Exception InputMismatchException) {
                in.next();
                continue;
            }
        }
        return bet;
    }

    public static void enterToRoll() {
        System.out.print("\nPress enter to roll: ");
        in.nextLine();
    }

    public static char getUserChoiceToContinue() {
        while (true) {
            System.out.print("\nWould you like to continue playing? (y/n): ");
            String placeholder = in.nextLine().toLowerCase();
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

        while (choiceToContinue == 'y') {
            int bet = getUserBetAmount();
            System.out.println("\nyour bet for this round is $" + String.valueOf(bet));
            in.nextLine();

            boolean win;
            int point = 0;
            int turn = 0;
            while (true) {
                turn++;
                enterToRoll();
                int num1 = rand.nextInt(6) + 1;
                int num2 = rand.nextInt(6) + 1;
                int total = num1 + num2;
                System.out.println("\nrolled: " + String.valueOf(num1) + " and " + String.valueOf(num2) + " -> " + String.valueOf(total));
                if (turn == 1) {
                    point = total;
                    if (total == 7 || total == 11) {
                        win = true;
                        break;
                    }
                    if (total == 2 || total == 3 || total == 12) {
                        win = false;
                        break;
                    }
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
                choiceToContinue = getUserChoiceToContinue();
            }
        }
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
