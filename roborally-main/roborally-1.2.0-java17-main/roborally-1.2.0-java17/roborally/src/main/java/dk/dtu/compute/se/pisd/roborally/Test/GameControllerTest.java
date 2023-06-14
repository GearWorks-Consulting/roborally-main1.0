package dk.dtu.compute.se.pisd.roborally.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALIZATION;
import static org.junit.jupiter.api.Assertions.*;
import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class GameControllerTest {

    private GameController gameController;
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board(10, 10, "Board 1");
        gameController = new GameController(board);
    }

    @Test
    public void testStartProgrammingPhase() {
        assertEquals(INITIALIZATION, board.getPhase());
        assertNull(board.getCurrentPlayer());
        assertEquals(0, board.getStep());

        gameController.startProgrammingPhase();

        assertEquals(Phase.PROGRAMMING, board.getPhase());
        assertNotNull(board.getCurrentPlayer());
        assertEquals(0, board.getStep());
    }

    @Test
    public void testFinishProgrammingPhase() {
        // Initialize the programming phase
        gameController.startProgrammingPhase();
        Player currentPlayer = board.getCurrentPlayer();

        // Make sure the current player is not null
        assertNotNull(currentPlayer);

        // Make the fields visible for the current player
        for (int i = 0; i < Player.NO_REGISTERS; i++) {
            CommandCardField field = currentPlayer.getProgramField(i);
            field.setVisible(true);
        }

        gameController.finishProgrammingPhase();

        // Verify that the fields are now invisible, phase changed, and current player and step reset
        for (int i = 0; i < Player.NO_REGISTERS; i++) {
            CommandCardField field = currentPlayer.getProgramField(i);
            assertFalse(field.isVisible());
        }
        assertEquals(Phase.ACTIVATION, board.getPhase());
        assertNotNull(board.getCurrentPlayer());
        assertEquals(0, board.getStep());
    }
}
