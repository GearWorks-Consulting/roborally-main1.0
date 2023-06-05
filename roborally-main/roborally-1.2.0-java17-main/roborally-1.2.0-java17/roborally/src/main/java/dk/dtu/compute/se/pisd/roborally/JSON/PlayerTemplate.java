package dk.dtu.compute.se.pisd.roborally.JSON;

import dk.dtu.compute.se.pisd.roborally.model.Heading;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

public class PlayerTemplate {
    private Heading heading = SOUTH;

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }
}
