/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.*;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.*;

/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
public class GameController {
    /**
     * Creates an empty gameboard which the game controller is associated with.
     */

    //The game board associated with the game controller.
    final public Board board;
    //The list of options for interactive commands.
    final private List<String> OPTIONS_Interactive = Arrays.asList("Left", "Right");
    //The current turn number.
    int turn = 0;

    /**
     * The GameController class manages the game logic and controls the interactions
     * between the Board, Players, and other components of the game.
     */

    /**
            * Creates a GameController instance associated with the given game board.
            *
            * @param board the game board
     */
    public GameController(@NotNull Board board) {
        this.board = board;
    }

    /**
     * @param space the space to which the current player should move
     */
    public void moveCurrentPlayerToSpace(@NotNull Space space) {
        /**
         * Moves the current player to the specified space and sets the current player to the next player by +1 and currentplayer.
         *
         * @param space the space to which the current player should move too.
         */

        if (space != null && space.board == board) {
            Player currentPlayer = board.getCurrentPlayer();

            if (currentPlayer != null && space.getPlayer() == null) {
                currentPlayer.setSpace(space);
                int playerNumber = turn;
                board.setCurrentPlayer(board.getPlayer(playerNumber));
                playerNumber = (playerNumber + 1) % board.getPlayersNumber();
            }
        }
    }

    // XXX: V2
    public void startProgrammingPhase() {
        // Set the game phase to Programming
        board.setPhase(Phase.PROGRAMMING);
        // Set the current player to the player at the current turn
        board.setCurrentPlayer(board.getPlayer(turn));
        // Set the current step to the current turn
        board.setStep(turn);

        // Reset the command card fields for all players
        for (int i = 0; i < board.getPlayersNumber(); i++) {
            Player player = board.getPlayer(i);
            if (player != null) {
                // Reset the program fields
                for (int j = 0; j < Player.NO_REGISTERS; j++) {
                    CommandCardField field = player.getProgramField(j);
                    field.setCard(null);
                    field.setVisible(true);
                }
                // Generate random command cards for the card fields
                for (int j = 0; j < Player.NO_CARDS; j++) {
                    CommandCardField field = player.getCardField(j);
                    field.setCard(generateRandomCommandCard());
                    field.setVisible(true);
                }
            }
        }
    }

    // XXX: V2
    private CommandCard generateRandomCommandCard() {
        // Get all available commands
        Command[] commands = Command.values();
        // Generate a random index within the range of available commands
        int random = (int) (Math.random() * commands.length);

        // Create a new command card with the randomly selected command
        return new CommandCard(commands[random]);
    }

    /**
     * This method handles the button click event for finishing the programming phase.
     * It hides the program fields and makes the first field visible for each player.
     * It sets the game phase to Activation and updates the current player and step.
     */
    public void finishProgrammingPhase() {
        makeProgramFieldsInvisible();
        makeProgramFieldsVisible(turn);
        board.setPhase(Phase.ACTIVATION);
        board.setCurrentPlayer(board.getPlayer(turn));
        board.setStep(turn);
    }

    // XXX: V2
    private void makeProgramFieldsVisible(int register) {
        // Check if the register is within a valid range
        if (register >= 0 && register < Player.NO_REGISTERS) {
            // Make the program field visible for each player
            for (int i = 0; i < board.getPlayersNumber(); i++) {
                Player player = board.getPlayer(i);
                CommandCardField field = player.getProgramField(register);
                field.setVisible(true);
            }
        }
    }

    // XXX: V2
    private void makeProgramFieldsInvisible() {
        // Hide all program fields for each player
        for (int i = 0; i < board.getPlayersNumber(); i++) {
            Player player = board.getPlayer(i);
            for (int j = 0; j < Player.NO_REGISTERS; j++) {
                CommandCardField field = player.getProgramField(j);
                field.setVisible(false);
            }
        }
    }

    // XXX: V2
    public void executePrograms() {
        // Disable step mode
        board.setStepMode(false);
        // Continue executing the programs
        continuePrograms();
    }

    // XXX: V2
    public void executeStep() {
        board.setStepMode(true);
        continuePrograms();
    }

    // XXX: V2
    private void continuePrograms() {
        // Execute the next step repeatedly until the activation phase ends or step mode is disabled
        do {
            executeNextStep();
        } while (board.getPhase() == Phase.ACTIVATION && !board.isStepMode());
    }

    // XXX: V2
    private void executeNextStep() {

        // Get the current player
        Player currentPlayer = board.getCurrentPlayer();

        // Check if the game is in the activation phase and the current player is valid
        if (board.getPhase() == Phase.ACTIVATION && currentPlayer != null) {
            int step = board.getStep();

            // Check if the step is within a valid range
            if (step >= 0 && step < Player.NO_REGISTERS) {
                // Get the command card for the current step
                CommandCard card = currentPlayer.getProgramField(step).getCard();

                // Check if the card is not null
                if (card != null) {
                    Command command = card.command;
                    // Check if the command requires player interaction
                    if (command.isInteractive()) {
                        board.setPhase(Phase.PLAYER_INTERACTION);
                        return;

                    }
                    // Execute the command for the current player
                    executeCommand(currentPlayer, command);
                }
                // Update the next player and step
                int nextPlayerNumber = board.getPlayerNumber(currentPlayer) + 1;
                // Set the current player to the next player
                if (nextPlayerNumber < board.getPlayersNumber()) {
                    board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
                } else {
                    step++;
                    if (step < Player.NO_REGISTERS) {
                        // Make the program fields visible for the next step
                        makeProgramFieldsVisible(step);

                        // Set the step and current player to the first player
                        board.setStep(step);
                        board.setCurrentPlayer(board.getPlayer(turn));
                    } else {
                        // Go to the next turn
                        turn = (turn + 1) % board.getPlayersNumber();

                        // Check for a winning condition and restart the programming phase
                        winGame();
                        startProgrammingPhase();
                    }
                }
            } else {
                // Invalid step value, assert false
                assert false;
            }
        } else {
            // Invalid game phase or current player, assert false
            assert false;
        }

    }

    /**
     * Executes the specified command for the given player.
     * Checks the player and command, and uses a switch statement to determine the form of CommandCards.
     */

    private void executeCommand(@NotNull Player player, Command command) {
        if (player != null && player.board == board && command != null) {


            switch (command) {
                case FORWARD:
                    // Execute forward movement command
                    this.moveForward(player);
                    break;
                case RIGHT:
                    // Execute right turn command
                    this.turnRight(player);
                    break;
                case LEFT:
                    // Execute left turn command
                    this.turnLeft(player);
                    break;
                case UTURN:
                    // Execute U-turn command
                    this.uTurn(player);
                    break;
                case FAST_FORWARD:
                    // Execute fast forward command
                    this.fastForward(player);
                    break;

                // Handle left-right option command (implementation missing)
                case OPTION_LEFT_RIGHT:
                    break;

            }
        }
    }

    /**
     * Represents the movement functionality of a player in the game.
     * Moves the player 1 vector direction forward, depending on the heading.
     */
    public void moveForward(@NotNull Player player) {
        Space space = player.getSpace();
        Space playerSpace = player.getSpace();


        Heading heading = player.getHeading();
        Space target = board.getNeighbour(space, heading);

        // Check if player, board, and spaces are valid
        if (player != null && player.board == board && space != null && playerSpace.wallFace(player.getHeading())) {

            // Check the heading and target space coordinates for movement
            if ((space.x < target.x) && (player.getHeading() == WEST)) {

            } else if ((space.x > target.x) && (player.getHeading() == EAST)) {

            } else if ((space.y > target.y) && (player.getHeading() == SOUTH)) {

            } else if ((space.y < target.y) && (player.getHeading() == NORTH)) {

            }

            else if (target != null) {

                // Perform player movement and trigger associated actions
                if (robotCollide(target, heading)) {

                    target.setPlayer(player);
                    player.setSpace(target);
                    GearRotation(player);
                    conveyerTransport(player);
                    CheckPointTokener(player);
                    PushPanel(player);
                }
            }

            /**
             * Detects the player's heading and check if meets a wall on the same space as the player with a macthing heading.
             * if it is the same it cannot move.(Continue if not).
             */
        } else if (player != null && player.board == board && space != null) {
            int check = 1;
            //int check =1;
            for (int i = 0; i < check; i++) {
                // Check if there is no wall in front of the player or if the heading is north
                if (!playerSpace.PlacedWall(player.getHeading()) || this.Checkheading(player) == Heading.NORTH) {
                    continue;
                }
                playerSpace = player.board.getNeighbour(playerSpace, player.getHeading());
                // If there is a player in the target space without a wall, recursively call moveForward
                if (playerSpace.getPlayer() != null && !playerSpace.PlacedWall(player.getHeading())) {
                    moveForward(player);
                }
            }

            // Handle invalid player, board, or space
        } else {

        }
    }

    /**
     * Moves the player 2 vector direction forward, depending on the heading. by calling the forward method
     */
    public void fastForward(@NotNull Player player) {
        moveForward(player);
        moveForward(player);
    }

    /**
     * Moves the player's heading direction 90 degress, or right and checks for the current phasing direction.
     */
    public void turnRight(@NotNull Player player) {

        // Update player's heading based on the current heading
        if (board.getCurrentPlayer().getHeading() == Heading.NORTH)
            player.setHeading(Heading.EAST);

        else if (board.getCurrentPlayer().getHeading() == Heading.EAST)
            player.setHeading(Heading.SOUTH);
        else if (board.getCurrentPlayer().getHeading() == Heading.SOUTH)
            player.setHeading(Heading.WEST);
        else if (board.getCurrentPlayer().getHeading() == Heading.WEST)
            player.setHeading(Heading.NORTH);
    }


    /**
     * Moves the player's heading direction 90 degress, or left and checks for the current phasing direction.
     */
    public void turnLeft(@NotNull Player player) {
        // Update player's heading based on the current heading
        if (board.getCurrentPlayer().getHeading() == Heading.NORTH)
            player.setHeading(Heading.WEST);
        else if (board.getCurrentPlayer().getHeading() == Heading.EAST)
            player.setHeading(Heading.NORTH);
        else if (board.getCurrentPlayer().getHeading() == Heading.SOUTH)
            player.setHeading(Heading.EAST);
        else if (board.getCurrentPlayer().getHeading() == Heading.WEST)
            player.setHeading(Heading.SOUTH);
    }

    /**
     * Executes a U-turn by turning the player 180 degrees, equivalent to two left turns.
     */
    public void uTurn(@NotNull Player player) {
        turnLeft(player);
        turnLeft(player);
    }

    /**
     * Moves a command card from a source field to a target field.
     *
     * @param source the source field from which to move the command card
     * @param target the target field to which to move the command card
     * @return true if the move was successful, false otherwise
     * @throws NullPointerException if either source or target is null
     */
    public boolean moveCards(@NotNull CommandCardField source, @NotNull CommandCardField target) {
        CommandCard sourceCard = source.getCard();
        CommandCard targetCard = target.getCard();
        if (sourceCard != null && targetCard == null) {
            target.setCard(sourceCard);
            source.setCard(null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the opposite heading direction of the player's current heading.
     *
     * @param player the player for which to check the heading
     * @return the opposite heading direction
     */
    public Heading Checkheading(Player player) {
        Heading playerDirection = player.getHeading();
        Heading newDirect;
        if (playerDirection == Heading.WEST) {
            newDirect = Heading.EAST;
        } else if (playerDirection == Heading.EAST) {
            newDirect = Heading.WEST;
        } else if (playerDirection == Heading.NORTH) {
            newDirect = Heading.SOUTH;
        } else newDirect = Heading.NORTH;
        return newDirect;

    }

    /**
     * Executes a command option for a player and continues the game with the OptionInteractive card. It sets phase to Activation Phase.
     *
     * @param player  the player for whom to execute the command option
     * @param comman2 the command  the option to execute with userinput.
     */
    public void executeCommandOptionAndContinue(Player player, Command comman2) {
        board.setPhase(Phase.ACTIVATION);
        this.executeCommand(player, comman2);


        int step = board.getStep();
        step++;
        if (step < Player.NO_REGISTERS) {
            makeProgramFieldsVisible(step);
            board.setStep(step);
            board.setCurrentPlayer(board.getPlayer(turn));
        } else {
            startProgrammingPhase();
        }
        continuePrograms();
    }

    /**
     * Executes a gear rotation for the player if the current space contains a gear.
     *
     * @param player the player for whom to check and execute gear rotation
     */
    public void GearRotation(Player player) {
        Space space3 = player.getSpace();
        Gear gear = space3.getGear();
        if (gear != null) {
            turnRight(player);
        }
    }

    /**
     * Transports the player using a conveyor belt if present on their current space.
     * The player's heading is set to the conveyor's direction, and the player is moved forward.
     *
     * @param player the player to be transported
     */
    public void conveyerTransport(Player player) {
        Space space3 = player.getSpace();
        conveyorBelt conveyor = space3.getConveyor();
        if (conveyor != null) {
            String colour = conveyor.getColour();
            Heading heading2 = conveyor.getDirection();
            player.setHeading(heading2);
            if (colour.equals("blue")) {
                this.fastForward(player);
            } else {
                this.moveForward(player);
            }


        }
    }

    /**
     * Pushes the player forward if a push panel is present on their current space.
     * The player's heading is set to the push panel's direction before moving forward.
     *
     * @param player the player to be pushed
     */
    public void PushPanel(Player player) {
        Space space5 = player.getSpace();
        PushPanel pushPanel = space5.getPushPanel();
        if(pushPanel != null) {
            Heading heading5 = pushPanel.getDirection();
            player.setHeading(heading5);
            this.moveForward(player);
        }

    }

    /**
     * Checks if the target space is occupied by another player and their heading does not oppose the specified heading.
     * If a collision occurs, the other player is moved forward.
     *
     * @param target  the target space to check for collision
     * @param heading the heading direction to compare with other player's heading
     * @return true if a collision occurs, false otherwise
     */
    public boolean robotCollide(Space target, Heading heading) {
        if (target.getPlayer() != null && !target.getPlayer().getHeading().next().next().equals(heading)) {
            Player old = target.getPlayer();
            moveForward(old);

        } else return target.getPlayer() == null || !target.getPlayer().getHeading().next().next().equals(heading);
        return true;

    }

    /**
     * Checks if the player is on a checkpoint space.
     * If the player's token count matches the checkpoint's order number, the player's token count is incremented.
     * Otherwise, a message is set for the player.
     *
     * @param player the player to check for checkpoint tokenization
     */
    public void CheckPointTokener(Player player) {
        Space space = player.getSpace();

        CheckPoint checkPoint = space.getCheckPoint();
        if (checkPoint != null && player.getTokens() == checkPoint.orderNo) {
            player.setTokens(player.getTokens() + 1);
        } else if (checkPoint != null) {
            player.setMessage();
        }
    }

    /**
     * Checks if the current player has won the game based on the game's ID and token count.
     * If the conditions are met, the winning player's name is printed and the game is exited.
     */
    public void winGame() {
        if (board.getGameId().equals(3)) {
            if (board.getCurrentPlayer().getTokens() == 6) {
                System.out.println(board.getCurrentPlayer().getName() + "Du har vundet");
                Platform.exit();
            }
        } else if (board.gameId != (3)) {
            if (board.getCurrentPlayer().getTokens() == 3) {
                System.out.println(board.getCurrentPlayer().getName() + "Du har vundet");
                Platform.exit();
            }
        }
    }
    /**
     * Sets the board for unit testing purposes.
     *
     * @param board the board to set
     */
    public void setBoard(Board board) {
    }
}