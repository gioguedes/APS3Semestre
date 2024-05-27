package com.mycompany.graphics.generator;

import java.util.ArrayList;
import java.util.List;

// Singleton DataStore para armazenar dados compartilhados
public class DataStore {

    // Instância única da classe (volatile para garantir visibilidade entre threads)
    private static volatile DataStore instance;

    // Variáveis para armazenar os dados da aplicação
    private String empresaNome; // Nome da empresa
    private List<String> secondaryChoices; // Escolhas da segunda etapa
    private List<String> thirdChoices; // Escolhas da terceira etapa
    private List<String> fourChoices; // Escolhas da quarta etapa
    private int totalScore; // Pontuação total meio ambiente
    private int totalScore1; // Pontuação total social
    private int totalScore2; // Pontuação total governança

    // Construtor privado para evitar instância direta
    private DataStore() {
        // Inicialização das listas e pontuações
        this.secondaryChoices = new ArrayList<>();
        this.thirdChoices = new ArrayList<>();
        this.fourChoices = new ArrayList<>();
        this.totalScore = 0;
        this.totalScore1 = 0;
        this.totalScore2 = 0;
    }

    // Método para obter a instância única (Singleton)
    public static DataStore getInstance() {
        if (instance == null) {
            synchronized (DataStore.class) {
                if (instance == null) {
                    instance = new DataStore();
                }
            }
        }
        return instance;
    }

    // Getters e setters para acessar e modificar os dados armazenados
    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public List<String> getSecondaryChoices() {
        return secondaryChoices;
    }

    public void setSecondaryChoices(List<String> secondaryChoices) {
        this.secondaryChoices = secondaryChoices;
    }

    public List<String> getThirdChoices() {
        return thirdChoices;
    }

    public void setThirdChoices(List<String> thirdChoices) {
        this.thirdChoices = thirdChoices;
    }

    public List<String> getFourChoices() {
        return fourChoices;
    }

    public void setFourChoices(List<String> fourChoices) {
        this.fourChoices = fourChoices;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalScore1() {
        return totalScore1;
    }

    public void setTotalScore1(int totalScore1) {
        this.totalScore1 = totalScore1;
    }

    public int getTotalScore2() {
        return totalScore2;
    }

    public void setTotalScore2(int totalScore2) {
        this.totalScore2 = totalScore2;
    }
}
