import java.util.Random;
import java.util.Scanner;


public class Yahtzee {

  public static void main(String[] args) {
    Random randNum = new Random();
    Scanner userIn = new Scanner(System.in);
    boolean newRands = true;
    int die1 = randNum.nextInt(6) + 1;
    int die2 = randNum.nextInt(6) + 1;
    int die3 = randNum.nextInt(6) + 1;
    int die4 = randNum.nextInt(6) + 1;
    int die5 = randNum.nextInt(6) + 1;
    int[] rolls = new int[5];
    String in;

    //
    // System.out.println("input: " + in);


    for (int i = 0; i < 3; i++) {
      if (newRands) {
        die1 = randNum.nextInt(6) + 1;
        die2 = randNum.nextInt(6) + 1;
        die3 = randNum.nextInt(6) + 1;
        die4 = randNum.nextInt(6) + 1;
        die5 = randNum.nextInt(6) + 1;
        rolls[0] = die1;
        rolls[1] = die2;
        rolls[2] = die3;
        rolls[3] = die4;
        rolls[4] = die5;

      }

      System.out.println("roll number " + (i + 1) + ": " + rolls[0] + " " + rolls[1] + " "
          + rolls[2] + " " + rolls[3] + " " + rolls[4]);

      if (i == 2) {
        // go to fn
        System.out.println("last roll");
        break;
      }

      System.out.println("Keep(K), reroll some(R), or reroll all(A)?: ");
      in = userIn.next();

      if (in.equalsIgnoreCase("k")) {
        // go to function to choose
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
          rolls[rerollIdx - 1] = randNum.nextInt(6) + 1;
        }

      } else if (in.equalsIgnoreCase("a")) {
        if (i == 2) {
          System.out.println("Sorry, you cannot reroll all, you will have to keep these");
        }
        continue;
      }

    }

  }

}
