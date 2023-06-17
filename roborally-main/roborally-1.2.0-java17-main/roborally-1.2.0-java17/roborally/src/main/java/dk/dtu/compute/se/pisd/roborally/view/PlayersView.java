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
import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import javafx.scene.control.TabPane;

/**
 * The view class that represents the players in the game.
 * It extends TabPane and implements the ViewObserver interface.
 * It displays individual PlayerViews for each player in the game.
 * It observes changes in the game board and updates accordingly.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */
public class PlayersView extends TabPane implements ViewObserver {

    private final Board board;

    private final PlayerView[] playerViews;
    /**
     * Constructs a PlayersView object with the given game controller.
     *
     * @param gameController the game controller instance.
     */
    public PlayersView(GameController gameController) {
        board = gameController.board;
        // Set tab closing policy to unavailable
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // Create PlayerView instances for each player and add them as tabs
        playerViews = new PlayerView[board.getPlayersNumber()];
        for (int i = 0; i < board.getPlayersNumber();  i++) {
            playerViews[i] = new PlayerView(gameController, board.getPlayer(i));
            this.getTabs().add(playerViews[i]);
        }
        // Attach this PlayersView as an observer to the board
        board.attach(this);

        // Update the view with the initial state of the board
        update(board);

    }

    @Override
    public void updateView(Subject subject) {
        if (subject == board) {
            // Select the tab corresponding to the current player
            Player current = board.getCurrentPlayer();
            this.getSelectionModel().select(board.getPlayerNumber(current));
        }
    }

}
