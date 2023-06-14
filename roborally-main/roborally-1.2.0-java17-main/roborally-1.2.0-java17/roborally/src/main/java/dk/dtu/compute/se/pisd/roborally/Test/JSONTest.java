package dk.dtu.compute.se.pisd.roborally.Test;

import dk.dtu.compute.se.pisd.roborally.JSON.LoadBoard;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {

    @Test
    public void testSaveBoard() {
        // Create a sample board
        Board board = new Board(5, 5);
        // Add elements to the board

        String boardName = "savedBoard";
        LoadBoard.saveBoard(board, boardName);

        // Add assertions to validate the saved board
        // For example, you can check if the board file exists in the expected location
    }
}
