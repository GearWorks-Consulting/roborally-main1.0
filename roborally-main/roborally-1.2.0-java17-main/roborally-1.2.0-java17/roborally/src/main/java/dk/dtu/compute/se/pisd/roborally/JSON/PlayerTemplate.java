package dk.dtu.compute.se.pisd.roborally.JSON;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Heading;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

public class PlayerTemplate {
    private Heading heading = SOUTH;
     public static int NO_REGISTERS ;
     public static int NO_CARDS ;
     private String name;
    private String color;
    private int tokens;


    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }
}
