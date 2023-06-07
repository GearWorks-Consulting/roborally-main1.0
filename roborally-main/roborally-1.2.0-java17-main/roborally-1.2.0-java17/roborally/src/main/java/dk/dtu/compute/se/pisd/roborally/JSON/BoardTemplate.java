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
package dk.dtu.compute.se.pisd.roborally.JSON;

import dk.dtu.compute.se.pisd.roborally.model.Phase;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.ArrayList;
import java.util.List;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALISATION;


/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class BoardTemplate {

    public SpaceTemplate[][] spaces;
    public int width;
    public int height;

    public String boardName;

    public int gameId;

    public List<PlayerTemplate> players = new ArrayList<>();

    public PlayerTemplate current;

    public Phase phase = INITIALISATION;

    public int step = 0;

    public boolean stepMode;

    public BoardTemplate(int width, int height) {
    spaces = new SpaceTemplate[width][height];
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            SpaceTemplate space = new SpaceTemplate();
          space.x=x;
          space.y=y;
            spaces[x][y] = space;

        }
    }
    this.width=width;
    this.height=height;
}
    public PlayerTemplate getPlayer(int i) {
        if (i >= 0 && i < players.size()) {
            return players.get(i);
        } else {
            return null;
        }
    }
    public int getPlayersNumber() {
        return players.size();
    }
}

