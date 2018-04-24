package GameLogic;


import UserInterface.ConsoleUI;
import UserInterface.UI;
import Players.AI;
import Players.Human;
import Players.Player;

/**
 * Tic-Tac-Toe: TWo-player console version.
 */
public class Game {

    private String[] board = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};

    private UI ui;
    private Player player1;
    private Player player2;
    private Player orderFirst;
    private Player orderSecond;

    public Game(UI ui) {
        this.ui = ui;
    }

    public void play() {
        int move;
        Player winner = orderSecond;
        initGame();
        ui.printBoard(board);
        do {
            move = orderFirst.getNextMove(board);
            board[move] = orderFirst.getToken();
            ui.printMessage(String.format("*************** Board after %s **************",orderFirst.getName()));
            ui.printBoard(board);
          if (GameConditions.gameIsOver(board) && !GameConditions.tie(board)) {
            winner = orderFirst;
          } else if (!GameConditions.gameIsOver(board) && !GameConditions.tie(board)) {
            move = orderSecond.getNextMove(board);
            board[move] = orderSecond.getToken();
            ui.printMessage(String.format("*************** Board after %s **************",orderSecond.getName()));
            ui.printBoard(board);
          }
        } while (!GameConditions.gameIsOver(board) && !GameConditions.tie(board));

        if (GameConditions.tie(board)) {
          ui.printMessage("Game is a Tie");
        } else {
          ui.printMessage(String.format("%s is the winner",winner.getName()));
        }
        ui.printMessage("Game over\n");
  }

  /** Initializes the game */
  public void initGame() {
      int userSelectionGame = -1;
      int userSelectionToken = -1;
      userSelectionGame = printOptions(
              "select from one of the following games",
            "1 - human v. human\n" +
            "2 - computer v. computer\n" +
            "3 - human v. computer",
            1, 3);
      switch (userSelectionGame) {
          case 1:
              userSelectionToken = printOptions("Select player 1 user token", "1 - X\n" +
                      "2 - O", 1, 2);
              player1 = new Human(userSelectionToken == 1 ? "X" : "O", ui, "player 1");
              player2 = new Human(userSelectionToken == 1 ? "O" : "X", ui, "player 2");
              break;
          case 2:
              userSelectionToken = printOptions("Select AI 1 user token", "1 - X\n" +
                      "2 - O", 1, 2);
              player1 = new AI(userSelectionToken == 1 ? "X" : "O", "player 1");
              player2 = new AI(userSelectionToken == 1 ? "O" : "X", "player 2");
              break;
          case 3:
              userSelectionToken = printOptions("Select player 1 user token", "1 - X\n" +
                      "2 - O", 1, 2);
              player1 = new Human(userSelectionToken == 1 ? "X" : "O", ui, "player 1");
              player2 = new AI(userSelectionToken == 1 ? "O" : "X", "player 2");
              break;
          default:
              break;
      }
      int userSelectionOrder = -1;
      userSelectionOrder = printOptions(
              "Select which player will go first",
              String.format("1 - player1 (%s - %s)\n2 - player2 (%s - %s)",
                player1.getPlayerType(), player1.getToken(),
                player2.getPlayerType(), player2.getToken()),
              1, 2);
      orderFirst = userSelectionOrder == 1 ? player1 : player2;
      orderSecond = userSelectionOrder == 1 ? player2 : player1;
  }

  public int printOptions(String string1, String string2, int lower, int upper) {
      ui.printMessage(string1);
      ui.printMessage(string2);
      int selection = -1;
      do {
          ui.printMessage(String.format("Enter [%d-%d]",lower, upper));
          selection = ui.getUserInput();
      } while(selection < lower || selection > upper);
      return selection;
  }


}
