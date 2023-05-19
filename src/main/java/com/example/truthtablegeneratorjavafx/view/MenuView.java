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

        SVGPath saveIcon = new SVGPath();
        SVGPath copyIcon = new SVGPath();
        SVGPath githubIcon = new SVGPath();

        // get SVG path from the SVG
        // it's cool because it isn't actually in our program. SVGPath decodes this on its own into the image we want!
        saveIcon.setContent("M433.1 129.1l-83.9-83.9C342.3 38.32 327.1 32 316.1 32H64C28.65 32 0 60.65 0 96v320c0 35.35 28.65 64 64 64h320c35.35 0 64-28.65 64-64V163.9C448 152.9 441.7 137.7 433.1 129.1zM224 416c-35.34 0-64-28.66-64-64s28.66-64 64-64s64 28.66 64 64S259.3 416 224 416zM320 208C320 216.8 312.8 224 304 224h-224C71.16 224 64 216.8 64 208v-96C64 103.2 71.16 96 80 96h224C312.8 96 320 103.2 320 112V208z");
        copyIcon.setContent("M53.9791489,9.1429005H50.010849c-0.0826988,0-0.1562004,0.0283995-0.2331009,0.0469999V5.0228" +
                "C49.7777481,2.253,47.4731483,0,44.6398468,0h-34.422596C7.3839517,0,5.0793519,2.253,5.0793519,5.0228v46.8432999" +
                "c0,2.7697983,2.3045998,5.0228004,5.1378999,5.0228004h6.0367002v2.2678986C16.253952,61.8274002,18.4702511,64,21.1954517,64" +
                "h32.783699c2.7252007,0,4.9414978-2.1725998,4.9414978-4.8432007V13.9861002" +
                "C58.9206467,11.3155003,56.7043495,9.1429005,53.9791489,9.1429005z M7.1110516,51.8661003V5.022" +
                "c0-1.6487999,1.3938999-2.9909999,3.1062002-2.9909999h34.422596c1.7123032,0,3.1062012,1.3422,3.1062012,2.9909999v46.8432999" +
                "c0,1.6487999-1.393898,2.9911003-3.1062012,2.9911003h-34.422596C8.5049515,54.8572006,7.1110516,53.5149002,7.1110516,51.8661003z" +
                " M56.8888474,59.1567993c0,1.550602-1.3055,2.8115005-2.9096985,2.8115005h-32.783699" +
                "c-1.6042004,0-2.9097996-1.2608986-2.9097996-2.8115005v-2.2678986h26.3541946" +
                "c2.8333015,0,5.1379013-2.2530022,5.1379013-5.0228004V11.1275997c0.0769005,0.0186005,0.1504021,0.0469999,0.2331009,0.0469999" +
                "h3.9682999c1.6041985,0,2.9096985,1.2609005,2.9096985,2.8115005V59.1567993z");
        githubIcon.setContent("M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z");


        // File tab buttons
        MenuItem saveAs = new MenuItem("Save as .csv", saveIcon);
        saveAs.setStyle("-fx-max-width: 10;");
        // Edit tab buttons
        MenuItem copy = new MenuItem("Copy Values", copyIcon);
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
