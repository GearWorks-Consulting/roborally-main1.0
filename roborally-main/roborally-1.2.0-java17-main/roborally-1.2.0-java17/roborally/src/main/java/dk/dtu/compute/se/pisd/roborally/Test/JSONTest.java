package dk.dtu.compute.se.pisd.roborally.Test;

import dk.dtu.compute.se.pisd.roborally.JSON.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {

    @Test
    public void testSaveBoard() {
        Board board = new Board(5, 5);

        String boardName = "savedBoard";
        LoadBoard.saveBoard(board, boardName);

    }
}
