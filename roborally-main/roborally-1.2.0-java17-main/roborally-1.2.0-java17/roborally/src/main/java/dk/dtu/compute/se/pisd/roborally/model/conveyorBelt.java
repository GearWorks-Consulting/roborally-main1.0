package dk.dtu.compute.se.pisd.roborally.model;

/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */

public class conveyorBelt   {
    private final Heading direction;
    private final String colour ;
    public conveyorBelt(String colour,Heading heading ){

     this.colour = colour;
     direction = heading;
    }

    public String getColour() {
        return colour;
    }

    public Heading getDirection() {
        return direction;
    }


}
