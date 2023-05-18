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

        scene = new Scene(render(), 1000, 1000);
    }

    public Scene getScene(){
     return scene;
    }

    @Override
    public Parent render() {
        borderPane = new BorderPane();

        FormulaEntryView formulaEntryView = new FormulaEntryView(model, controller);

        TableView tableView = new TableView(model, controller);

        borderPane.setTop(formulaEntryView.render());

        borderPane.setCenter(tableView.render());

        return borderPane;
    }

    @Override
    public void update(Model model) {
        this.model = model;
        scene.setRoot(render());
    }
}
