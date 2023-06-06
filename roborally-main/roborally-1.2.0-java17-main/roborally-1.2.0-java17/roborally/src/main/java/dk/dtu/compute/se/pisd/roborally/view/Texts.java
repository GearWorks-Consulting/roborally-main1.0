package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class Texts extends VBox implements ViewObserver {
    private Text text = new Text();
    public Pane overlayPane;
    private Timeline textRemovalTimeline;
    private Board board;



    //final public Board board;

    public Texts(Board board) {
        this.board = board;

        overlayPane = new Pane();
        board.attach(this);
        update(board);
    }

    public void textDisplay() {

        System.out.println("kage kage test test");
    }

    //public Pane getOverlayPane() {
    //  return overlayPane;
    //}

    //public void setOverlayPane(Pane overlayPane) {
    //  this.overlayPane = overlayPane;
    //}
    private void removeText() {
        overlayPane.getChildren().remove(text);
    }
    @Override
    public void updateView(Subject subject) {

            // Set the position of the overlayPane using absolute positioning

            //textRemovalTimeline.playFromStart();

    }
}
