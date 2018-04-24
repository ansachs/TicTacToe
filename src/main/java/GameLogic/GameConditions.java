package GameLogic;

public class GameConditions {

    public static boolean gameIsOver(String[] board) {
        return board[0] == board[1] && board[1] == board[2] ||
                board[3] == board[4] && board[4] == board[5] ||
                board[6] == board[7] && board[7] == board[8] ||
                board[0] == board[3] && board[3] == board[6] ||
                board[1] == board[4] && board[4] == board[7] ||
                board[2] == board[5] && board[5] == board[8] ||
                board[0] == board[4] && board[4] == board[8] ||
                board[2] == board[4] && board[4] == board[6];
    }

    public static boolean tie(String[] board) {
        for (String space : board) {
            if (space != "X" && space != "O") {
                return false;
            }
        }
        return true;
    }

}
