import java.util.Random;
import java.util.Scanner;


public class Yahtzee {
  public static int[] rolls = new int[5];
  public static String[] categories = new String[14];
  public static int[] scores = new int[14];

  public static void upper(int choice) {
    int counter = 0;
    if (choice > 15) {
      Yahtzee.scores[choice - 1] = 0;
    }

    for (int i = 0; i < 5; i++) {
      if (choice == Yahtzee.rolls[i]) {
        counter += choice;
      }
    }
    // means crossed out

    Yahtzee.scores[choice - 1] = counter;

    Yahtzee.printScore();
  }

  public static void lower(int choice) {
    int total = 0;
    System.out.println((choice / 100) - 1);

    // cross out a category
    if (choice > 15) {
      Yahtzee.scores[(choice / 100) - 1] = 0;
    }

    // for three of a kind, four of a kind, and chance
    else if (choice == 7 || choice == 8 || choice == 14) {
      for (int i = 0; i < 5; i++) {
        System.out.println(Yahtzee.rolls[i]);
        total += Yahtzee.rolls[i];
      }
      Yahtzee.scores[choice - 1] = total;
    }

    // full house
    else if (choice == 9) {
      Yahtzee.scores[choice - 1] = 25;
    }
    // sm straight
    else if (choice == 10) {
      Yahtzee.scores[choice - 1] = 30;
    }
    // lrg straight
    else if (choice == 11) {
      Yahtzee.scores[choice - 1] = 40;
    }
    // yahtzee
    else if (choice == 12) {
      Yahtzee.scores[choice - 1] = 50;
    }
    // bonus yahtzee
    else {
      Yahtzee.scores[choice - 1] += 100;
    }

    Yahtzee.printScore();

  }

  public static void init() {
    Yahtzee.categories[0] = "Ones";
    Yahtzee.categories[1] = "Twos";
    Yahtzee.categories[2] = "Threes";
    Yahtzee.categories[3] = "Fours";
    Yahtzee.categories[4] = "Fives";
    Yahtzee.categories[5] = "Sixes";
    Yahtzee.categories[6] = "Three of a kind";
    Yahtzee.categories[7] = "Four of a kind";
    Yahtzee.categories[8] = "Full house";
    Yahtzee.categories[9] = "Small straight";
    Yahtzee.categories[10] = "Large straight";
    Yahtzee.categories[11] = "Yahtzee";
    Yahtzee.categories[12] = "Bonus Yahtzee";
    Yahtzee.categories[13] = "Chance";

    for (int i = 0; i < 14; i++) {
      // init everything to 0
      Yahtzee.scores[i] = -1;
      if (i == 13) {
        Yahtzee.scores[i] = 0;
      }
    }
  }

  public static void printScore() {
    System.out.println("Score card: ");
    for (int i = 0; i < 14; i++) {
      System.out.println((i + 1) + ". " + Yahtzee.categories[i] + " : " + Yahtzee.scores[i]);
    }
  }

  public static int score() {
    Scanner userIn = new Scanner(System.in);
    Yahtzee.printScore();
    System.out.println(
        "Which category do you choose?(Integer) If you want to cross out, add 00 to the end. EX: cross out yahtzee = 1200");
    int category = userIn.nextInt();
    // Yahtzee.categories[category - 1]
    if (category - 1 <= 6) {
      Yahtzee.upper(category);
    } else {
      Yahtzee.lower(category);
    }
    return 0;
  }

  public static void roll() {
    String in;
    Random randNum = new Random();
    Scanner userIn = new Scanner(System.in);
    boolean newRands = true;
    int die1 = randNum.nextInt(6) + 1;
    int die2 = randNum.nextInt(6) + 1;
    int die3 = randNum.nextInt(6) + 1;
    int die4 = randNum.nextInt(6) + 1;
    int die5 = randNum.nextInt(6) + 1;

    for (int i = 0; i < 3; i++) {
      if (newRands) {
        die1 = randNum.nextInt(6) + 1;
        die2 = randNum.nextInt(6) + 1;
        die3 = randNum.nextInt(6) + 1;
        die4 = randNum.nextInt(6) + 1;
        die5 = randNum.nextInt(6) + 1;
        Yahtzee.rolls[0] = die1;
        Yahtzee.rolls[1] = die2;
        Yahtzee.rolls[2] = die3;
        Yahtzee.rolls[3] = die4;
        Yahtzee.rolls[4] = die5;
      }
      System.out.println("----------------------------------------------------------------------");
      System.out.println("roll number " + (i + 1) + ": " + Yahtzee.rolls[0] + " " + Yahtzee.rolls[1]
          + " " + Yahtzee.rolls[2] + " " + Yahtzee.rolls[3] + " " + Yahtzee.rolls[4]);

      if (i == 2) {
        // go to fn
        Yahtzee.score();
        break;
      }

      System.out.println("Keep(K), reroll some(R), or reroll all(A)?: ");
      in = userIn.next();

      if (in.equalsIgnoreCase("k")) {
        // go to function to choose
        Yahtzee.score();
        break;
        // choose which to reroll
      } else if (in.equalsIgnoreCase("r")) {
        // because we do not want to reroll all
        newRands = false;
        if (i == 2) {
          System.out.println("Sorry, you cannot reroll, you will have to keep these");
        }
        System.out.println("How many dice do you want to reroll? ");
        int rerollAmt = userIn.nextInt();
        for (int j = 0; j < rerollAmt; j++) {
          // select which index to reroll
          System.out.println(
              "Please input the indexes you want to re-roll (1-5) one at a time from the beginning");
          int rerollIdx = userIn.nextInt();
          Yahtzee.rolls[rerollIdx - 1] = randNum.nextInt(6) + 1;
        }

      } else if (in.equalsIgnoreCase("a")) {
        if (i == 2) {
          System.out.println("Sorry, you cannot reroll all, you will have to keep these");
        }
        continue;
      }
    }
    // for end

  }

  public static int totalScore() {
    int upperTotal = 0;
    int total = 0;
    for (int i = 0; i < 6; i++) {
      upperTotal += Yahtzee.scores[i];
      total += Yahtzee.scores[i];
    }
    // upper bonus
    if (upperTotal >= 63) {
      total += 35;
    }

    for (int i = 6; i < 14; i++) {
      total += Yahtzee.scores[i];
    }
    return total;
  }

  public static void main(String[] args) {
    Yahtzee.init();
    for (int i = 0; i < 13; i++) {
      Yahtzee.roll();
    }
    int finalScore = Yahtzee.totalScore();
    System.out.println("Final score is: " + finalScore);
  }
}
