package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class RoboRallyMenuBar extends MenuBar {

    private AppController appController;

    private Menu controlMenu;
    private Menu controlMenu1;

    private MenuItem hostGame;
    private MenuItem joinGame;
    private MenuItem localGame;

    private MenuItem saveGame;
    private MenuItem newGame;
    private MenuItem loadGame;
    private MenuItem loadGameLocal;
    private MenuItem stopGame;
    private MenuItem exitApp;
    private MenuItem exitAppOnline;

    public RoboRallyMenuBar(AppController appController) {
        this.appController = appController;

        controlMenu1 = new Menu("Local");
        controlMenu = new Menu("Online");

        this.getMenus().add(controlMenu);
        this.getMenus().add(controlMenu1);

        localGame = new MenuItem("Local Game");
        localGame.setOnAction(e -> this.appController.localGame());
        controlMenu1.getItems().add(localGame);

        joinGame = new MenuItem("Join Game");
        joinGame.setOnAction(e -> this.appController.JoinGame());
        controlMenu.getItems().add(joinGame);

        hostGame = new MenuItem("Host Game");
        hostGame.setOnAction(e -> this.appController.HostGame());
        controlMenu.getItems().add(hostGame);

        newGame = new MenuItem("New Game");
        newGame.setOnAction(e -> this.appController.newGame());
        controlMenu1.getItems().add(newGame);

        stopGame = new MenuItem("Stop Game");
        stopGame.setOnAction(e -> this.appController.stopGame());
        controlMenu1.getItems().add(stopGame);

        saveGame = new MenuItem("Save Game");
        saveGame.setOnAction(e -> this.appController.saveGame());
        controlMenu1.getItems().add(saveGame);

        loadGame = new MenuItem("Load Game");
        loadGame.setOnAction(e -> this.appController.loadGame());
        controlMenu.getItems().add(loadGame);

        loadGameLocal = new MenuItem("Load Game");
        loadGameLocal.setOnAction(e -> this.appController.loadGame());
        controlMenu1.getItems().add(loadGameLocal);

        exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> this.appController.exit());
        controlMenu1.getItems().add(exitApp);

        exitAppOnline = new MenuItem("Exit");
        exitAppOnline.setOnAction(e -> this.appController.exit());
        controlMenu.getItems().add(exitAppOnline);

        controlMenu.setOnShowing(e -> update());
        controlMenu.setOnShown(e -> this.updateBounds());
        controlMenu1.setOnShowing(e -> update());
        controlMenu1.setOnShown(e -> this.updateBounds());
        update();
    }

    public void update() {
        boolean isGameRunning = appController.isGameRunning();
        boolean isLoadGameVisible = !isGameRunning; // Set the visibility of Load Game based on game running status
        boolean isOtherItemsVisible = true; // Set the visibility of other menu items based on Load Game visibility

        newGame.setVisible(isOtherItemsVisible && isGameRunning);
        stopGame.setVisible(isOtherItemsVisible && isGameRunning);
        saveGame.setVisible(isOtherItemsVisible && isGameRunning);

        hostGame.setVisible(isOtherItemsVisible);
        joinGame.setVisible(isOtherItemsVisible);
        loadGame.setVisible(isLoadGameVisible);
        loadGameLocal.setVisible(isLoadGameVisible);

    }
}
