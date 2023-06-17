package dk.dtu.compute.se.pisd.roborally.model;

/**
 *
 *  The PushPanel class represents a push panel in the game.
 *  It determines the direction in which players are pushed when they step on the push panel.
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *,
 */
public class PushPanel {
    private final Heading direction;

    /**
     * Constructs a PushPanel object with the specified direction.
     *
     * @param heading the direction in which players are pushed
     */
    public PushPanel( Heading heading ){

     direction = heading;
    }

    /**
     * Retrieves the direction in which players are pushed by the push panel.
     *
     * @return the direction of the push panel
     */
    public Heading getDirection() {
        return direction;
    }


}
