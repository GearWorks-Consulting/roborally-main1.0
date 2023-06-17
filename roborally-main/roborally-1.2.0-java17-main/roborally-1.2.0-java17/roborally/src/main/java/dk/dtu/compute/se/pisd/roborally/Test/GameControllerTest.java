package dk.dtu.compute.se.pisd.roborally.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALISATION;
import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALIZATION;
import static org.junit.jupiter.api.Assertions.*;
import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;
/**
 *
 * @author Moiz H. Khalil, s215617@dtu.dk
 *@version 1.8 QA.
 * @since 17-6-2023
 *
 */
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
        assertEquals(INITIALISATION, board.getPhase());
        assertNull(board.getCurrentPlayer());
        assertEquals(0, board.getStep());

        gameController.startProgrammingPhase();

        assertEquals(Phase.PROGRAMMING, board.getPhase());
        assertTrue(board.getCurrentPlayer() == null);
        assertEquals(0, board.getStep());
    }


    @Test
        public void testFinishProgrammingPhase() {
            board.setPhase(Phase.PROGRAMMING);
            board.setCurrentPlayer(board.getPlayer(0));
            board.setStep(0);

            gameController.finishProgrammingPhase();

            assertEquals(Phase.ACTIVATION, board.getPhase());
            assertNull(board.getCurrentPlayer()); // Change to assertNull

        }


    }
