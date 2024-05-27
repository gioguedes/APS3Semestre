package com.mycompany.graphics.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    public void writeDataToCSV(String fileName, List<String> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String entry : data) {
                writer.append(entry).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever dados no arquivo CSV: " + e.getMessage());
        }
    }
}