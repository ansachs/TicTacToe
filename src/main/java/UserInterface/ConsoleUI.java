package UserInterface;

import java.util.Scanner;

public class ConsoleUI implements UI {

    public ConsoleUI() {
    }

    public void printBoard(String[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + "\n===+===+===\n" + " " + board[3] + " | " + board[4] + " | " + board[5] + "\n===+===+===\n" + " " + board[6] + " | " + board[7] + " | " + board[8] + "\n"); // print all the board cells
    }

    public void printMessage(String string){
        System.out.println(string);
    }

    public int getUserInput(){
//        System.out.println("input a number");
        Scanner userChoice = null;
        Integer output = null;
        String userInput = null;
            do {
                userChoice = new Scanner(System.in);
                if (userChoice.hasNextLine()) {
                    try {
                        userInput = userChoice.nextLine();
                        output = Integer.parseInt(userInput);
                    } catch (Exception e) {
                        System.out.println("must be an integer");
                    }
                } else {
                    output = -1;
                }
            } while(output == null);
        return output;
    }
}
