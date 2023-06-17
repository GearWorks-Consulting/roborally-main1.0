package dk.dtu.compute.se.pisd.roborally.Test;

import dk.dtu.compute.se.pisd.roborally.JSON.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Moiz H. Khalil, s215617@dtu.dk
 *@version 1.8 QA.
 * @since 17-6-2023
 *
 */
public class JSONTest {

    @Test
    public void testSaveBoard() {
        Board board = new Board(5, 5);

        String boardName = "savedBoard";
        //LoadBoard.saveBoard(board, boardName);

    }
}
