package com.example.truthtablegeneratorjavafx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import com.example.truthtablegeneratorjavafx.model.*;
import com.example.truthtablegeneratorjavafx.view.*;
import com.example.truthtablegeneratorjavafx.controller.*;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);

        BigMainView bigPapa = new BigMainView(model, controller, stage);

        model.addObserver(bigPapa);

        stage.setScene(bigPapa.getScene());

        stage.setTitle("Truth Table Generator");

        String imageName = "/truth_table_gen_javafx_icon.png";
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream(imageName)));

        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}