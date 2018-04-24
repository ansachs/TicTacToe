package UserInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class ConsoleUITest {
    private UI display = new ConsoleUI();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void PrintsCurrentBoardToConsole(){
        String[] board = {"0", "X", "2", "O", "X", "5", "6", "7", "8"};
        display.printBoard(board);
        assertEquals(" 0 | X | 2\n" +
                                "===+===+===\n" +
                                " O | X | 5\n" +
                                "===+===+===\n" +
                                " 6 | 7 | 8\n\n"
                                , outContent.toString());
    }

    @Test
    public void PrintsAMessage(){
        String message = "you win";
        display.printMessage(message);
        assertEquals("you win\n", outContent.toString());
    }

    //if method fails and has not more input returns a -1
    //successful call returns integer
    @Test
    public void getsUserInputAsInteger(){
        int result1, result2, result3;
        ByteArrayInputStream in = new ByteArrayInputStream("ab\r\n".getBytes());
        System.setIn(in);
        result1 = display.getUserInput();
        in = new ByteArrayInputStream("5\r\n".getBytes());
        System.setIn(in);
        result2 = display.getUserInput();
        in = new ByteArrayInputStream("4.12\r\n".getBytes());
        System.setIn(in);
        result3 = display.getUserInput();
        assertEquals(-1, result1);
        assertEquals(5, result2);
        assertEquals(-1, result3);

    }
}
