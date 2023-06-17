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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * The Command enumeration represents different commands that can be used in the game.
 * Each command has a display name and may have options associated with it.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */
public enum Command {

    // This is a very simplistic way of realizing different commands.

    FORWARD("Fwd"),
    RIGHT("Turn Right"),
    LEFT("Turn Left"),
    FAST_FORWARD("Fast Fwd"),
    UTURN("UTURN"),
    // XXX Assignment P3
    OPTION_LEFT_RIGHT("Left OR Right", LEFT, RIGHT);


    final public String displayName;

    final private List<Command> options;
    /**
     * Constructs a new Command enum with the given display name and optional sub-commands.
     *
     * @param displayName the display name of the command
     * @param options     the optional sub-commands associated with the command
     */
    Command(String displayName, Command... options) {
        this.displayName = displayName;
        this.options = Collections.unmodifiableList(Arrays.asList(options));
    }
    /**
     * Checks if the command has options associated with it.
     *
     * @return true if the command has options, false otherwise
     */
    public boolean isInteractive() {
        return !options.isEmpty();

    }

    /**
     * Retrieves the list of options associated with the command.
     *
     * @return the list of options associated with the command
     */
    public List<Command> getOptions() {
        return options;
    }

}
