import java.util.Random;


public class Yahtzee {

  public static void main(String[] args) {
    Random randNum = new Random();
    //
    // Scanner userIn = new Scanner(System.in);
    // System.out.println("Enter something: ");
    //
    // String in = userIn.nextLine();
    // System.out.println("input: " + in);



    int die1 = randNum.nextInt(6) + 1;
    int die2 = randNum.nextInt(6) + 1;
    int die3 = randNum.nextInt(6) + 1;
    int die4 = randNum.nextInt(6) + 1;
    int die5 = randNum.nextInt(6) + 1;
    System.out.println("your roll: " + die1 + " " + die2 + " " + die3 + " " + die4 + " " + die5);


  }

}
