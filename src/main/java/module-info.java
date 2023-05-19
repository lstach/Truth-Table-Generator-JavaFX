module com.example.truthtablegeneratorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.truthtablegeneratorjavafx to javafx.fxml;
    exports com.example.truthtablegeneratorjavafx;
    exports com.example.truthtablegeneratorjavafx.model;
    opens com.example.truthtablegeneratorjavafx.model to javafx.fxml;
    exports com.example.truthtablegeneratorjavafx.view;
    opens com.example.truthtablegeneratorjavafx.view to javafx.fxml;
    exports com.example.truthtablegeneratorjavafx.controller;
    opens com.example.truthtablegeneratorjavafx.controller to javafx.fxml;
}