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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import javafx.scene.Group;
/**
 * The view class that displays the game board.
 * It represents the graphical user interface for the board and handles events related to the board.
 * This class extends VBox and implements ViewObserver interface.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */


public class BoardView extends VBox implements ViewObserver {

    private final Board board;

    private final GridPane mainBoardPane;
    private final SpaceView[][] spaces;

    private final PlayersView playersView;

    private final Label statusLabel;
    private final GameController gameController;
    private final SpaceEventHandler spaceEventHandler;

    private final Text text = new Text();
    public Pane overlayPane;
    private final Timeline textRemovalTimeline;

    /**
     * Constructor for BoardView class.
     * Initializes the board view, including the main board pane, player views, status label, overlay pane, etc.
     * Attaches the board view as an observer to the board.
     *
     * @param gameController the game controller instance
     */
    public BoardView(@NotNull GameController gameController) {
        board = gameController.board;
        this.gameController = gameController;
        overlayPane = new Pane();
        mainBoardPane = new GridPane();
        playersView = new PlayersView(gameController);
        statusLabel = new Label("<no status>");

        //This is a timer for the text duration.
        Duration removalDuration = Duration.seconds(4);
        textRemovalTimeline = new Timeline(new KeyFrame(removalDuration, event -> removeText()));

        // Adds UI elements to the current view
        this.getChildren().add(mainBoardPane);
        this.getChildren().add(playersView);
        this.getChildren().add(statusLabel);
        this.getChildren().add(overlayPane);

        // Creates an array of SpaceView objects to represent the spaces on the board
        spaces = new SpaceView[board.width][board.height];

        // Creates a SpaceEventHandler for handling space events
        spaceEventHandler = new SpaceEventHandler(gameController);

        // Iterates through each space on the board
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                Space space = board.getSpace(x, y);
                // Creates a SpaceView object for the current space
                SpaceView spaceView = new SpaceView(space);
                /**
                 * This takes an image using imageview loaded from our resource folder and then detects what @OrderNo is equally set as. This is made in BoardClass.
                 * @board is used as the infrastructure to devide the points, because it calls the checkpoint object.(here we detect the checkpoint) and use the matching picture for OrderNo.
                 */
                spaces[x][y] = spaceView;
                /**
                 * This takes an image using imageview loaded from our resource folder and then detects what getDirection is equally set as from the object of the conveyer. This is made in BoardClass.
                 *  * Creates color for the spaces of each conveyer and creates picture.
                 */
                // Checks if the space has a conveyor with the color "blue"
                if (space.getConveyor()!=null && space.getConveyor().getColour()=="blue") {
                    ImageView imageView = new ImageView();
                    Image image = null;
                    // Sets the image based on the direction of the conveyor
                    if(space.getConveyor().getDirection() == Heading.WEST) {
                        image = new Image("BlueWest.png", 60, 60, false, false);
                    } else if(space.getConveyor().getDirection() == Heading.EAST ) {
                        image = new Image("BlueEast.png", 60, 60, false, false);
                    } else if ( space.getConveyor().getDirection() == Heading.SOUTH ) {
                        image = new Image("BlueSouth.png", 60, 60, false, false);
                    }else if (space.getConveyor().getDirection() == Heading.NORTH ) {
                        image = new Image("Blue.png", 60, 60, false, false);
                    }
                    // Sets the image to the ImageView and adds it to the SpaceView
                    imageView.setImage(image);
                    spaceView.getChildren().add(imageView);
                }
                // Checks if the space has a conveyor with the color "green"
           else if (space.getConveyor()!=null && space.getConveyor().getColour()=="green") {
                    ImageView imageView = new ImageView();
                    Image image =null;
                    // Sets the image based on the direction of the conveyor
                         image = new Image("GreenWest.png", 60, 60, false, false);
                  if(space.getConveyor().getDirection() == Heading.EAST ) {
                        image = new Image("GreenEastt.png", 60, 60, false, false);
                    } else if ( space.getConveyor().getDirection() == Heading.SOUTH ) {
                        image = new Image("GreenSouth.png", 60, 60, false, false);
                    }else if (space.getConveyor().getDirection() == Heading.NORTH ) {
                        image = new Image("Green.png", 60, 60, false, false);
                    }

                    // Sets the image to the ImageView and adds it to the SpaceView
                    imageView.setImage(image);
                        spaceView.getChildren().add(imageView);

                }
                // Checks if the space has a push panel
                else if (space.getPushPanel()!=null) {
                    ImageView imageView = new ImageView();
                    Image image =null;
                    // Sets the image based on the direction of the push panel
                    if(space.getPushPanel().getDirection() == Heading.WEST ){
                        image = new Image("PushPanelWest.png", 60, 60, false, false);
                    } else if(space.getPushPanel().getDirection() == Heading.EAST ) {
                        image = new Image("PushPanelEast.png", 60, 60, false, false);
                    } else if ( space.getPushPanel().getDirection() == Heading.SOUTH ) {
                        image = new Image("PushPanelSouth.png", 60, 60, false, false);
                    }else if (space.getPushPanel().getDirection() == Heading.NORTH ) {
                        image = new Image("PushPanelNorth.png", 60, 60, false, false);
                    }
                    imageView.setImage(image);
                    spaceView.getChildren().add(imageView);
                }


                // Creates an ImageView for the checkpoints
        else    if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo() ==0){

                    ImageView imageView = new ImageView();
                    Image image = new Image("cp1.png",60,60,false,false);

                    imageView.setImage(image);
                    spaceView.getChildren().add(imageView);
                }
         else   if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo() ==1){
                    ImageView imageView = new ImageView();
                    Image image = new Image("cp2.png",60,60,false,false);
                    imageView.setImage(image);
                    spaceView.getChildren().add(imageView);
                }
        else    if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo() ==2){

                    ImageView imageView = new ImageView();
                    Image image = new Image("cp3.png",60,60,false,false);
                    imageView.setImage(image);
                    // Adds the ImageView to the SpaceView
                    spaceView.getChildren().add(imageView);
                }
                // Adds the SpaceView to the mainBoardPane at the current position
                mainBoardPane.add(spaceView, x, y);
                // Sets a mouse click event handler for the SpaceView
                spaceView.setOnMouseClicked(spaceEventHandler);
            }
        }
        // Attaches the current view to the board as a subject
        board.attach(this);
        // Updates the current view with the board state
        update(board);
    }
    // Removes the 'text' from the overlayPane
    private void removeText() {
        overlayPane.getChildren().remove(text);

    }
    // Updates the view based on the subject that triggered the update
    @Override
    public void updateView(Subject subject) {
        // Checks if the current player has a message
        if (subject == board) {

            Phase phase = board.getPhase();
            statusLabel.setText(board.getStatusMessage());
            if(gameController.board.getCurrentPlayer().getMessage()) {
                Platform.runLater(() -> {
                    // Sets the text properties for the message
                    text.setText("You need a previous Checkpoint's flag first!");
                    text.setStyle("-fx-font-size: 25; -fx-font-weight: bold;");
                    text.setFill(Color.ORANGE);
                    // Adds the 'text' to the 'overlayPane'
                    overlayPane.getChildren().add(text);
                });
                overlayPane.setManaged(false);

                //cordination layout x and y for text.
                overlayPane.setLayoutX(100);
                overlayPane.setLayoutY(220);

                // Sets the cycle count for the text removal timeline
                textRemovalTimeline.setCycleCount(1);
                gameController.board.getCurrentPlayer().setMessagefalse();
            }

        }
        // Starts the text removal timeline from the beginning
        textRemovalTimeline.playFromStart();
    }

    // Event handler for space mouse click events
    private class SpaceEventHandler implements EventHandler<MouseEvent> {

        final public GameController gameController;

        public SpaceEventHandler(@NotNull GameController gameController) {
            this.gameController = gameController;
        }

        @Override
        public void handle(MouseEvent event) {
            Object source = event.getSource();
            // Checks if the board is the same as the game controller's board
            if (source instanceof SpaceView spaceView) {
                Space space = spaceView.space;
                Board board = space.board;



                if (board == gameController.board) {
                    // Moves the current player to the clicked space
                    gameController.moveCurrentPlayerToSpace(space);
                    event.consume();
                }
            }
        }

    }


}
