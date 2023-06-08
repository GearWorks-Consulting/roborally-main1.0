package dk.dtu.compute.se.pisd.roborally.model;
import dk.dtu.compute.se.pisd.designpatterns.observer.Observer;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

import java.awt.*;
import java.util.Vector;

//this class defines the gear action field, gear rotation, which turns the gear action, to the right.

public class Gear {
    private Heading direction;
    public Gear(Heading heading ){

        direction = heading;
    }

    public Heading getDirection() {
        return direction;
    }


}
