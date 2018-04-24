package GameLogic;

import UserInterface.ConsoleUI;

public class Runner {
    static Game game;
    public static void main(String[] args) {
        game = new Game(new ConsoleUI());
        game.play();
    }
}