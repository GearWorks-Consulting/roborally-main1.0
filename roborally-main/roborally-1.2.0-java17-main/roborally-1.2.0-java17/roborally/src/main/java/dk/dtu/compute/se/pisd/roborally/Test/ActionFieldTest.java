package dk.dtu.compute.se.pisd.roborally.Test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import dk.dtu.compute.se.pisd.roborally.model.conveyorBelt;
import dk.dtu.compute.se.pisd.roborally.model.Heading;

import dk.dtu.compute.se.pisd.roborally.model.Gear;

import dk.dtu.compute.se.pisd.roborally.model.PushPanel;
/**
 *
 * @author Moiz H. Khalil, s215617@dtu.dk
 *@version 1.8 QA.
 * @since 17-6-2023
 *
 */


public class ActionFieldTest {

    // Unit tests for blue Conveyor Belt colour
    @Test
    public void testConveyorBeltGetColourBlue() {
        String expectedColour = "Blue";
        conveyorBelt belt = new conveyorBelt(expectedColour, Heading.NORTH);
        String actualColour = belt.getColour();
        assertEquals(expectedColour, actualColour);
    }

    // Unit tests for Conveyor Belt colour green
    @Test
    public void testConveyorBeltGetColourGreen() {
        String expectedColour = "Green";
        conveyorBelt belt = new conveyorBelt(expectedColour, Heading.NORTH);
        String actualColour = belt.getColour();
        assertEquals(expectedColour, actualColour);
    }

    // Unit tests for Conveyor Belt direction
    @Test
    public void testConveyorBeltGetDirectionEast() {
        Heading expectedDirection = Heading.EAST;
        conveyorBelt belt = new conveyorBelt("Blue", expectedDirection);
        Heading actualDirection = belt.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testConveyorBeltGetDirectionWest() {
        Heading expectedDirection = Heading.WEST;
        conveyorBelt belt = new conveyorBelt("Blue", expectedDirection);
        Heading actualDirection = belt.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testConveyorBeltGetDirectionNorth() {
        Heading expectedDirection = Heading.NORTH;
        conveyorBelt belt = new conveyorBelt("Blue", expectedDirection);
        Heading actualDirection = belt.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testConveyorBeltGetDirectionSouth() {
        Heading expectedDirection = Heading.SOUTH;
        conveyorBelt belt = new conveyorBelt("Blue", expectedDirection);
        Heading actualDirection = belt.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }

    // Unit tests for Gear Rotation
    @Test
    public void testGearRotationGetDirectionNorth() {
        Heading expectedDirection = Heading.NORTH;
        Gear gear = new Gear(expectedDirection);
        Heading actualDirection = gear.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testGearRotationGetDirectionWest() {
        Heading expectedDirection = Heading.WEST;
        Gear gear = new Gear(expectedDirection);
        Heading actualDirection = gear.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testGearRotationGetDirectionEast() {
        Heading expectedDirection = Heading.EAST;
        Gear gear = new Gear(expectedDirection);
        Heading actualDirection = gear.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testGearRotationGetDirectionSouth() {
        Heading expectedDirection = Heading.SOUTH;
        Gear gear = new Gear(expectedDirection);
        Heading actualDirection = gear.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }



    // Unit tests for Push Panel
    @Test
    public void testPushPanelGetDirectionNorth() {
        Heading expectedDirection = Heading.NORTH;
        PushPanel pushPanel = new PushPanel(expectedDirection);
        Heading actualDirection = pushPanel.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testPushPanelGetDirectionWEST() {
        Heading expectedDirection = Heading.WEST;
        PushPanel pushPanel = new PushPanel(expectedDirection);
        Heading actualDirection = pushPanel.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
    @Test
    public void testPushPanelGetDirectionEAST() {
        Heading expectedDirection = Heading.EAST;
        PushPanel pushPanel = new PushPanel(expectedDirection);
        Heading actualDirection = pushPanel.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void testPushPanelGetDirectionSouth() {
        Heading expectedDirection = Heading.SOUTH;
        PushPanel pushPanel = new PushPanel(expectedDirection);
        Heading actualDirection = pushPanel.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }



}
