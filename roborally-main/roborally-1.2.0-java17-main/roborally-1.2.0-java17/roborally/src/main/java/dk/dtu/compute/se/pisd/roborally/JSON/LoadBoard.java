package dk.dtu.compute.se.pisd.roborally.JSON;
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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.dtu.compute.se.pisd.roborally.client.ProductClient;
import dk.dtu.compute.se.pisd.roborally.model.*;


import java.io.*;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.List;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class LoadBoard {

    private static final String BOARDSFOLDER = "boards";
    private static final String DEFAULTBOARD = "defaultboard";
    private static final String JSON_EXT = "json";


    public static BoardTemplate boardFromFile(String boardname) {
        if (boardname == null) {
            boardname = DEFAULTBOARD;
        }
        JsonReader reader;
        try {
            File file = new File(boardname + "." + JSON_EXT);
            FileReader fileReader = new FileReader(file);


            // In simple cases, we can create a Gson object with new Gson():
            GsonBuilder simpleBuilder = new GsonBuilder().
                    registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>());
            Gson gson = simpleBuilder.create();


            // FileReader fileReader = null;
            reader = new JsonReader(fileReader);

            // fileReader = new FileReader(filename);
            BoardTemplate template;


            template = gson.fromJson(reader, BoardTemplate.class);





            reader.close();
            return template;
        } catch (IOException e1) {



        }
        return null;

    }
    public static BoardTemplate boardFromServer(String boardname) {
        return ProductClient.loadBoard(boardname);
    }

    public static Board upDateBoard(BoardTemplate template, Board result){
        if (result != null) {

            for (int i = 0; i < result.width; i++) {
                for (int j = 0; j < result.height; j++) {
                    Space space = result.getSpace(i, j);
                    SpaceTemplate temSpace = template.spaces[i][j];
                    if (temSpace.Conveyor != null) {
                        space.setConveyor(temSpace.Conveyor);
                    }
                    if (temSpace.checkPoint != null) {
                        space.setCheckPoint(temSpace.checkPoint);
                    }
                    if (temSpace.wallHeading != null) {
                        space.addWall(temSpace.wallHeading);
                        space.setWall();
                        space.setWallFacing(temSpace.wallHeading);
                    }
                    if (temSpace.gearRotation != null) {
                        space.setGear(temSpace.gearRotation);
                    }


                }
            }


            if (template.gameId != null)
                result.setGameId(template.gameId);
            for (int i = 0; i < result.getPlayersNumber(); i++) {

                result.getPlayer(i).setTokens(template.getPlayer(i).tokens);
                result.getPlayer(i).setSpace(result.getSpace(template.getPlayer(i).space.x, template.getPlayer(i).space.y));
                result.spaces[template.getPlayer(i).space.x][template.getPlayer(i).space.y].setPlayer(result.getPlayer(i));
                result.getPlayer(i).setHeading(template.getPlayer(i).heading);
                result.getPlayer(i).getSpace().setPlayer(result.getPlayer(i));
                result.getPlayer(i).setName(template.getPlayer(i).name);


                for (int k = 0; k < result.getPlayer(i).getCards().length; k++) {
                    result.getPlayer(i).getCardField(k).setCard(template.getPlayer(i).cards[k].card);
                    result.getPlayer(i).getCardField(k).setVisible(template.getPlayer(i).cards[k].visible);

                }
                for (int k = 0; k < result.getPlayer(i).getProgram().length; k++) {
                    result.getPlayer(i).getProgramField(k).setCard(template.getPlayer(i).program[k].card);
                    result.getPlayer(i).getProgramField(k).setVisible(template.getPlayer(i).program[k].visible);
                }

                if (template.current != null && result.current != null && template.current.name.equals(result.getCurrentPlayer().getName())) {
                    result.setCurrentPlayer(result.getPlayer(i));
                }


            }

            if (template.gameId != null)
                result.setGameId(template.gameId);

            result.setPhase(template.phase);
            result.setStep(template.step);
            result.setStepMode(template.stepMode);

return  result;
        }
        else
        {
            result= new Board(template.width, template.height);
            for (int i = 0; i < result.width; i++) {
                for (int j = 0; j < result.height; j++) {
                    Space space = result.getSpace(i, j);
                    SpaceTemplate temSpace = template.spaces[i][j];
                    if (temSpace.Conveyor != null) {
                        space.setConveyor(temSpace.Conveyor);
                    }
                    if (temSpace.checkPoint != null) {
                        space.setCheckPoint(temSpace.checkPoint);
                    }
                    if (temSpace.wallHeading != null) {
                        space.addWall(temSpace.wallHeading);
                        space.setWall();
                        space.setWallFacing(temSpace.wallHeading);
                    }
                    if (temSpace.gearRotation != null) {
                        space.setGear(temSpace.gearRotation);
                    }


                }
            }


            if (template.gameId != null)
                result.setGameId(template.gameId);
            for (int i = 0; i < template.getPlayersNumber(); i++) {
                 List<String> PLAYER_COLORS = Arrays.asList("red", "green", "blue", "orange", "grey", "magenta");
                result.addPlayer(new Player(result,PLAYER_COLORS.get(i) ,"Player " + (i + 1)));

                result.getPlayer(i).setTokens(template.getPlayer(i).tokens);
                result.getPlayer(i).setSpace(result.getSpace(template.getPlayer(i).space.x, template.getPlayer(i).space.y));
                result.spaces[template.getPlayer(i).space.x][template.getPlayer(i).space.y].setPlayer(result.getPlayer(i));
                result.getPlayer(i).setHeading(template.getPlayer(i).heading);
                result.getPlayer(i).getSpace().setPlayer(result.getPlayer(i));
                result.getPlayer(i).setName(template.getPlayer(i).name);


                for (int k = 0; k < result.getPlayer(i).getCards().length; k++) {
                    result.getPlayer(i).getCardField(k).setCard(template.getPlayer(i).cards[k].card);
                    result.getPlayer(i).getCardField(k).setVisible(template.getPlayer(i).cards[k].visible);

                }
                for (int k = 0; k < result.getPlayer(i).getProgram().length; k++) {
                    result.getPlayer(i).getProgramField(k).setCard(template.getPlayer(i).program[k].card);
                    result.getPlayer(i).getProgramField(k).setVisible(template.getPlayer(i).program[k].visible);
                }

                if (template.current != null && result.current != null && template.current.name.equals(result.getCurrentPlayer().getName())) {
                    result.setCurrentPlayer(result.getPlayer(i));
                }


            }

            if (template.gameId != null)
                result.setGameId(template.gameId);

            result.setPhase(template.phase);
            result.setStep(template.step);
            result.setStepMode(template.stepMode);

return result;
        }

    }

    public static Board loadMap(String boardname) {
        if (boardname == null) {
            boardname = DEFAULTBOARD;
        }
        JsonReader reader;
        try {
            File file = new File(boardname + "." + JSON_EXT);
            FileReader fileReader = new FileReader(file);


            // In simple cases, we can create a Gson object with new Gson():
            GsonBuilder simpleBuilder = new GsonBuilder().
                    registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>());
            Gson gson = simpleBuilder.create();

            Board result;
            // FileReader fileReader = null;
            reader = new JsonReader(fileReader);

            // fileReader = new FileReader(filename);
            BoardTemplate template = gson.fromJson(reader, BoardTemplate.class);

            result = new Board(template.width, template.height);
            for (int i = 0; i < template.width; i++) {
                for (int j = 0; j < template.height; j++) {
                    Space space = result.getSpace(i, j);
                    SpaceTemplate temSpace = template.spaces[i][j];
                    if (temSpace.Conveyor != null) {
                        space.setConveyor(temSpace.Conveyor);
                    }
                    if (temSpace.checkPoint != null) {
                        space.setCheckPoint(temSpace.checkPoint);
                    }
                    if (temSpace.wallHeading != null) {
                        space.addWall(temSpace.wallHeading);
                        space.setWall();
                        space.setWallFacing(temSpace.wallHeading);
                    }
                    if (temSpace.gearRotation != null) {
                        space.setGear(temSpace.gearRotation);
                    }


                }
            }
            reader.close();
            return result;
        } catch (IOException e1) {

        }
        return null;

    }
    public static BoardTemplate NormalBoardToTemplate(Board board){
        BoardTemplate template = new BoardTemplate(board.width, board.height);
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                Space space = board.getSpace(i, j);
                SpaceTemplate tempSpace = template.spaces[i][j];
                if (space.getConveyor() != null) {
                    tempSpace.Conveyor = space.getConveyor();
                }
                if (space.getCheckPoint() != null) {
                    tempSpace.checkPoint = space.getCheckPoint();
                }
                if (space.getWall()) {
                    tempSpace.wallHeading = space.getWallFacing();
                }
                if (space.getGear() != null) {
                    tempSpace.gearRotation = space.getGear();
                }
            }
        }
        for (int i = 0; i < board.getPlayersNumber(); i++) {
            PlayerTemplate playerAmount = new PlayerTemplate();
            playerAmount.name = board.getPlayer(i).getName();
            playerAmount.color = board.getPlayer(i).getColor();
            playerAmount.tokens = board.getPlayer(i).getTokens();
            playerAmount.heading = board.getPlayer(i).getHeading();
            template.players.add(playerAmount);
            playerAmount.space = template.spaces[board.getPlayer(i).getSpace().x][board.getPlayer(i).getSpace().y];

            for (int k = 0; k < board.getPlayer(i).getCards().length; k++) {
                playerAmount.cards[k].card = board.getPlayer(i).getCardField(k).getCard();
                playerAmount.cards[k].visible = board.getPlayer(i).getCardField(k).isVisible();
            }
            for (int k = 0; k < board.getPlayer(i).getProgram().length; k++) {
                playerAmount.program[k].card = board.getPlayer(i).getProgramField(k).getCard();
                playerAmount.program[k].visible = board.getPlayer(i).getProgramField(k).isVisible();
            }

            if (board.getCurrentPlayer().getName().equals(board.getPlayer(i).getName())) {
                template.current = playerAmount;
            }

        }


        if (board.getGameId() != null)
            template.gameId = board.getGameId();


        template.phase = board.getPhase();
        template.step = board.getStep();
        template.stepMode = board.stepMode;
        return template;

    }

    public static void saveGameToFile(BoardTemplate template, String name) {

        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        // TODO: this is not very defensive, and will result in a NullPointerException
        //       when the folder "resources" does not exist! But, it does not need
        //       the file "simpleCards.json" to exist!
        String filename =
                name + "." + JSON_EXT;
        GsonBuilder simpleBuilder = new GsonBuilder().
                registerTypeAdapter(FieldAction.class, new Adapter<FieldAction>()).
                setPrettyPrinting();

        Gson gson = simpleBuilder.create();

        FileWriter fileWriter = null;
        JsonWriter writer = null;
        try {
            fileWriter = new FileWriter(filename);
            writer = gson.newJsonWriter(fileWriter);
            gson.toJson(template, template.getClass(), writer);
            writer.close();
        } catch (IOException e1) {
            if (writer != null) {
                try {
                    writer.close();
                    fileWriter = null;
                } catch (IOException e2) {
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e2) {
                }
            }
        }


    }
    public static void UpdateMoveToServer (Board board,int currentPlayer){
        BoardTemplate templateFromServer = boardFromServer("serverGame");
            PlayerTemplate playerAmount = templateFromServer.getPlayer(currentPlayer);
            playerAmount.tokens = board.getPlayer(currentPlayer).getTokens();
            playerAmount.heading = board.getPlayer(currentPlayer).getHeading();
            playerAmount.space.x = templateFromServer.getPlayer(currentPlayer).space.x;
            playerAmount.space.y=templateFromServer.getPlayer(currentPlayer).space.y;


        for (int k = 0; k < board.getPlayer(currentPlayer).getCards().length; k++) {
                playerAmount.cards[k].card = board.getPlayer(currentPlayer).getCardField(k).getCard();
                playerAmount.cards[k].visible = board.getPlayer(currentPlayer).getCardField(k).isVisible();
            }
            for (int k = 0; k < board.getPlayer(currentPlayer).getProgram().length; k++) {
                playerAmount.program[k].card = board.getPlayer(currentPlayer).getProgramField(k).getCard();
                playerAmount.program[k].visible = board.getPlayer(currentPlayer).getProgramField(k).isVisible();
            }


        templateFromServer.current = templateFromServer.getPlayer(board.getPlayerNumber(board.getCurrentPlayer()));

        templateFromServer.phase = board.getPhase();
        templateFromServer.step = board.getStep();
        templateFromServer.stepMode = board.stepMode;
        boardToServer(templateFromServer,"serverGame");
        upDateBoard(templateFromServer,board);


    }

    public static void boardToServer(BoardTemplate template,String fileName) {
        ProductClient.saveBoard(template,fileName);
    }


}
