package com.example.truthtablegeneratorjavafx.view;

import com.example.truthtablegeneratorjavafx.HelloApplication;
import com.example.truthtablegeneratorjavafx.controller.Controller;
import com.example.truthtablegeneratorjavafx.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FormulaEntryView implements FXComponent{

    private Model model;
    private Controller controller;

    private final String calculateIdle;
    private final String calculateHover;
    private final String calculateClick;

    private final String helpIdle;
    private final String helpHover;
    private final String helpClick;

    private HBox hBox;
    private VBox vBox;
    private Stage helpWindow;

    public FormulaEntryView(Model model, Controller controller, Stage helpWindow){
        this.model = model;
        this.controller = controller;
        this.helpWindow = helpWindow;

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
                "-fx-background-color: green;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpIdle = "-fx-background-color: gray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpHover = "-fx-background-color: lightGray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        this.helpClick = "-fx-background-color: lightGray;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bolder;";

        hBox = new HBox();
        vBox = new VBox();
        initialize(); // this is to initialize elements that don't need to be updated, so that they change only once (upon starting).
    }

    public void initialize(){

    }

    @Override
    public Parent render() {

        //create the text field for entering the formula
        TextField formulaEntry = new TextField();
        formulaEntry.setPadding(new Insets(20, 0, 20, 10));

        formulaEntry.setPrefWidth(Integer.MAX_VALUE); // makes textbox take up width of parent hBox (as much space as possible)

        formulaEntry.setStyle(
                "-fx-font-size: 18;" +

                        "-fx-font-color: white;"
        );
        formulaEntry.setText(model.getUneditedFormula());
        formulaEntry.positionCaret(model.getUneditedFormula().length());

        //create the calculate that calculates once the formula is complete
        Button calculate = new Button();
        calculate.setText("Calculate");
        calculate.setTranslateX(7);

        int numOperands = model.getVariables().size();

        /*  the truth table is hard to read if it gets too wide as the window moves.
            but we can't give it a fixed max size, as this would make it too small for more variables.
            Thus, we give each column a max width + 1 for the formula column.
            same with minimum.
         */

        calculate.setMinWidth(150);
        calculate.setMaxWidth((numOperands + 1) * 60);

        calculate.setOnAction(
                (ActionEvent event) -> {
                    controller.clickCalculate(formulaEntry.getText());
                });

        calculate.setStyle(calculateIdle);
        calculate.setOnMouseEntered(e -> calculate.setStyle(calculateHover));
        calculate.setOnMouseExited(e -> calculate.setStyle(calculateIdle));

        Button help = new Button();
        help.setText("How to type?");
        help.setTranslateX(7);
        help.setTranslateY(4);
        help.setMinWidth(150);
        help.setOnAction(
                (ActionEvent event) -> {
                    if (!helpWindow.isShowing()){
                        helpWindow.show();
                    }
                });

        help.setStyle(helpIdle);
        help.setOnMouseEntered(e -> help.setStyle(helpHover));
        help.setOnMouseExited(e -> help.setStyle(helpIdle));

        VBox nestedvBox = new VBox();
        nestedvBox.getChildren().add(calculate);
        nestedvBox.getChildren().add(help);

        //add UI elements to the hbox
        hBox.getChildren().add(formulaEntry);
        hBox.getChildren().add(nestedvBox);

        hBox.setPadding(new Insets(10, 10, 10, 0));

        vBox.getChildren().add(hBox);

        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #FFFFFF;");

        if (!model.getErrorMessage().equals("")) { // only displays the error message if there is an error in parsing.
            Text errorMsg = new Text();
            errorMsg.setStyle(
                    "-fx-font-size: 15;"
            );
            errorMsg.setText(model.getErrorMessage());
            errorMsg.setFill(Color.RED);

            vBox.getChildren().add(errorMsg);
        }

        return vBox;
    }
}
