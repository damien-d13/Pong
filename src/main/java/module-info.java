module fr.damien {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens fr.damien to javafx.fxml;
    exports fr.damien;
}