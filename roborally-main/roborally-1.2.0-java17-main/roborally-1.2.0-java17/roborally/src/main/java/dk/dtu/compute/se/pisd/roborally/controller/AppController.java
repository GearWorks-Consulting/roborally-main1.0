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

//For End credits scrolling in Information tab in MenuBar
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

//Observer pattern
import dk.dtu.compute.se.pisd.designpatterns.observer.Observer;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.RoboRally;

import dk.dtu.compute.se.pisd.roborally.client.ProductClient;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import dk.dtu.compute.se.pisd.roborally.JSON.LoadBoard;
/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
public class AppController implements Observer {

    final private List<Integer> PLAYER_NUMBER_OPTIONS = Arrays.asList(2, 3, 4, 5, 6);
    final private List<String> BOARD_NUMBER_OPTION = Arrays.asList("Map 1 - Small","Map 2 - Small","Map 3 - Large");
    final private List<String> PLAYER_COLORS = Arrays.asList("red", "green", "blue", "orange", "grey", "magenta");

    final private RoboRally roboRally;
    Board board;


    private GameController gameController;
    private int playerCount;
    public int minimumplayer;


    public AppController(@NotNull RoboRally roboRally) {
        this.roboRally = roboRally;
    }

    private String enteredText = "";
    private BoardTemplate hostBoardTemplate;

    public void HostGame() {
        Stage primaryStage = new Stage();
        TextField nameTextFieldGet = new TextField();
        ProductClient.setPlayerTurn("0");
        // Create a button to open a new screen
        Button openButton = new Button("Open");
        openButton.setOnAction(event -> {
            playerCount = 1;
            ProductClient.updatePlayerJoinedCounter(""+ playerCount);
                startGame();
                gameController.setPlayerNumber(0);
            gameController.updateServer();
            gameController.setOnline(1);

                primaryStage.close();
        });
        // Create a layout and add the text field and button
        VBox root = new VBox(10);
        root.getChildren().addAll(nameTextFieldGet, openButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 250, 100);
        primaryStage.setTitle("Customize Password");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void JoinGame() {
        Stage primaryStage = new Stage();
        TextField nameTextFieldGet = new TextField();

        // Create a button to open a new screen
        Button openButton = new Button("Open");
        openButton.setOnAction(event -> {
            String playerName = nameTextFieldGet.getText();
            int playerCounter = Integer.parseInt(ProductClient.getPlayerCounter());
            BoardTemplate template = LoadBoard.boardFromServer("serverGame");
            board=LoadBoard.upDateBoard(template, board);
            handleJoinGame(playerName,board,playerCounter);
            primaryStage.close();

        });

        // Create a layout and add the text field and button
        VBox root = new VBox(10);
        root.getChildren().addAll(nameTextFieldGet, openButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 400, 100);
        primaryStage.setTitle("Welcome to roborally, want to join?");
        primaryStage.setScene(scene);
        primaryStage.show();

    ;
        //gameController.setTurn(1);
       ProductClient.setCompleteMove("false");
      // chooseMap("Map 1 - Small");
      // addPlayersToBoard(board,2);
    }
    public void handleJoinGame(String playerName,Board board,int playerCounter) {
        boolean isNameCorrect = checkifNameCorrect(playerName);
        if (isNameCorrect && playerCounter < board.getPlayersNumber()) {
            gameController = new GameController(board);
            gameController.setPlayerNumber(playerCounter);
            gameController.setOnline(1);
            playerCounter++;
            ProductClient.updatePlayerJoinedCounter(String.valueOf(playerCounter));
            gameController.startProgrammingPhase();
            roboRally.createBoardView(gameController);
        } else {
            showAlertIfLobbyFull();
        }
    }

    private boolean checkifNameCorrect(String playerName) {
        boolean isNameCorrect = playerName.equals(enteredText);
        if (!isNameCorrect) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Name");
            alert.setContentText("The entered name does not match the required name!");
            alert.showAndWait();
        }
        return isNameCorrect;
    }

    private void showAlertIfLobbyFull() {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Lobby Full");
            alert.setHeaderText(null);
            alert.setContentText("The lobby is now full.");
            alert.showAndWait();
    }


    private void startGame() {
        ChoiceDialog<Integer> playerdialog = new ChoiceDialog<>(PLAYER_NUMBER_OPTIONS.get(0), PLAYER_NUMBER_OPTIONS);
        playerdialog.setTitle("Player number");
        playerdialog.setHeaderText("Select number of players");
        Optional<Integer> result = playerdialog.showAndWait();

        ChoiceDialog<String> boardDialog = new ChoiceDialog<>(BOARD_NUMBER_OPTION.get(0), BOARD_NUMBER_OPTION);
        boardDialog.setTitle("Board number");
        boardDialog.setHeaderText("Select Board");
        Optional<String> result2 = boardDialog.showAndWait();
        String selectedBoard = result2.orElse(null); // Use orElse to handle canceled dialog

        if (selectedBoard != null) { // Check if board selection is present
            if (gameController != null) {
                // The UI should not allow this, but in case this happens anyway.
                // give the user the option to save the game or abort this operation!
                if (!stopGame()) {
                    return;
                }
            }

            // XXX the board should eventually be created programmatically or loaded from a file
            //     here we just create an empty board with the required number of players.
            Board board = chooseMap(selectedBoard);

            gameController = new GameController(board);

            int no = result.get();
            minimumplayer = no;
            addPlayersToBoard(board, no);
            gameController.startProgrammingPhase();
            roboRally.createBoardView(gameController);



            //  BoardTemplate boardTemplate = LoadBoard.NormalBoardToTemplate(board);
            // ProductClient.saveBoard(boardTemplate,"test5");
        }
    }

    public void localGame() {
            startGame();

            gameController.startProgrammingPhase();
            roboRally.createBoardView(gameController);
        }

    public void addPlayersToBoard(Board board,int no){
        for (int i = 0; i < no; i++) {
            Player player = new Player(board, PLAYER_COLORS.get(i), "Player " + (i + 1));
            board.addPlayer(player);

            player.setSpace(board.getSpace(i, 0));
            player.getSpace().setPlayer(player);
        }
        board.setCurrentPlayer(board.getPlayer(0));


    }
    public Board chooseMap(String selectedBoard){

        switch (selectedBoard) {
            case "Map 1 - Small":
                board = LoadBoard.loadMap("Board 1");

                 /*   BoardTemplate TEM=LoadBoard.boardFromServer("test5");
                    int no = result.get();
            for (int i = 0; i < no; i++) {
                Player player = new Player(board, PLAYER_COLORS.get(i), "Player " + (i + 1));
                board.addPlayer(player);
                player.setSpace(board.getSpace(i, 0));
                player.getSpace().setPlayer(player);
                board.setCurrentPlayer(board.getPlayer(0));


            }

                    LoadBoard.upDateBoard(TEM,board);

                  */
                board.setGameId(1);
                break;

            case "Map 2 - Small":
                // Logic for Map 2
                board = LoadBoard.loadMap("Board 2");
                board.setGameId(2);


                break;
            case "Map 3 - Large":
                // Logic for Map 3
                board = LoadBoard.loadMap("Board 3");
                board.setGameId(3);
                //board= new Board(12,10);
                break;
        }
        return board;
    }

    public void saveGame() {
    BoardTemplate boardTemplate= LoadBoard.NormalBoardToTemplate(gameController.board);
    LoadBoard.saveGameToFile(boardTemplate,"Last Game");
    }

    public void loadGame() {
        try {
            BoardTemplate template= LoadBoard.boardFromFile("last Game");
          board=LoadBoard.upDateBoard(template,board);

            if (board != null) {
                gameController = new GameController(board);
              //  gameController.startProgrammingPhase();
                roboRally.createBoardView(gameController);
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No Saved Game Found");
                alert.setHeaderText(null);
                alert.setContentText("There is no saved game available. Better start playing!.");

                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Loading Game");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while loading the game: " + e.getMessage());

            alert.showAndWait();
        }
    }

    public void showInformation() {
        List<String> rules = Arrays.asList("How To Play", "RoboRally - Awards Won", "Credits", "...");

        ChoiceDialog<String> ruleDialog = new ChoiceDialog<>(null, rules);
        ruleDialog.setTitle("Information Details");
        ruleDialog.setHeaderText("Select an info");
        ruleDialog.setContentText("Select an info to view:");

        Optional<String> selectedRule = ruleDialog.showAndWait();
        selectedRule.ifPresent(this::displayRule);
    }

    private void displayRule(String rule) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Details");
        alert.setHeaderText("Information Details");

        if (rule.equals("How To Play")) {
            alert.setContentText("Here is a short summary of the setup of the actual game: \n" +
                    "-\tThe players choose a board to compete in.\n" +
                    "-\tEach players choose a robot and its corresponding colour figure and programming deck. Furthermore, each player gets a player mat.\n" +
                    "-\tThere are six special programming cards not associated with any specific robots meaning it’s for all.\n" +
                    "-\tCheckpoint tokens for the players. The checkpoint tokens are acquired for each checkpoint the tokens are displayed in the bottom left corner.\n" +
                    "-\tGoal of the game is for an individual player to get all checkpoint tokens.\n" +
                    "Each game is comprised of multiple rounds with each rounds having two phases.\n" +
                    "1.\tThe programming phase.\n" +
                    "2.\tThe activation phase.\n" +
                    "Each phase has their own rules and progressions. As it is called a race, the game ends when one of the players reaches the final checkpoint. \n");
        } else if (rule.equals("RoboRally - Awards Won")){
            alert.setContentText("-\tAt the 1995 Origins Awards, RoboRally won awards in two categories: \n" +
                    "\"Best Fantasy or Science Fiction Boardgame of 1994\"\n" +
                    "\"Best Graphic Presentation of a Boardgame of 1994\"\n" +
                    "-\tAt the 1996 Origins Awards, the Armed and Dangerous expansion won \"Best Graphic Presentation of a Boardgame of 1995”\n" +
                    "-\tAt the 1997 Origins Awards, RoboRally Grand Prix won \"Best Fantasy or Science Fiction Boardgame of 1996”\n");
        } else if (rule.equals("Credits")){
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefSize(400, 200);

            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setText("Thanks to our professor:\n" +
                    "- Professor Siniša Neškovic \n\n" +
                    "- Anne Ringsted\n" +
                    "- Ekkart Kindler \n" +
                    "- Hubert Baumeister\n" +
                    "- Censor Standing Infront of me \n" +
                    "\n"+
                    "Teaching Assistents:\n" +
                    "- Christian Francesco Notarmaso Pone \n" +
                    "- Camila Santos Celes \n" +
                    "- Ioannis Orestis Zamanis Dimitroulas \n" +
                    "- Christian Juul Lund Andersen \n" +
                    "-  Frederik Emil Schibelfeldt \n");

            scrollPane.setContent(textArea);
            alert.getDialogPane().setContent(scrollPane);
        }


        else {
            alert.setContentText(rule);
        }

        alert.showAndWait();
    }





    /**
     * Stop playing the current game, giving the user the option to save
     * the game or to cancel stopping the game. The method returns true
     * if the game was successfully stopped (with or without saving the
     * game); returns false, if the current game was not stopped. In case
     * there is no current game, false is returned.
     *
     * @return true if the current game was stopped, false otherwise
     */
    public boolean stopGame() {
        if (gameController != null) {

            // here we save the game (without asking the user).
            saveGame();

            gameController = null;
            roboRally.createBoardView(null);
            return true;
        }
        return false;
    }

    public void exit() {
        if (gameController != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit RoboRally?");
            alert.setContentText("Are you sure you want to exit RoboRally?");
            Optional<ButtonType> result = alert.showAndWait();

            if (!result.isPresent() || result.get() != ButtonType.OK) {
                return; // return without exiting the application
            }
        }

        // If the user did not cancel, the RoboRally application will exit
        // after the option to save the game
        if (gameController == null || stopGame()) {
            Platform.exit();
        }
    }
    public boolean isGameRunning() {
        return gameController != null;
    }
    @Override
    public void update(Subject subject) {
        // XXX do nothing for now


    }
}
