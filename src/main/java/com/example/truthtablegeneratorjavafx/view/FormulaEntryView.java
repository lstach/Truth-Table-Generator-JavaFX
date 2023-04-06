package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class FormulaEntryView implements FXComponent{

    private Model model;
    private Controller controller;

    public FormulaEntryView(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Parent render() {

        HBox hBox = new HBox();

        //create the text field for entering the formula
        TextField formulaEntry = new TextField();
        formulaEntry.setMinWidth(250);
        formulaEntry.setPrefHeight(50);
        formulaEntry.setPrefWidth(1000);

        formulaEntry.setMinHeight(50);
        formulaEntry.setFont(Font.font("Consolas", 20));

        //create the button that calculates once the formula is complete
        Button button = new Button();
        button.setText("Generate");
        button.setMinWidth(100);
        button.setMaxWidth(100);
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        button.setLineSpacing(30);

        formulaEntry.setFont(Font.font("Consolas", 20));

        formulaEntry.setStyle("-fx-border-color: black");

        button.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCalculate(formulaEntry.getText());
                });

        button.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 8,7,6;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

        //add UI elements to the hbox
        hBox.getChildren().add(formulaEntry);
        hBox.getChildren().add(button);

        //hBox.setAlignment(Pos.BASELINE_CENTER);

        hBox.setAlignment(Pos.BOTTOM_CENTER);

        hBox.setSpacing(20);

        return hBox;
    }
}
