package dk.dtu.compute.se.pisd.roborally.model;

/**
 * ...
 *
 * @author Moiz H. Khalil, s215617@dtu.dk
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
