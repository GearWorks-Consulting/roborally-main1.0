package dk.dtu.compute.se.pisd.roborally.model;



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
