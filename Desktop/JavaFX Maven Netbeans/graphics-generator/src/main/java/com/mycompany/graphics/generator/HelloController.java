package com.mycompany.graphics.generator;

// Importações necessárias para o funcionamento do JavaFX e controle de interface
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

// Classe HelloController que gerencia a interação com a interface do usuário
public class  HelloController {

    @FXML
    private TextField textField; // Campo de texto para entrada do nome da empresa

    private String empresaNome; // Armazena o nome da empresa

    // Método de inicialização chamado automaticamente após carregar o FXML
    @FXML
    public void initialize() {
        // Adiciona um ChangeListener ao textField para limitar a entrada a 30 caracteres
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                textField.setText(oldValue);
                LimiteCaracteres();
            }
        });
    }

    // Método chamado ao clicar no botão
    @FXML
    private void btnClique() {
        String input = textField.getText();
        // Verifica se o campo de texto está vazio ou contém apenas espaços
        if (input == null || input.trim().isEmpty()) {
            showErrorAlert("Erro ao prosseguir", "Digite o nome da empresa corretamente.");
        } else {
            empresaNome = input.trim();
            // Salva o nome da empresa no DataStore
            DataStore.getInstance().setEmpresaNome(empresaNome);

            // Abre a janela secundária
            openSecondaryWindow();
        }
    }

    // Método que exibe um alerta de erro se o limite de caracteres for excedido
    private void LimiteCaracteres() {
        showErrorAlert("Limite de Caracteres Excedido", "O nome da empresa não pode ter mais que 30 caracteres.");
    }

    // Método chamado ao clicar no botão de fechar
    @FXML
    private void btnClose() {
        // Fecha a aplicação
        Stage stage = (Stage) textField.getScene().getWindow();
        stage.close();
    }

    // Método que abre a janela secundária
    private void openSecondaryWindow() {
        try {
            Stage currentStage = (Stage) textField.getScene().getWindow();
            // Carrega o layout da janela secundária a partir do FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/graphics/generator/secondary.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            newStage.setScene(scene);
            // Define o ícone da nova janela
            Image icon = new Image(getClass().getResourceAsStream("/com/mycompany/graphics/generator/icon.png"));
            newStage.getIcons().add(icon);
            newStage.setTitle("Diagnostic Metas ESG");
            newStage.show();
            currentStage.close(); // Fecha a janela atual
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que exibe um alerta de erro
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
