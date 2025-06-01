module fr.ilannstefanovitch.saebomberman {
    requires javafx.controls;
    requires javafx.fxml;

    exports fr.ilannstefanovitch.saebomberman;
    opens fr.ilannstefanovitch.saebomberman to javafx.fxml;
}