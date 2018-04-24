package Players;

import GameLogic.GameConditions;
import java.util.ArrayList;

public class AI implements Player {
    private String AIToken;
    private String[] board;
    private String playerType = "AI";
    private String name;

    public AI(String AIToken) {
        this.AIToken = AIToken;
    }

    public AI(String AIToken, String name) {
        this.AIToken = AIToken;
        this.name = name;
    }

    public int getNextMove(String[] board) {
        this.board = board.clone();
        int spot;
        if (this.board[4] == "4") {
            spot = 4;
        } else {
            spot = this.getBestMove(this.board, AIToken, AIToken, 0);
        }
        return spot;
    }

    private int getBestMove(String[] board, String currentToken, String AIToken, int count) {
        String[] newBoard = board.clone();
        ArrayList<String> availableSpaces = new ArrayList<String>();
        int spot = -1;
        int getSpot = -1;
        boolean foundBestMove = false;

        String enemyToken = currentToken == "O" ? "X" : "O";

        for (String s : newBoard) {
            if (s != "X" && s != "O") {
                availableSpaces.add(s);
            }
        }

        // base case for self : return possibility if win or tie
        // base case for opponent: return possibility if tie
        // base case for opponent: return -1 if win
        for (String as : availableSpaces) {
            spot = Integer.parseInt(as);
            if (checkForTie(newBoard, Integer.parseInt(as), currentToken)) {
                return spot;
            } else if (checkForWinLose(newBoard, Integer.parseInt(as), currentToken)) {
                return(currentToken == AIToken ? spot : -1);
            }
        }

        // base case for current token, if opponent can win, attempt to block them, recurse on new board
        // only return spot if recursion does not return a failed state
        for (String as : availableSpaces) {
            spot = Integer.parseInt(as);
            if (checkForWinLose(newBoard, Integer.parseInt(as), enemyToken)) {
                getSpot = initiateRecursion(board, Integer.parseInt(as), currentToken, count);
                foundBestMove = true;
                if (getSpot != -1) {
                    return spot;
                }
            }
        }

        // if no neither case above hit, then loop through available space and initiate recursion on new board
        if(!foundBestMove) {

            for (String as : availableSpaces) {
                spot = Integer.parseInt(as);
                //check for fork is not necessary unless certain conditions are imposed, as shown in tests
                if (checkForFork(newBoard, as, availableSpaces, currentToken)) {
                    return(currentToken == AIToken ? spot : -1);
                }
            }

            for (String as : availableSpaces) {
                spot = Integer.parseInt(as);
                getSpot = initiateRecursion(board, Integer.parseInt(as), currentToken, count);
                if (getSpot != -1) {
                    return spot;
                }
            }
        }

        //default class return
        return -1;
    }

    //switch to opponents token and check board
    public int initiateRecursion(String[] board, int spot, String currentToken,int count){
        String[] newBoard = board.clone();
        newBoard[spot] = currentToken;
        String enemyToken = currentToken == "O" ? "X" : "O";
        return getBestMove(newBoard, enemyToken, AIToken, ++count);
    }

    private boolean checkForWinLose(String[] board, int spot, String currentToken) {
        String[] newBoard = board.clone();
        newBoard[spot] = currentToken;
        if (GameConditions.gameIsOver(newBoard)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean  checkForTie(String[] board, int spot, String currentToken) {
        String[] newBoard = board.clone();
        newBoard[spot] = currentToken;
        if (GameConditions.tie(newBoard)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkForFork(String[] board, String as, ArrayList<String> availableSpaces, String currentToken) {
        String[] newBoard = board.clone();
        int spot = Integer.parseInt(as);
        newBoard[spot] = currentToken;
        ArrayList<String> newAvailableSpaces = (ArrayList<String>) availableSpaces.clone();
        int count = 0;
        newAvailableSpaces.remove(as);
        for (String move2 : newAvailableSpaces) {
            int spot2 = Integer.parseInt(move2);
            newBoard[spot2] = currentToken;
            if (checkForWinLose(newBoard, Integer.parseInt(move2), currentToken)) {
                count++;
            }
            newBoard[spot2] = move2;
        }
        if (count > 1) {
            return true;
        }
        return false;
    }

    public String getToken() {
        return AIToken;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getName() {
        return name;
    }
}

