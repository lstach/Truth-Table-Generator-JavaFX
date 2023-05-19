package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuView implements FXComponent{

    private Model model;
    private Controller controller;
    private Stage stage;


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

        // File tab buttons
        MenuItem saveAs = new MenuItem("Save as .csv");
        // Edit tab buttons
        MenuItem copy = new MenuItem("Copy Values");
        // About tab buttons
        MenuItem gitHub = new MenuItem("github");

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
