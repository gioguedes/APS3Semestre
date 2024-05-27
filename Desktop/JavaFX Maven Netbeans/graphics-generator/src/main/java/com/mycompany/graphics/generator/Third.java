package com.mycompany.graphics.generator;

// Importações necessárias para o funcionamento do JavaFX e controle de interface
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

// Classe Third que implementa Initializable para inicializar a interface do usuário
public class Third implements Initializable {

    // Constantes de escolha e pontuação
    private static final String[] CHOICES = {"Sim", "Não", "Não se aplica"};
    private static final int SCORE_SIM = 20;
    private static final int SCORE_NAO = 0;
    private static final int SCORE_NAO_SE_APLICA = 5;

    // Referências para os componentes da interface
    @FXML
    private ChoiceBox<String> btnChoiceBox6;
    @FXML
    private ChoiceBox<String> btnChoiceBox7;
    @FXML
    private ChoiceBox<String> btnChoiceBox8;
    @FXML
    private ChoiceBox<String> btnChoiceBox9;
    @FXML
    private ChoiceBox<String> btnChoiceBox10;

    @FXML
    private Label btnLabel2;

    // Método chamado automaticamente após carregar o FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Adiciona as opções de escolha aos ChoiceBoxes
        List<ChoiceBox<String>> choiceBoxes = Arrays.asList(
                btnChoiceBox6, btnChoiceBox7, btnChoiceBox8, btnChoiceBox9, btnChoiceBox10);

        for (ChoiceBox<String> choiceBox : choiceBoxes) {
            choiceBox.getItems().addAll(CHOICES);
        }

        // Obtém o valor de empresaNome e define no Label
        String empresaNome = DataStore.getInstance().getEmpresaNome();
        btnLabel2.setText(empresaNome);
    }

    // Método chamado ao clicar no botão para prosseguir
    @FXML
    protected void btnClick3(ActionEvent event) {
        if (allChoiceBoxesAnswered()) {
            List<String> selectedChoices = Arrays.asList(
                    btnChoiceBox6.getValue(),
                    btnChoiceBox7.getValue(),
                    btnChoiceBox8.getValue(),
                    btnChoiceBox9.getValue(),
                    btnChoiceBox10.getValue()
            );

            int thirdTotalScore = calculateTotalScore(selectedChoices);

            // Armazena as escolhas e a pontuação total
            DataStore.getInstance().setThirdChoices(selectedChoices);
            DataStore.getInstance().setTotalScore1(thirdTotalScore);

            // Abre a próxima janela
            openFourthWindow();
        } else {
            // Exibe um alerta de erro se nem todas as ChoiceBoxes foram respondidas
            showErrorAlert("Erro ao prosseguir", "Responda todas as perguntas antes de prosseguir.");
        }
    }

    // Método para verificar se todas as ChoiceBoxes foram respondidas
    private boolean allChoiceBoxesAnswered() {
        return Arrays.asList(btnChoiceBox6, btnChoiceBox7, btnChoiceBox8, btnChoiceBox9, btnChoiceBox10)
                .stream().allMatch(choiceBox -> choiceBox.getValue() != null);
    }

    // Método para calcular a pontuação total com base nas escolhas
    private int calculateTotalScore(List<String> choices) {
        return choices.stream().mapToInt(this::getValueForChoice).sum();
    }

    // Método para obter a pontuação correspondente a uma escolha
    private int getValueForChoice(String choice) {
        switch (choice) {
            case "Sim":
                return SCORE_SIM;
            case "Não":
                return SCORE_NAO;
            case "Não se aplica":
                return SCORE_NAO_SE_APLICA;
            default:
                return 0;
        }
    }

    // Método para abrir a próxima janela
    private void openFourthWindow() {
        try {
            // Fecha a janela atual e abre a próxima
            Stage currentStage = (Stage) btnChoiceBox6.getScene().getWindow();
            currentStage.close();

            Stage newStage = new Stage();
            newStage.setTitle("Diagnóstico Metas ESG");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/graphics/generator/four.fxml"));
            newStage.setScene(new Scene(fxmlLoader.load()));
            Image icon = new Image(getClass().getResourceAsStream("/com/mycompany/graphics/generator/icon.png"));
            newStage.getIcons().add(icon);
            newStage.show();
        } catch (IOException e) {
        }
    }

    // Método para exibir um alerta de erro
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
