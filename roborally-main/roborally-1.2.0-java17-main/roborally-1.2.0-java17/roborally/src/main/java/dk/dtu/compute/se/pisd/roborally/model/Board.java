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
package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALISATION;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk med et twist fra Mathias og Abdi fra gruppe 21
 * @version 1.0 prototype
 * @since 2023-04-16
 */
public class Board extends Subject {

    public final int width;

    public final int height;

    public final String boardName;

    public Integer gameId;

    public  Space[][] spaces;

    public List<Player> players = new ArrayList<>();

    public  Player current;

    public Phase phase = INITIALISATION;

    public int step = 0;

    public boolean stepMode;

    public Board(int width, int height, @NotNull String boardName) {
        this.boardName = boardName;
        this.width = width;
        this.height = height;
        spaces = new Space[width][height];
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Space space;
                //Space space = new Space(this, x, y);
                space = new Space(this, x, y);
/* //Board 1
                if(x==1 && y==3) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }

                if(x==1 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }

                if(x==1 && y==4) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }

                if(x==2 && y==3) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }

                if(x==2 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }

                if(x==2 && y==4) {
                    space.addWall(Heading.SOUTH); //HERE
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }

                if(x==1 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                if(x==0 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                //Wall 2.
                if(x==1 && y==4) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }
                if(x==1 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }



                if(x==2 && y==4) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }
                if(x==3 && y==4) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                //Wall 3


                if(x==2 && y==4) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }
                if(x==2 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }


                if(x==5 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                if(x==5 && y==6) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==6) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }
                if(x==5 && y==7) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==7) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                 */
                //Board 1


                //Board 2
                /*
                if(x==1 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                if(x==0 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                //Wall 2.
                if(x==1 && y==4) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }
                if(x==1 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }



                if(x==2 && y==4) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }
                if(x==3 && y==4) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                //Wall 3


                if(x==2 && y==4) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }
                if(x==2 && y==5) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }


                if(x==5 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                if(x==5 && y==6) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==6) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }
                if(x==5 && y==7) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }
                if(x==4 && y==7) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                */

                //Board 2

                /* //Board 3
                if(x==9 && y==8) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                if(x== 10 && y==8) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                if(x==10 && y==8) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }

                if(x==10 && y==9) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }

                if(x==4 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }

                if(x==5 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }


                //Wall 2.
                if(x==2 && y==5) {
                    space.addWall(Heading.SOUTH);
                    space.setWall();
                    space.setWallFacing(Heading.SOUTH);
                }
                if(x==2 & y==6) {
                    space.addWall(Heading.NORTH);
                    space.setWall();
                    space.setWallFacing(Heading.NORTH);
                }



                if(x==6 && y==5) {
                    space.addWall(Heading.EAST);
                    space.setWall();
                    space.setWallFacing(Heading.EAST);
                }
                if(x==7 && y==5) {
                    space.addWall(Heading.WEST);
                    space.setWall();
                    space.setWallFacing(Heading.WEST);
                }

                */  //Board 3

                spaces[x][y] = space;

            }
        }
        this.stepMode = false;

        //Board 1
        /*
        conveyorBelt con1 = new conveyorBelt("blue",Heading.SOUTH);
        conveyorBelt con2 = new conveyorBelt("green",Heading.WEST);
        conveyorBelt con3 = new conveyorBelt("green",Heading.WEST);
        conveyorBelt con4 = new conveyorBelt("green",Heading.WEST);
        CheckPoint checkPoint0 = new CheckPoint(0);
        CheckPoint checkPoint1 = new CheckPoint(1);
        CheckPoint checkPoint2 = new CheckPoint(2);
        Gear gear1 = new Gear(Heading.SOUTH);
        Gear gear2 = new Gear(Heading.SOUTH);

        // Test for conveyor belt : Delete this later
        spaces[0][5].setConveyor(con1);
        spaces[4][5].setConveyor(con2);
        spaces[3][6].setConveyor(con3);
        spaces[3][7].setConveyor(con3);
        spaces[5][9].setConveyor(con3);
        spaces[4][6].setCheckPoint(checkPoint0);
        spaces[2][4].setCheckPoint(checkPoint1);
        spaces[6][7].setCheckPoint(checkPoint2);
        spaces[5][3].setGear(gear1);
        spaces[6][4].setGear(gear2);

         */
        //Board 1

        //Board 2
        /*
        conveyorBelt con1 = new conveyorBelt("blue",Heading.SOUTH);
        conveyorBelt con2 = new conveyorBelt("green",Heading.WEST);
        conveyorBelt con3 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con4 = new conveyorBelt("green",Heading.NORTH);
        Gear gear1 = new Gear(Heading.SOUTH);
        Gear gear2 = new Gear(Heading.SOUTH);

        CheckPoint checkPoint0 = new CheckPoint(0);
        CheckPoint checkPoint1 = new CheckPoint(1);
        CheckPoint checkPoint2 = new CheckPoint(2);

        spaces[5][7].setCheckPoint(checkPoint0);
        spaces[7][4].setCheckPoint(checkPoint1);
        spaces[0][7].setCheckPoint(checkPoint2);


        spaces[0][5].setConveyor(con1);
        spaces[4][5].setConveyor(con2);
        spaces[3][6].setConveyor(con3);
        spaces[3][7].setConveyor(con3);
        spaces[5][9].setConveyor(con3);
        spaces[5][3].setGear(gear1);
        spaces[6][4].setGear(gear2);

        */
        //Board 2

        /* //board 3
        conveyorBelt con1 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con2 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con3 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con4 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con5 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con6 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con7 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con8 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con9 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con10 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con11 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con12 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con13 = new conveyorBelt("green",Heading.WEST);
        conveyorBelt con14 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con15 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con16 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con17 = new conveyorBelt("green",Heading.WEST);

        conveyorBelt con18 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con19 = new conveyorBelt("green",Heading.EAST);
        conveyorBelt con20 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con21 = new conveyorBelt("green",Heading.SOUTH);

        conveyorBelt con22 = new conveyorBelt("green",Heading.NORTH);
        conveyorBelt con23 = new conveyorBelt("green",Heading.WEST);
        conveyorBelt con24 = new conveyorBelt("green",Heading.SOUTH);
        conveyorBelt con25 = new conveyorBelt("green",Heading.NORTH);

        CheckPoint checkPoint0 = new CheckPoint(0);
        CheckPoint checkPoint1 = new CheckPoint(1);
        CheckPoint checkPoint2 = new CheckPoint(2);
        Gear gear1 = new Gear(Heading.SOUTH);
        Gear gear2 = new Gear(Heading.SOUTH);

        // Test for conveyor belt : Delete this later
        spaces[3][9].setConveyor(con1);
        spaces[5][2].setConveyor(con2);
        spaces[7][2].setConveyor(con3);
        spaces[0][2].setConveyor(con4);
        spaces[2][2].setConveyor(con5);
        spaces[4][3].setConveyor(con6);
        spaces[6][3].setConveyor(con7);
        spaces[0][4].setConveyor(con8);
        spaces[3][4].setConveyor(con9);
        spaces[5][4].setConveyor(con10);
        spaces[1][5].setConveyor(con11);
        spaces[4][5].setConveyor(con12);
        spaces[3][6].setConveyor(con13);
        spaces[5][6].setConveyor(con14);
        spaces[1][7].setConveyor(con15);
        spaces[3][7].setConveyor(con16);
        spaces[1][7].setConveyor(con17);

        spaces[6][8].setConveyor(con18);
        spaces[9][2].setConveyor(con19);
        spaces[11][3].setConveyor(con20);
        spaces[8][4].setConveyor(con21);

        spaces[9][5].setConveyor(con22);
        spaces[11][5].setConveyor(con23);
        spaces[10][7].setConveyor(con24);
        spaces[11][8].setConveyor(con25);

        spaces[2][7].setCheckPoint(checkPoint1);
        spaces[5][5].setCheckPoint(checkPoint0);
        spaces[10][8].setCheckPoint(checkPoint2);

        spaces[7][7].setGear(gear1);
            */ //board 3

    }



    public Board(int width, int height) {
        this(width, height, "defaultboard");
    }

    public Integer getGameId() {
        return gameId;
    }


    public void setGameId(int gameId) {
        if (this.gameId == null) {
            this.gameId = gameId;
        } else {
            if (!this.gameId.equals(gameId)) {
                throw new IllegalStateException("A game with a set id may not be assigned a new id!");
            }
        }
    }

    public Space getSpace(int x, int y) {
        if (x >= 0 && x < width &&
                y >= 0 && y < height) {
            return spaces[x][y];
        } else {
            return null;
        }
    }

    public int getPlayersNumber() {
        return players.size();
    }

    public void addPlayer(@NotNull Player player) {
        if (player.board == this && !players.contains(player)) {
            players.add(player);
            notifyChange();
        }
    }

    public Player getPlayer(int i) {
        if (i >= 0 && i < players.size()) {
            return players.get(i);
        } else {
            return null;
        }
    }

    public Player getCurrentPlayer() {
        return current;
    }

    public void setCurrentPlayer(Player player) {
        if (player != this.current && players.contains(player)) {
            this.current = player;
            notifyChange();
        }
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        if (phase != this.phase) {
            this.phase = phase;
            notifyChange();
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        if (step != this.step) {
            this.step = step;
            notifyChange();
        }
    }

    public boolean isStepMode() {
        return stepMode;
    }

    public void setStepMode(boolean stepMode) {
        if (stepMode != this.stepMode) {
            this.stepMode = stepMode;
            notifyChange();
        }
    }

    public int getPlayerNumber(@NotNull Player player) {
        if (player.board == this) {
            return players.indexOf(player);
        } else {
            return -1;
        }
    }

    /**
     * Returns the neighbour of the given space of the board in the given heading.
     * The neighbour is returned only, if it can be reached from the given space
     * (no walls or obstacles in either of the involved spaces); otherwise,
     * null will be returned.
     *
     * @param space the space for which the neighbour should be computed
     * @param heading the heading of the neighbour
     * @return the space in the given direction; null if there is no (reachable) neighbour
     */
    public Space getNeighbour(@NotNull Space space, @NotNull Heading heading) {
        int x = space.x;
        int y = space.y;
        switch (heading) {
            case SOUTH:
                y = (y + 1) % height;
                break;
            case WEST:
                x = (x + width - 1) % width;
                break;
            case NORTH:
                y = (y + height - 1) % height;
                break;
            case EAST:
                x = (x + 1) % width;
                break;
        }

        return getSpace(x, y);
    }

    public String getStatusMessage() {
        // this is actually a view aspect, but for making assignment V1 easy for
        // the students, this method gives a string representation of the current
        // status of the game

        // XXX: V2 changed the status so that it shows the phase, the player and the step
        return "Phase: " + getPhase().name() +
                ", Current player  " + getCurrentPlayer().getName() +
                ", Step: " + getStep() + ", Token countrer: "+getCurrentPlayer().getTokens();
    }


}
