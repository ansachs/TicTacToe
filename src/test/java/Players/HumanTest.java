package Players;

import UserInterface.ConsoleUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class HumanTest {

    private Human human = new Human("X",new ConsoleUI());
    private String[] board = {"0", "X", "2", "O", "X", "5", "6", "7", "8"};
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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
    public void CheckHumanChoiceDoesNotAllowInvalidHumanMove(){
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        assertEquals(-1, human.checkHumanChoice(board));
        in = new ByteArrayInputStream("{".getBytes());
        System.setIn(in);
        assertEquals(-1, human.checkHumanChoice(board));
        in = new ByteArrayInputStream("99".getBytes());
        System.setIn(in);
        assertEquals(-1, human.checkHumanChoice(board));
        in = new ByteArrayInputStream("-5".getBytes());
        System.setIn(in);
        assertEquals(-1, human.checkHumanChoice(board));
        in = new ByteArrayInputStream("4.59".getBytes());
        System.setIn(in);
        assertEquals(-1, human.checkHumanChoice(board));
    }

    @Test
    public void CheckHumanChoiceAllowsValidInput(){
        ByteArrayInputStream in = new ByteArrayInputStream("8".getBytes());
        System.setIn(in);
        assertEquals(8, human.checkHumanChoice(board));
        in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        assertEquals(0, human.checkHumanChoice(board));
    }
}
