package dk.dtu.compute.se.pisd.roborally.model;

/**
 *
 *  The conveyorBelt class represents a conveyor belt in the game.
 *  It determines the direction and color of the conveyor belt.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */


public class conveyorBelt   {
    private final Heading direction;
    private final String colour ;

    /**
     * Constructs a conveyorBelt object with the specified color and direction.
     *
     * @param colour  the color of the conveyor belt
     * @param heading the direction of the conveyor belt
     */

    public conveyorBelt(String colour,Heading heading ){

     this.colour = colour;
     direction = heading;
    }
    /**
     * Retrieves the color of the conveyor belt.
     *
     * @return the color of the conveyor belt
     */

    public String getColour() {
        return colour;
    }

    /**
     * Retrieves the direction of the conveyor belt.
     *
     * @return the direction of the conveyor belt
     */

    public Heading getDirection() {
        return direction;
    }


}
