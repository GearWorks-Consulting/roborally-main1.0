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
package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */

/**
 * The PlayerView class represents the graphical view of a player's status and actions in the game.
 * It extends the JavaFX Tab class and implements the ViewObserver interface.
 * It observes changes in the associated Player object and updates its view accordingly.
 */
public class PlayerView extends Tab implements ViewObserver {

    //Player attributes
    private final Player player;
    // UI elements
    private final VBox top;
    private final Label programLabel;
    private final GridPane programPane;
    private final Label cardsLabel;
    private final GridPane cardsPane;
    private final CardFieldView[] programCardViews;
    private final CardFieldView[] cardViews;
    private final VBox buttonPanel;
    private final Button finishButton;
    private final Button executeButton;
    private final Button stepButton;
    private final VBox playerInteractionPanel;
    private final GameController gameController;
    /**

 *  Constructs a PlayerView constructor for the class imported specified GameController and Player instances.

      * Initializes all of the required fields and creates UI objects via programPane & Labes. Furthermore, loops cardFieldView.
     * Also creates buttonFunctionality with the methods in GameController.

    *  @param gameController the GameController instance that controls the game

     * @param player the Player instance associated with this PlayerView instance
     */

    public PlayerView(@NotNull GameController gameController, @NotNull Player player) {
        super(player.getName());
        this.setStyle("-fx-text-base-color: " + player.getColor() + ";");

        top = new VBox();
        this.setContent(top);

        this.gameController = gameController;
        this.player = player;

        programLabel = new Label("Program");

        programPane = new GridPane();
        programPane.setVgap(2.0);
        programPane.setHgap(2.0);
        programCardViews = new CardFieldView[Player.NO_REGISTERS];
        for (int i = 0; i < Player.NO_REGISTERS; i++) {
            CommandCardField cardField = player.getProgramField(i);
            if (cardField != null) {
                programCardViews[i] = new CardFieldView(gameController, cardField);
                programPane.add(programCardViews[i], i, 0);
            }
        }



        finishButton = new Button("Finish Programming");
        finishButton.setOnAction( e -> gameController.finishProgrammingPhase());

        executeButton = new Button("Execute Program");
        executeButton.setOnAction( e-> gameController.executePrograms());

        stepButton = new Button("Execute Current Register");
        stepButton.setOnAction( e-> gameController.executeStep());

        buttonPanel = new VBox(finishButton, executeButton, stepButton);
        buttonPanel.setAlignment(Pos.CENTER_LEFT);
        buttonPanel.setSpacing(3.0);

        playerInteractionPanel = new VBox();
        playerInteractionPanel.setAlignment(Pos.CENTER_LEFT);
        playerInteractionPanel.setSpacing(3.0);

        cardsLabel = new Label("Command Cards");
        cardsPane = new GridPane();
        cardsPane.setVgap(2.0);
        cardsPane.setHgap(2.0);
        cardViews = new CardFieldView[Player.NO_CARDS];
        for (int i = 0; i < Player.NO_CARDS; i++) {
            CommandCardField cardField = player.getCardField(i);
            if (cardField != null) {
                cardViews[i] = new CardFieldView(gameController, cardField);
                cardsPane.add(cardViews[i], i, 0);
            }
        }
        // Add UI elements to the layout
        top.getChildren().add(programLabel);
        top.getChildren().add(programPane);
        top.getChildren().add(cardsLabel);
        top.getChildren().add(cardsPane);

        // Attach this view as an observer to the player's board
        if (player.board != null) {
            player.board.attach(this);
            update(player.board);
        }
    }

    /**
     * Updates the view for the given subject.
     * This method updates the view of the program cards for the player's board
     * and retrieves the current player for the update.
     *
     * */
    @Override
    public void updateView(Subject subject) {
        if (subject == player.board) {
            // Update the program card views based on the board's phase and current step
            for (int i = 0; i < Player.NO_REGISTERS; i++) {
                CardFieldView cardFieldView = programCardViews[i];
                if (cardFieldView != null) {
                    if (player.board.getPhase() == Phase.PROGRAMMING ) {
                        cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                    } else {
                        if (i < player.board.getStep()) {
                            cardFieldView.setBackground(CardFieldView.BG_DONE);
                        } else if (i == player.board.getStep()) {
                            if (player.board.getCurrentPlayer() == player) {
                                cardFieldView.setBackground(CardFieldView.BG_ACTIVE);
                            } else if (player.board.getPlayerNumber(player.board.getCurrentPlayer()) > player.board.getPlayerNumber(player)) {
                                cardFieldView.setBackground(CardFieldView.BG_DONE);
                            } else {
                                cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                            }
                        } else {
                            cardFieldView.setBackground(CardFieldView.BG_DEFAULT);
                        }
                    }
                }
            }
            // Update the button panel and button states based on the board's phase
            if (player.board.getPhase() != Phase.PLAYER_INTERACTION) {
                if (!programPane.getChildren().contains(buttonPanel)) {
                    programPane.getChildren().remove(playerInteractionPanel);
                    programPane.add(buttonPanel, Player.NO_REGISTERS, 0);
                }
                switch (player.board.getPhase()) {
                    case INITIALISATION:
                        finishButton.setDisable(true);

                        executeButton.setDisable(false);
                        stepButton.setDisable(true);
                        break;

                    case PROGRAMMING:
                        finishButton.setDisable(false);
                        executeButton.setDisable(true);
                        stepButton.setDisable(true);
                        break;

                    case ACTIVATION:
                        finishButton.setDisable(true);
                        executeButton.setDisable(false);
                        stepButton.setDisable(false);
                        break;

                    default:
                        finishButton.setDisable(true);
                        executeButton.setDisable(true);
                        stepButton.setDisable(true);
                }

/**
 This method updates the program view with the current state of the game board.
 If the current phase is not PLAYER_INTERACTION, it removes the button panel and adds the player interaction panel for user ability,
 then clears its view and adds buttons for the interactive command card, if there is one.
 @param subject the subject being observed
 */
            } else {
                if (!programPane.getChildren().contains(playerInteractionPanel)) {
                    programPane.getChildren().remove(buttonPanel);
                    programPane.add(playerInteractionPanel, Player.NO_REGISTERS, 0);
                }
                playerInteractionPanel.getChildren().clear();

                if (player.board.getCurrentPlayer() == player) {
                    // TODO Assignment P3: these buttons should be shown only when there is
                    //      an interactive command card, and the buttons should represent
                    //      the player's choices of the interactive command card. The
                    //      following is just a mockup showing two options


                    int step = player.board.getStep();
                    CommandCard card = player.getProgramField(step).getCard();
                    List list = card.command.getOptions();

                    // Iterate over the options and create a button for each option
                    for (int i =0; i< list.size();i++){
                        // Set the action for the button to execute the selected option and continue
                        Button optionButton = new Button(list.get(i).toString());
                        Command co = (Command) list.get(i);
                        optionButton.setOnAction( e -> gameController.executeCommandOptionAndContinue(player,co));
                        optionButton.setDisable(false);
                        playerInteractionPanel.getChildren().add(optionButton);

                    }
                    }
                }
        }
        }
    }

