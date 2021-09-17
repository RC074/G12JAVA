import java.util.Random;

public class CrapsCOMP {
    static Random rand = new Random();
    static int wins = 0;
    static int loses = 0;
    static int bank = 1000;
    static int BET = 100;
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int point = 0;
            int turn = 0;
            while (true) {
                turn++;
                int total = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
                if (turn == 1) {
                    point = total;
                    if (total == 7 || total == 11) {
                        wins++;
                        bank += BET;
                        break;
                    }
                    if (total == 2 || total == 3 || total == 12) {
                        loses++;
                        bank -= BET;
                        break;
                    }
                } else {
                    if (total == point) {
                        wins++;
                        bank += BET;
                        break;
                    } 
                    if (total == 7) {
                        loses++;
                        bank -= BET;
                        break;
                    }
                }
            }
            if (bank == 0) {
                break;
            }
        }
        System.out.println("Total rounds: " + String.valueOf(wins+loses) + "\nWins: " + String.valueOf(wins) + "\nLoses: " + String.valueOf(loses) + "\nWin(%): " + String.valueOf((double)wins/((double)wins+(double)loses) * 100) + "%" + "\nLose(%): " + String.valueOf((double)loses/((double)wins+(double)loses) * 100) + "%" + "\nBank: " + String.valueOf(bank));
    }
}
