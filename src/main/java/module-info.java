module com.example.paintjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.paintjavafx to javafx.fxml;
    exports com.example.paintjavafx;
}