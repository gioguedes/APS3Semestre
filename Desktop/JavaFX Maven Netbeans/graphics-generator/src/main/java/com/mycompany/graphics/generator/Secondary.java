package com.mycompany.graphics.generator;

// Importações necessárias para o funcionamento do JavaFX e manipulação de eventos
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

// Classe Secondary que implementa a interface Initializable
public class Secondary implements Initializable {

    // Constantes de opções e pontuações
    private static final String[] CHOICES = {"Sim", "Não", "Não se aplica"};
    private static final int SCORE_SIM = 20;
    private static final int SCORE_NAO = 0;
    private static final int SCORE_NAO_SE_APLICA = 5;

    // Anotações FXML para vincular componentes da interface
    @FXML
    private ChoiceBox<String> btnChoiceBox1;
    @FXML
    private ChoiceBox<String> btnChoiceBox2;
    @FXML
    private ChoiceBox<String> btnChoiceBox3;
    @FXML
    private ChoiceBox<String> btnChoiceBox4;
    @FXML
    private ChoiceBox<String> btnChoiceBox5;

    @FXML
    private Label btnLabel1;

    // Método chamado automaticamente ao inicializar a interface
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Lista de ChoiceBoxes para iteração
        List<ChoiceBox<String>> choiceBoxes = Arrays.asList(
                btnChoiceBox1, btnChoiceBox2, btnChoiceBox3, btnChoiceBox4, btnChoiceBox5);

        // Adiciona as opções a cada ChoiceBox
        for (ChoiceBox<String> choiceBox : choiceBoxes) {
            choiceBox.getItems().addAll(CHOICES);
        }

        // Obtém o valor de empresaNome e define no Label
        String empresaNome = DataStore.getInstance().getEmpresaNome();
        btnLabel1.setText(empresaNome);
    }

    // Método chamado quando o botão é clicado
    @FXML
    protected void btnClick(ActionEvent event) {
        // Verifica se todas as ChoiceBoxes foram respondidas
        if (allChoiceBoxesAnswered()) {
            // Obtém as escolhas selecionadas
            List<String> selectedChoices = Arrays.asList(
                    btnChoiceBox1.getValue(),
                    btnChoiceBox2.getValue(),
                    btnChoiceBox3.getValue(),
                    btnChoiceBox4.getValue(),
                    btnChoiceBox5.getValue()
            );

            // Calcula a pontuação total com base nas escolhas
            int totalScore = calculateTotalScore(selectedChoices);

            // Armazena as escolhas e a pontuação no DataStore
            DataStore.getInstance().setSecondaryChoices(selectedChoices);
            DataStore.getInstance().setTotalScore(totalScore);

            // Abre a próxima janela
            openThirdWindow();
        } else {
            // Exibe um alerta de erro se nem todas as perguntas foram respondidas
            showErrorAlert("Erro ao prosseguir", "Responda todas as perguntas antes de prosseguir.");
        }
    }

    // Verifica se todas as ChoiceBoxes foram respondidas
    private boolean allChoiceBoxesAnswered() {
        return Arrays.asList(btnChoiceBox1, btnChoiceBox2, btnChoiceBox3, btnChoiceBox4, btnChoiceBox5)
                .stream().allMatch(choiceBox -> choiceBox.getValue() != null);
    }

    // Calcula a pontuação total com base nas escolhas
    private int calculateTotalScore(List<String> choices) {
        return choices.stream().mapToInt(this::getValueForChoice).sum();
    }

    // Retorna a pontuação correspondente a cada escolha
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

    // Abre a terceira janela da aplicação
    private void openThirdWindow() {
        try {
            // Fecha a janela atual
            Stage currentStage = (Stage) btnChoiceBox1.getScene().getWindow();
            currentStage.close();

            // Configura e exibe a nova janela
            Stage newStage = new Stage();
            newStage.setTitle("Diagnóstico Metas ESG");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/graphics/generator/third.fxml"));
            newStage.setScene(new Scene(fxmlLoader.load()));
            Image icon = new Image(getClass().getResourceAsStream("/com/mycompany/graphics/generator/icon.png"));
            newStage.getIcons().add(icon);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Exibe um alerta de erro
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
