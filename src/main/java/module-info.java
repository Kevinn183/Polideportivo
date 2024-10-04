module com.example.projectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;

    opens com.example.projectofinal to javafx.fxml;
    exports com.example.projectofinal;
    exports com.example.projectofinal.Controladores;
    opens com.example.projectofinal.Controladores to javafx.fxml;
}