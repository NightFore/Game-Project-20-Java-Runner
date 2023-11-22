module com.example.gameproject20javarunner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameproject20javarunner to javafx.fxml;
    exports com.example.gameproject20javarunner;
    exports com.example.gameproject20javarunner.model;
    opens com.example.gameproject20javarunner.model to javafx.fxml;
    exports com.example.gameproject20javarunner.manager;
    opens com.example.gameproject20javarunner.manager to javafx.fxml;
    exports com.example.gameproject20javarunner.entity;
    opens com.example.gameproject20javarunner.entity to javafx.fxml;
    exports com.example.gameproject20javarunner.view;
    opens com.example.gameproject20javarunner.view to javafx.fxml;
    exports com.example.gameproject20javarunner.application;
    opens com.example.gameproject20javarunner.application to javafx.fxml;
    exports com.example.gameproject20javarunner.controller;
    opens com.example.gameproject20javarunner.controller to javafx.fxml;
}