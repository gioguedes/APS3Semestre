module com.mycompany.graphics.generator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;  // Adicionado para javafx.embed.swing
    requires java.desktop;  // Adicionado para javax.imageio

    opens com.mycompany.graphics.generator to javafx.fxml;
    exports com.mycompany.graphics.generator;
}
