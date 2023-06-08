package dk.dtu.compute.se.pisd.roborally.model;



public class conveyorBelt   {
    private Heading direction;
    private String colour ;
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
