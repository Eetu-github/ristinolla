module com.example.ristinolla {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ristinolla to javafx.fxml;
    exports com.example.ristinolla;
}