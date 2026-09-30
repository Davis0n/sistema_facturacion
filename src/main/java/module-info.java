module ni.edu.uam.sistemafacturacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires static lombok;


    opens ni.edu.uam.sistemafacturacion.controller to javafx.fxml;
    exports ni.edu.uam.sistemafacturacion;
}

