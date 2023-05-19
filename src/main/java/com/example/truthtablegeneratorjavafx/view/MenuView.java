package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.HelloApplication;
import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuView implements FXComponent{

    private Model model;
    private Controller controller;
    private Stage stage;
    private Application application;


    public MenuView(Model model, Controller controller, Stage stage){
        this.model = model;
        this.controller = controller;
        this.stage = stage;
    }

    @Override
    public Parent render() {

        // Create MenuBar
        MenuBar menuBar = new MenuBar();
        // Create menu items
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu aboutMenu = new Menu("About");

        // get icon as svg for the GitHub tab
        SVGPath githubIcon = new SVGPath();
        githubIcon.setContent("M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z");

        // File tab buttons
        MenuItem saveAs = new MenuItem("Save as .csv");
        saveAs.setGraphic(new ImageView("saveIcon.png"));
        // Edit tab buttons
        MenuItem copy = new MenuItem("Copy Values");
        copy.setGraphic(new ImageView("copyIcon.png"));
        // About tab buttons
        MenuItem gitHub = new MenuItem("GitHub", githubIcon);

        // add click handlers
        gitHub.setOnAction(
                (ActionEvent event) -> {
                    controller.clickGitHub();
                });

        copy.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCopy();
                });

        saveAs.setOnAction(actionEvent -> {
            if (model.getTruthTable().length > 0){ // save the truth table as a .csv file
                FileChooser fileChooser = new FileChooser();
                File location = fileChooser.showSaveDialog(stage);
                controller.clickSaveAs(location);
            } else{ // don't try and save if there is nothing to save.
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have to make a truth table before you can save it as a .csv file.", ButtonType.CLOSE);
                alert.showAndWait();
            }
        });

        fileMenu.getItems().add(saveAs);

        editMenu.getItems().add(copy);
        aboutMenu.getItems().add(gitHub);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(aboutMenu);

        return menuBar;
    }
}
