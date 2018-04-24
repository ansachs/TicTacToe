package Players;

import UserInterface.ConsoleUI;
import UserInterface.UI;


public class Human implements Player {
    private String userToken;
    private UI ui;
    private String playerType = "Human";
    private String name;

    public Human(String userToken, UI ui) {
        this.userToken = userToken;
        this.ui = ui;
    }

    public Human(String userToken, UI ui, String name) {
        this.userToken = userToken;
        this.ui = ui;
        this.name = name;
    }

    public int getNextMove(String[] board){
        int validInput = -1;  // for input validation
        do {
            ui.printMessage(String.format("%s please select a space", name));
            ui.printMessage("Enter [0-8]:\n");
            validInput = checkHumanChoice(board);
        } while (validInput == -1);  // repeat until input is valid
        return validInput;
    }

    public int checkHumanChoice(String[] board){
        int spot = ui.getUserInput();
        if (spot < 0 || spot > 8) {
            ui.printMessage("location must be between 0 and 8");
            return -1;
        } else if (board[spot] != "X" && board[spot] != "O") {
            return spot;  // userChoice okay, exit loop
        } else {
            ui.printMessage("must be unoccupied space");
            return -1;
        }
    }

    public String getToken() {
        return userToken;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getName() {
        return name;
    }
}
