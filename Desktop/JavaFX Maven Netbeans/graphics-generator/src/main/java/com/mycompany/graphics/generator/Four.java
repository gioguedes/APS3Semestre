package com.mycompany.graphics.generator;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Four implements Initializable {

    private static final String[] CHOICES = {"Sim", "Não", "Não se aplica"};
    private static final int SCORE_SIM = 20;
    private static final int SCORE_NAO = 0;
    private static final int SCORE_NAO_SE_APLICA = 5;

    @FXML
    private ChoiceBox<String> btnChoiceBox11;
    @FXML
    private ChoiceBox<String> btnChoiceBox12;
    @FXML
    private ChoiceBox<String> btnChoiceBox13;
    @FXML
    private ChoiceBox<String> btnChoiceBox14;
    @FXML
    private ChoiceBox<String> btnChoiceBox15;
    @FXML
    private Label btnLabel3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ChoiceBox<String>> choiceBoxes = Arrays.asList(
                btnChoiceBox11, btnChoiceBox12, btnChoiceBox13, btnChoiceBox14, btnChoiceBox15);

        for (ChoiceBox<String> choiceBox : choiceBoxes) {
            choiceBox.getItems().addAll(CHOICES);
        }

        String empresaNome = DataStore.getInstance().getEmpresaNome();
        btnLabel3.setText(empresaNome);
    }

    @FXML
    protected void btnClick4(ActionEvent event) {
        if (allChoiceBoxesAnswered()) {
            List<String> selectedChoices = Arrays.asList(
                    btnChoiceBox11.getValue(),
                    btnChoiceBox12.getValue(),
                    btnChoiceBox13.getValue(),
                    btnChoiceBox14.getValue(),
                    btnChoiceBox15.getValue()
            );

            int fourTotalScore = calculateTotalScore(selectedChoices);

            DataStore.getInstance().setFourChoices(selectedChoices);
            DataStore.getInstance().setTotalScore2(fourTotalScore);

            saveAllResponsesToCSV();
            openChartsWindow();
        } else {
            showErrorAlert("Erro ao prosseguir", "Responda todas as perguntas antes de prosseguir.");
        }
    }

    private boolean allChoiceBoxesAnswered() {
        return Arrays.asList(btnChoiceBox11, btnChoiceBox12, btnChoiceBox13, btnChoiceBox14, btnChoiceBox15)
                .stream().allMatch(choiceBox -> choiceBox.getValue() != null);
    }

    private int calculateTotalScore(List<String> choices) {
        return choices.stream().mapToInt(this::getValueForChoice).sum();
    }

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

    private void saveAllResponsesToCSV() {
        String empresaNome = DataStore.getInstance().getEmpresaNome();
        List<String> allResponses = new ArrayList<>();
        allResponses.add(empresaNome);
        allResponses.addAll(DataStore.getInstance().getSecondaryChoices());
        allResponses.addAll(DataStore.getInstance().getThirdChoices());
        allResponses.addAll(DataStore.getInstance().getFourChoices());

        String fileName = empresaNome.replaceAll("\\s+", "_") + "_respostas.csv";

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeDataToCSV(fileName, allResponses);
    }

    private void openChartsWindow() {
        try {
            Stage currentStage = (Stage) btnChoiceBox11.getScene().getWindow();
            currentStage.close();

            Stage newStage = new Stage();
            newStage.setTitle("Diagnóstico Metas ESG");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/graphics/generator/charts.fxml"));
            newStage.setScene(new Scene(fxmlLoader.load()));
            Image icon = new Image(getClass().getResourceAsStream("/com/mycompany/graphics/generator/icon.png"));
            newStage.getIcons().add(icon);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a janela de gráficos: " + e.getMessage());
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
