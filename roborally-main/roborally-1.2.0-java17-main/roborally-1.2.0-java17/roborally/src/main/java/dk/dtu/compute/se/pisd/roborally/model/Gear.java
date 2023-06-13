package dk.dtu.compute.se.pisd.roborally.model;
import dk.dtu.compute.se.pisd.designpatterns.observer.Observer;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

import java.awt.*;
import java.util.Vector;

//this class defines the gear action field, gear rotation, which turns the gear action, to the right.
/**
 * ...
 *
 * @author Moiz H. Khalil, s215617@dtu.dk
 *
 */
public class Gear {
    private final Heading direction;
    public Gear(Heading heading ){

        direction = heading;
    }

    //Used for Unittesting
    //get Direction isn't relevant for rotation, since it takes in account of it turning right no matter the direction the player comes from.
    public Heading getDirection() {
        return direction;
    }
}
