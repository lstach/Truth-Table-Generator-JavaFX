package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import com.example.truthtablegeneratorjavafx.model.Model;
import com.example.truthtablegeneratorjavafx.model.ModelObserver;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class BigMainView implements FXComponent, ModelObserver {

    private Model model;
    private Controller controller;
    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;
    //TODO: add private fields of layout containers

    public BigMainView(Model model, Controller controller, Stage stage){
        this.model = model;
        this.controller = controller;
        this.stage = stage;

        scene = new Scene(render(), 1000, 1000);
    }

    public Scene getScene(){
     return scene;
    }

    @Override
    public Parent render() {

        borderPane = new BorderPane();

        MenuView menuView = new MenuView(model, controller, stage);
        FormulaEntryView formulaEntryView = new FormulaEntryView(model, controller);
        VBox top = new VBox();

        top.getChildren().add(menuView.render());
        top.getChildren().add(formulaEntryView.render());

        TableView tableView = new TableView(model, controller);

        borderPane.setTop(top);
        borderPane.setCenter(tableView.render());



        return borderPane;
    }

    @Override
    public void update(Model model) {
        this.model = model;
        scene.setRoot(render());
    }
}
