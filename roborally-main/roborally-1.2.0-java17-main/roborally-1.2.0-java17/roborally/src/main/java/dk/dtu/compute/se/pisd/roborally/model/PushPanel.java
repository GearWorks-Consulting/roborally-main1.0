package dk.dtu.compute.se.pisd.roborally.model;

/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
public class PushPanel {
    private final Heading direction;

    public PushPanel( Heading heading ){

     direction = heading;
    }

    public Heading getDirection() {
        return direction;
    }


}
