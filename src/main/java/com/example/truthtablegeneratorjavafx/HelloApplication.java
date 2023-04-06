package com.example.truthtablegeneratorjavafx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        BigMainView bigPapa = new BigMainView(model, controller);

        model.addObserver(bigPapa);

        stage.setScene(bigPapa.getScene());

        stage.setTitle("Hi there hello!");

        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}