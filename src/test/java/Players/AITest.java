package Players;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AITest {

    private Player computer = new AI("X");
    private String[] boardWin1 = {"O", "X", "2", "O", "X", "5", "6", "7", "8"};
    private String[] boardWin2 = {"X", "O", "2", "O", "X", "5", "6", "7", "8"};
    private String[] boardWin3 = {"0", "O", "2", "3", "O", "5", "X", "X", "8"};

    private String[] boardLose1 = {"O", "1", "2", "O", "X", "X", "6", "7", "8"};
    private String[] boardLose2 = {"O", "O", "2", "3", "X", "5", "6", "7", "X"};
    private String[] boardLose3 = {"O", "X", "2", "3", "O", "5", "X", "7", "8"};

    private String[] boardFork1 = {"O", "1", "2", "3", "X", "5", "6", "O", "8"};
    private String[] boardFork2 = {"X", "1", "2", "3", "O", "5", "6", "7", "O"};
    private String[] boardFork3 = {"0", "1", "2", "3", "O", "5", "6", "7", "8"};

    @Test
    public void correctlyIdentifiesWinningSituations(){
        assertEquals(7, computer.getNextMove(boardWin1));
        assertEquals(8, computer.getNextMove(boardWin2));
        assertEquals(8, computer.getNextMove(boardWin3));
    }

    @Test
    public void correctlyIdentifiesLosingSituations(){
        assertEquals(6, computer.getNextMove(boardLose1));
        assertEquals(2, computer.getNextMove(boardLose2));
        assertEquals(8, computer.getNextMove(boardLose3));
    }

    //there are multiple ways to block these forks, but computer will search for and return the highest up and
    //closest to the 0 place
    @Test
    public void correctlyBlockOpponentAttemptToFork(){
        assertEquals(3, computer.getNextMove(boardFork1));
        assertEquals(2, computer.getNextMove(boardFork2));
        assertEquals(0, computer.getNextMove(boardFork3));
    }
}
