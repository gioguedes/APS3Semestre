package com.mycompany.graphics.generator;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Classe Charts implementa Initializable para inicialização do controller
public class Charts implements Initializable {

    // Anotação @FXML para vincular os componentes do layout FXML
    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label btnLabel;

    // Método chamado quando o botão de sair é clicado
    @FXML
    private void btnClick7() {
        Platform.exit(); // Encerra a aplicação
    }

    // Método chamado quando o botão de salvar é clicado
    @FXML
    private void btnClick6() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Imagem");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));

        // Mostra a janela de salvar arquivo e obtém o arquivo escolhido
        File file = fileChooser.showSaveDialog(pieChart.getScene().getWindow());

        if (file != null) {
            try {
                // Cria imagens dos gráficos
                WritableImage pieChartImage = pieChart.snapshot(new SnapshotParameters(), null);
                WritableImage barChartImage = barChart.snapshot(new SnapshotParameters(), null);

                // Salva a imagem do PieChart
                File pieChartFile = new File(file.getParent(), "graficopizza_" + file.getName());
                ImageIO.write(SwingFXUtils.fromFXImage(pieChartImage, null), "png", pieChartFile);

                // Salva a imagem do BarChart
                File barChartFile = new File(file.getParent(), "graficocoluna_" + file.getName());
                ImageIO.write(SwingFXUtils.fromFXImage(barChartImage, null), "png", barChartFile);

            } catch (IOException e) {
                // Imprime a stack trace em caso de erro
                
            }
        }
    }

    // Método chamado durante a inicialização do controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtém as pontuações e o nome da empresa do DataStore
        int totalScore = DataStore.getInstance().getTotalScore();
        int totalScore1 = DataStore.getInstance().getTotalScore1();
        int totalScore2 = DataStore.getInstance().getTotalScore2();
        String empresaNome = DataStore.getInstance().getEmpresaNome();

        // Configura o Label com o nome da empresa
        btnLabel.setText(empresaNome);

        // Configura os dados do PieChart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Meio Ambiente", totalScore),
                        new PieChart.Data("Social", totalScore1),
                        new PieChart.Data("Governança", totalScore2));

        // Adiciona rótulos aos dados do PieChart
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty(), "%"
                        )
                )
        );

        // Adiciona os dados ao PieChart
        pieChart.getData().addAll(pieChartData);

        // Define o tamanho preferido do PieChart
        pieChart.setPrefSize(400, 400); // Ajuste os valores conforme necessário

        // Configura os dados do BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Meio Ambiente", totalScore));
        series.getData().add(new XYChart.Data<>("Social", totalScore1));
        series.getData().add(new XYChart.Data<>("Governança", totalScore2));

        // Adiciona os dados ao BarChart
        barChart.getData().add(series);

        // Remove a legenda do BarChart
        barChart.setLegendVisible(false);

        // Aplica o arquivo CSS para estilização dos gráficos
        try {
            String css = getClass().getResource("/styles.css").toExternalForm();
            pieChart.getStylesheets().add(css);
            barChart.getStylesheets().add(css);
        } catch (NullPointerException e) {
            System.err.println("Error loading CSS: " + e.getMessage());
        }
    }
}
