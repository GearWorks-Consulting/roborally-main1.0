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
import dk.dtu.compute.se.pisd.roborally.model.*;


import java.io.*;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class LoadBoard {




    private static final String BOARDSFOLDER = "boards";
    private static final String DEFAULTBOARD = "defaultboard";
    private static final String JSON_EXT = "json";

    public static Board loadBoard(String boardname) {
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

            result.setGameId(template.gameId);
            for(int i=0;i<template.getPlayersNumber();i++) {
                Player playerAmount = new Player(result,template.getPlayer(i).color,template.getPlayer(i).name);
                playerAmount.setTokens(template.getPlayer(i).tokens);
                playerAmount.setSpace(template.getPlayer(i).space);
                playerAmount.setHeading(template.getPlayer(i).heading);
                playerAmount.getSpace().setPlayer(playerAmount);
                if(template.current.name.equals(template.getPlayer(i).name)){
                    result.setCurrentPlayer(playerAmount);
                }
            }

            result.setCurrentPlayer((Player) template.players);
            result.setPhase(template.phase);
            result.setStep(template.step);
            result.setStepMode(template.stepMode);
            result.setGameId(template.gameId);



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
                        space.setGear(temSpace.checkPoint);
                    }


                }
            }
            reader.close();
            return result;
        } catch (IOException e1) {

        }
        return null;

    }


    public static void saveBoard(Board board, String name) {
        BoardTemplate template = new BoardTemplate(board.width, board.height);
        for(int i=0;i<board.getPlayersNumber();i++) {
            PlayerTemplate playerAmount = new PlayerTemplate();
            playerAmount.name=board.getPlayer(i).getName();
            playerAmount.color=board.getPlayer(i).getColor();
            playerAmount.tokens=board.getPlayer(i).getTokens();
            playerAmount.heading=board.getPlayer(i).getHeading();
            template.players.add(playerAmount);
            if(board.getCurrentPlayer().getName().equals(board.getPlayer(i).getName())){
                template.current =playerAmount;
            }

        }




        template.gameId=board.getGameId();
        template.phase=board.getPhase();
        template.step=board.getStep();
        template.stepMode=board.stepMode;



        for (int i=0; i<board.width; i++) {
            for (int j=0; j<board.height; j++) {
                Space space = board.getSpace(i,j);
                SpaceTemplate tempSpace = template.spaces[i][j];
                if (space.getConveyor()!=null) {
                  tempSpace.Conveyor=space.getConveyor();
                }
                if (space.getCheckPoint()!=null) {
                    tempSpace.checkPoint=space.getCheckPoint();
                }
                if(space.getWall()) {
                    tempSpace.wallHeading =space.getWallFacing();
                }
                if(space.getGear()!=null){
                    tempSpace.gearRotation=space.getGear();
                }

            }


        }





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
                } catch (IOException e2) {}
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e2) {}
            }
        }
    }

}

