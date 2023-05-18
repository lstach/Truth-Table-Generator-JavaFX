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

    private final String calculateIdle;
    private final String calculateHover;
    private final String calculateClick;

    private final String helpIdle;
    private final String helpHover;
    private final String helpClick;


    public FormulaEntryView(Model model, Controller controller){
        this.model = model;
        this.controller = controller;

        this.calculateIdle = "-fx-font-size: 18;" +
                "-fx-background-color: #555555;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.calculateHover = "-fx-font-size: 18;" +
                "-fx-background-color: #abb3b2;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.calculateClick = "-fx-font-size: 18;" +
                "-fx-background-color: #1c911e;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpIdle = "-fx-background-color: gray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpHover = "-fx-background-color: gray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpClick = "-fx-background-color: gray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";
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

        //create the calculate that calculates once the formula is complete
        Button calculate = new Button();
        calculate.setText("Calculate");
        calculate.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCalculate(formulaEntry.getText());
                });
        calculate.setTranslateX(7);

        //TODO: the calculate width ain't working... it's cut off
        //calculate.setPrefWidth(200);
        calculate.setMinWidth(150);
        //calculate.setMaxWidth(200);

        calculate.setStyle(
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

        help.setStyle(helpIdle);
        help.setOnMouseEntered(e -> help.setStyle(helpHover));
        help.setOnMouseExited(e -> help.setStyle(helpIdle));
        help.setOnMouseClicked(e -> help.setStyle(helpClick));

        VBox nestedvBox = new VBox();
        nestedvBox.getChildren().add(calculate);
        nestedvBox.getChildren().add(help);

        //add UI elements to the hbox
        hBox.getChildren().add(formulaEntry);
        hBox.getChildren().add(nestedvBox);

        hBox.setPadding(new Insets(10, 10, 10, 0));

        Text errorMsg = new Text();
        errorMsg.setText("Hello! I'm an error!");
        errorMsg.setStyle(
                "-fx-font-size: 15;"
        );
        errorMsg.setFill(Color.RED);
        errorMsg.setVisible(true);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);

        if (true) { //TODO: change this to condition given by model that adds an error message if there is one.
            vBox.getChildren().add(errorMsg);
        }
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #FFFFFF;");

        return vBox;
    }
}
