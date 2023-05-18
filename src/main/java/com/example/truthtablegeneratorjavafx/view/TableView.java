package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class TableView implements FXComponent{

    private Model model;
    private Controller controller;

    private ArrayList<String>[] truthTable;
    private ArrayList<String> variables;

    public TableView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        this.truthTable = model.getTruthTable();
        variables = model.getVariables();
    }

    @Override
    public Parent render() {

        //creates the grid which holds the truth table
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);

        //add each variable as a header to the truth table
        for (int i = 0; i < variables.size(); i++){
            Label label = new Label();
            label.setText(variables.get(i));
            label.setStyle(
                    "-fx-font-size: 30;" +
                    "-fx-font-weight: bold;"
            );

            gridPane.setHalignment(label, HPos.CENTER);
            gridPane.setValignment(label, VPos.CENTER);

            gridPane.add(label, i, 0); //column-major order for some reason. Not a fan.
        }

        Label label = new Label();
        label.setText(model.getFormula());
        label.setStyle(
                "-fx-font-size: 30;" +
                "-fx-font-weight: bold;"
        );

        gridPane.add(label, variables.size(), 0);

        for (int row = 0; row < truthTable.length; row++){
            for (int col = 0; col < truthTable[row].size(); col++){
                Label boolValueLabel = new Label();
                boolValueLabel.setText(truthTable[row].get(col));
                boolValueLabel.setStyle(

                        "-fx-font-size: 20;"

                );
                gridPane.add(boolValueLabel, col, row + 1); //column-major order for some reason. Not a fan.
                gridPane.setHalignment(boolValueLabel, HPos.CENTER);
                gridPane.setValignment(label, VPos.CENTER);
            }
        }

        gridPane.setHgap(50);
        gridPane.setVgap(50);

        return gridPane;
    }
}
