package com.mycompany.graphics.generator;



// Importação das bibliotecas necessárias do JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

// Classe principal que estende a classe Application do JavaFX
public class HelloApplication extends Application {

    // Método start() é o ponto de entrada para o código JavaFX
    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o arquivo FXML que define a interface do usuário
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/mycompany/graphics/generator/hello-view.fxml"));

        // Cria uma cena com o layout carregado do arquivo FXML e define seu tamanho
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Define o título da janela do aplicativo
        stage.setTitle("Diagnóstico Metas ESG");

        // Define a cena na janela do stage
        stage.setScene(scene);

        // Carrega um ícone para a janela do aplicativo
        Image icon = new Image(getClass().getResourceAsStream("/com/mycompany/graphics/generator/icon.png"));
        stage.getIcons().add(icon);

        // Exibe a janela do aplicativo
        stage.show();
    }

    // Método main() que é o ponto de entrada da aplicação
    public static void main(String[] args) {
        // Chama o método launch() para iniciar a aplicação JavaFX
        launch(args);
    }
}
