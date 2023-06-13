package dk.dtu.compute.se.pisd.roborally.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNull;

import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;

public class CheckpointTest {
    private GameController gameController;
    private Player player;
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board(10, 10, "Board 1");
        player = new Player(board, "Player 1", "Some description");  // Adjust the arguments as needed
        board.setGameId(3);
        board.setCurrentPlayer(player);
        gameController = new GameController(board);
    }

    @Test
    public void testCheckPointTokener_PlayerHasCorrectTokens() {
        player.setTokens(3);
        Space space = new Space(board, 0, 0);
        CheckPoint checkPoint = new CheckPoint(3);
        space.setCheckPoint(checkPoint);
        player.setSpace(space);

        gameController.CheckPointTokener(player);

        assertEquals(4, player.getTokens());
    }

    @Test
    public void testCheckPointTokener_PlayerDoesNotHaveCorrectTokens() {
        player.setTokens(2);
        Space space = new Space(board, 0, 0);
        CheckPoint checkPoint = new CheckPoint(3);
        space.setCheckPoint(checkPoint);
        player.setSpace(space);

        gameController.CheckPointTokener(player);

        assertEquals(2, player.getTokens());
        assertEquals(true, player.getMessage());
    }

    @Test
    public void testCheckPointTokener_NoCheckPoint() {
        player.setTokens(3);
        Space space = new Space(board, 0, 0);
        player.setSpace(space);

        gameController.CheckPointTokener(player);

        assertEquals(3, player.getTokens());
    }

}
