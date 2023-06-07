package dk.dtu.compute.se.pisd.roborally.JSON;

import dk.dtu.compute.se.pisd.roborally.model.*;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

public class PlayerTemplate {
    public Heading heading = SOUTH;
    public int NO_REGISTERS ;
    public static int NO_CARDS ;
    public String name;
    public String color;


    public int tokens;
     public Board board;

    public SpaceTemplate space;
    final public static int NO_REGISTERS2 = 5;
    final public static int NO_CARDS2 = 8;

    public CommandCardFieldTemplate[] program;
    public CommandCardFieldTemplate[] cards;

     public    CommandCardFieldTemplate programtest;
    public PlayerTemplate() {
         programtest = new CommandCardFieldTemplate();
        program = new CommandCardFieldTemplate[NO_REGISTERS2];
        cards = new CommandCardFieldTemplate[NO_CARDS2];


        for (int i = 0; i < program.length; i++) {
            program[i] = new CommandCardFieldTemplate();
        }


        for (int i = 0; i < cards.length; i++) {
            cards[i] = new CommandCardFieldTemplate();
        }

    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }
}
