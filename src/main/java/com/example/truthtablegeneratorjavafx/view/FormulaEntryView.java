package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FormulaEntryView implements FXComponent{

    private Model model;
    private Controller controller;

    public FormulaEntryView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;



    }

    @Override
    public Parent render() {

        HBox hBox = new HBox();

        //create the text field for entering the formula
        TextField formulaEntry = new TextField();
        formulaEntry.setMinWidth(250);
        formulaEntry.setMaxWidth(250);
        formulaEntry.setPrefWidth(250);

        //create the button that calculates once the formula is complete
        Button button = new Button();
        button.setText("Calculate");
        button.setMinWidth(100);
        button.setMaxWidth(100);
        button.setPrefWidth(100);
        button.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCalculate(formulaEntry.getText());
                });

        //add UI elements to the hbox
        hBox.getChildren().add(formulaEntry);
        hBox.getChildren().add(button);

        return hBox;
    }
}
