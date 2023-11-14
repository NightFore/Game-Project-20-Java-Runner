module com.example.gameproject20javarunner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameproject20javarunner to javafx.fxml;
    exports com.example.gameproject20javarunner;
}