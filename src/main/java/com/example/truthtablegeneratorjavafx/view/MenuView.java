package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuView implements FXComponent{

    private Model model;
    private Controller controller;


    public MenuView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        initialize();
    }

    private void initialize(){

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
        MenuItem saveAs = new MenuItem("Save as");
        // Edit tab buttons
        MenuItem copy = new MenuItem("Copy Values");
        // About tab buttons
        MenuItem gitHub = new MenuItem("github");

        // add click handlers to controller
        saveAs.setOnAction(
                (ActionEvent event) -> {
                    controller.clickSaveAs();
                });

        copy.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCopy();
                });

        gitHub.setOnAction(
                (ActionEvent event) -> {
                    controller.clickGitHub();
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
