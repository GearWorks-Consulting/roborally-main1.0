/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.view;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.model.CheckPoint;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */

/**
 * This class represents the graphical view of a Space in the RoboRally game.
 */
public class SpaceView extends StackPane implements ViewObserver {

    final public static int SPACE_HEIGHT = 60; // 75;
    final public static int SPACE_WIDTH = 60; // 75;

   final public static Color wallColor = Color.BLACK;
    final public static double wallThickness = 3;


    public final Space space;

    /**
     * Constructs a SpaceView object with the given Space model object.
     *
     * @param space The Space model object associated with this view.
     */
    public SpaceView(@NotNull Space space) {
        this.space = space;

        //space settings set to string attributes
        this.setPrefWidth(SPACE_WIDTH);
        this.setMinWidth(SPACE_WIDTH);
        this.setMaxWidth(SPACE_WIDTH);

        this.setPrefHeight(SPACE_HEIGHT);
        this.setMinHeight(SPACE_HEIGHT);
        this.setMaxHeight(SPACE_HEIGHT);



        /**
         * The following code detects based on position of PlacedWall and Heading.
         * It then creates a stroke(which fills out based on wallThinkness) for each wall and of course updates the space.
         */

        double up = space.PlacedWall(Heading.NORTH) ? wallThickness : 0;
        double right = space.PlacedWall(Heading.EAST) ? wallThickness : 0;
        double down = space.PlacedWall(Heading.SOUTH) ? wallThickness : 0;
        double left = space.PlacedWall(Heading.WEST) ? wallThickness : 0;

        BorderWidths borderWidths = new BorderWidths(up, right, down, left);
        BorderStroke borderStroke = new BorderStroke(wallColor, BorderStrokeStyle.SOLID, null, borderWidths);
        Border border = new Border(borderStroke);
        this.setBorder(border);

        space.attach(this);
        update(space);
    }


    //Updates the graphical representation of the player and other elements in the Space.
    private void updatePlayer() {
        this.getChildren().clear();

// Check if the sum of 'space.x' and 'space.y' is an even number
        if ((space.x + space.y) % 2 == 0) {
            // Create an ImageView object to display an image
            ImageView imageView = new ImageView();
            // Load the image file "BackgroundPanel.png" with dimensions 60x60
            Image image = new Image("BackgroundPanel.png", 60, 60, false, false);
            // Set the loaded image as the content of the ImageView
            imageView.setImage(image);
            // Add the ImageView to the list of child nodes of the current container
            this.getChildren().add(imageView);
            // Uncomment the next line to set a white background color for the container
            //this.setStyle("-fx-background-color: white;");
        }
        else {
            // Create an ImageView object to display an image
            ImageView imageView = new ImageView();
            // Load the image file "BackgroundPanel.png" with dimensions 60x60
            Image image = new Image("BackgroundPanel.png", 60, 60, false, false);
            // Set the loaded image as the content of the ImageView
            imageView.setImage(image);
            // Add the ImageView to the list of child nodes of the current container
            this.getChildren().add(imageView);
            // Uncomment the next line to set a black background color for the container
            //this.setStyle("-fx-background-color: black;");
        }
        // Display the corresponding checkpoint image based on the order number
        if(space.getCheckPoint()!=null &&space.getCheckPoint().getOrderNo()==0){
            ImageView imageView = new ImageView();
            Image image = new Image("cp1.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);
        }
        else if (space.getCheckPoint()!=null &&space.getCheckPoint().getOrderNo()==1){
            ImageView imageView = new ImageView();
            Image image = new Image("cp2.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);
        }
        else if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo()==2){
            ImageView imageView = new ImageView();
            Image image = new Image("cp3.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);

        }
        else if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo()==3){
            ImageView imageView = new ImageView();
            Image image = new Image("cp4.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);

        }
        else if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo()==4){
            ImageView imageView = new ImageView();
            Image image = new Image("cp5.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);

        }
        else if (space.getCheckPoint()!=null && space.getCheckPoint().getOrderNo()==5){
            ImageView imageView = new ImageView();
            Image image = new Image("cp6.png",60,60,false,false);
            imageView.setImage(image);
            this.getChildren().add(imageView);
        }
        // Display the corresponding action field image based on the direction
            if (space.getConveyor() != null && space.getConveyor().getColour().equals("blue")) {
            ImageView imageView = new ImageView();
            Image image = null;
            if(space.getConveyor().getDirection() == Heading.WEST) {
                image = new Image("BlueWest.png", 60, 60, false, false);
            } else if(space.getConveyor().getDirection() == Heading.EAST ) {
                image = new Image("BlueEast.png", 60, 60, false, false);
            } else if ( space.getConveyor().getDirection() == Heading.SOUTH ) {
                image = new Image("BlueSouth.png", 60, 60, false, false);
            }else if (space.getConveyor().getDirection() == Heading.NORTH ) {
                image = new Image("Blue.png", 60, 60, false, false);
            }
            imageView.setImage(image);

            this.getChildren().add(imageView);
        }
            // Display the corresponding action field image based on the direction
        else if (space.getConveyor() != null && space.getConveyor().getColour().equals("green")) {
            ImageView imageView = new ImageView();
            Image image = null;
            if(space.getConveyor().getDirection() == Heading.WEST) {
                image = new Image("GreenWest.png", 60, 60, false, false);
            } else if(space.getConveyor().getDirection() == Heading.EAST ) {
                image = new Image("GreenEastt.png", 60, 60, false, false);
            } else if ( space.getConveyor().getDirection() == Heading.SOUTH ) {
                image = new Image("GreenSouth.png", 60, 60, false, false);
            }else if (space.getConveyor().getDirection() == Heading.NORTH ) {
                image = new Image("Green.png", 60, 60, false, false);
            }
            imageView.setImage(image);
            this.getChildren().add(imageView);


        }
            // Display the corresponding action field image based on the direction
            else if (space.getPushPanel() != null) {
                ImageView imageView = new ImageView();
                Image image = null;
                if(space.getPushPanel().getDirection() == Heading.WEST) {
                    image = new Image("PushPanelWest.png", 60, 60, false, false);
                } else if(space.getPushPanel().getDirection() == Heading.EAST ) {
                    image = new Image("PushPanelEast.png", 60, 60, false, false);
                } else if ( space.getPushPanel().getDirection() == Heading.SOUTH ) {
                    image = new Image("PushPanelSouth.png", 60, 60, false, false);
                }else if (space.getPushPanel().getDirection() == Heading.NORTH ) {
                    image = new Image("PushPanelNorth.png", 60, 60, false, false);
                }
                imageView.setImage(image);
                this.getChildren().add(imageView);


            }

            // Display the corresponding action field image
      else  if(space.getGear()!=null) {
            ImageView imageView = new ImageView();
            Image image = null;
            if (true) {
                image = new Image("gear.png", 60, 60, false, false);
            }
            imageView.setImage(image);
            this.getChildren().add(imageView);
        }



        Player player = space.getPlayer();

        // Display the player arrow based on the player's heading
        if (player != null) {
            Polygon arrow = new Polygon(0.0, 0.0,
                    10.0, 20.0,
                    20.0, 0.0 );
            try {
                arrow.setFill(Color.valueOf(player.getColor()));
            } catch (Exception e) {
                arrow.setFill(Color.MEDIUMPURPLE);
            }

            arrow.setRotate((90*player.getHeading().ordinal())%360);
            this.getChildren().add(arrow);
        }
    }
    @Override
    public void updateView(Subject subject) {
        if (subject == this.space) {
            updatePlayer();
        }
    }
}