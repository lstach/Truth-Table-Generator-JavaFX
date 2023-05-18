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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
        formulaEntry.setPadding(new Insets(20, 500, 20, 10));

        formulaEntry.setPrefWidth( Integer.MAX_VALUE ); // makes textbox take up width of parent hBox (as much space as possible)

        formulaEntry.setStyle(
                "-fx-font-size: 18;" +

                        "-fx-font-color: white;"
        );

        //create the button that calculates once the formula is complete
        Button button = new Button();
        button.setText("Calculate");
        button.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCalculate(formulaEntry.getText());
                });
        button.setTranslateX(7);

        //TODO: the button width ain't working... it's cut off
        //button.setPrefWidth(200);
        button.setMinWidth(150);
        //button.setMaxWidth(200);

        button.setStyle(
                "-fx-font-size: 18;" +
                        "-fx-background-color: #555555;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bolder;"
        );


        Button help = new Button();
        help.setText("How to type?");
        help.setTranslateX(7);
        help.setTranslateY(4);
        help.setMinWidth(150);
        help.setOnAction(
                (ActionEvent event) -> {
                    controller.clickHelp();
                });
        help.setStyle(
                "-fx-background-color: gray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                        "-fx-font-weight: bolder;"
        );

        VBox nestedvBox = new VBox();
        nestedvBox.getChildren().add(button);
        nestedvBox.getChildren().add(help);

        //add UI elements to the hbox
        hBox.getChildren().add(formulaEntry);
        hBox.getChildren().add(nestedvBox);
        hBox.setStyle("-fx-background-color: #FFFFFF;");
        hBox.setPadding(new Insets(10, 10, 10, 10));

        Text errorMsg = new Text();
        errorMsg.setText("Hello! I'm an error!");
        errorMsg.setStyle(
                "-fx-font-size: 15;"
        );
        errorMsg.setFill(Color.RED);
        errorMsg.setVisible(true);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(errorMsg);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        return vBox;
    }
}
