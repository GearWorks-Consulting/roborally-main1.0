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

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
/**
 *
 * @author Abdi, Mathias, & Moiz H. Khalil
 * @version 2.0 Release.
 *  @since 17-6-2023
 *
 */
public class RoboRallyMenuBar extends MenuBar {

    private final AppController appController;

    private final Menu controlMenu1;
    private final MenuItem rules;


    private Menu controlMenu;

    private MenuItem hostGame;

    private MenuItem joinGame;

    private MenuItem saveGame;

    private MenuItem localGame;

    private MenuItem loadGame;

    private MenuItem stopGame;

    private MenuItem exitApp;

    public RoboRallyMenuBar(AppController appController) {
        this.appController = appController;

        controlMenu = new Menu("File");
        controlMenu1 = new Menu("Information");

        //displays tab from the menu
        this.getMenus().add(controlMenu);
        this.getMenus().add(controlMenu1);







        hostGame = new MenuItem("Host Game");
        hostGame.setOnAction( e -> this.appController.HostGame());
        controlMenu.getItems().add(hostGame);

        joinGame = new MenuItem("Join Game");
        joinGame.setOnAction( e -> this.appController.JoinGame());
        controlMenu.getItems().add(joinGame);

        localGame = new MenuItem("Local Game");
        localGame.setOnAction(e -> this.appController.localGame());
        controlMenu.getItems().add(localGame);

        stopGame = new MenuItem("Stop Game");
        stopGame.setOnAction(e -> this.appController.stopGame());
        controlMenu.getItems().add(stopGame);

        saveGame = new MenuItem("Save Game");
        saveGame.setOnAction( e -> this.appController.saveGame());
        controlMenu.getItems().add(saveGame);


        loadGame = new MenuItem("Load Game");
        loadGame.setOnAction(e -> this.appController.loadGame());
        controlMenu.getItems().add(loadGame);

        rules = new MenuItem("Information");
        rules.setOnAction(e -> this.appController.showInformation());
        controlMenu1.getItems().add(rules);

        exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> this.appController.exit());
        controlMenu.getItems().add(exitApp);

        controlMenu.setOnShowing(e -> update());
        controlMenu.setOnShown(e -> this.updateBounds());
        update();
    }

    public void update() {

        boolean isGameRunning = appController.isGameRunning();
        boolean isLoadGameVisible = !isGameRunning;
        boolean isOtherItemsVisible = true;

        localGame.setVisible(isOtherItemsVisible);
        saveGame.setVisible(isOtherItemsVisible && isGameRunning);
        stopGame.setVisible(isOtherItemsVisible && isGameRunning);
        hostGame.setVisible(isOtherItemsVisible);
        loadGame.setVisible(isLoadGameVisible);
        rules.setVisible(true);
    }

}
