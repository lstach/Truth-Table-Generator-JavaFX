package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class TableView implements FXComponent{

    private Model model;
    private Controller controller;


    public TableView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Parent render() {

        //creates the grid which holds the truth table
        GridPane gridPane = new GridPane();


        return null;
    }
}
