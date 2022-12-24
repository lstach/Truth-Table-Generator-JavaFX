package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import com.example.truthtablegeneratorjavafx.model.Model;
import com.example.truthtablegeneratorjavafx.model.ModelObserver;

public class BigMainView implements FXComponent, ModelObserver {

    private Model model;
    private Controller controller;
    private Scene scene;
    private BorderPane borderPane;
    //TODO: add private fields of layout containers

    public BigMainView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;

        scene = new Scene(render(), 500, 500);
    }


    public Scene getScene(){
     return scene;
    }
    @Override
    public Parent render() {
        borderPane = new BorderPane();

        Label testLabel = new Label();
        testLabel.setText("Hi hello ima test label hey!");
        borderPane.setTop(testLabel);
        return borderPane;
    }

    @Override
    public void update(Model model) {
        this.model = model;
        scene.setRoot(render());
    }
}
