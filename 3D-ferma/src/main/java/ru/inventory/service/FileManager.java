package ru.inventory.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ru.inventory.domain.FilamentSpool;
import ru.inventory.domain.PETGSpool;
import ru.inventory.domain.PLASpool;
import ru.inventory.domain.Warehouse;

public class FileManager {
    private final ObjectMapper objectMapper;

    public FileManager() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void saveToJson(Warehouse warehouse, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), warehouse);
    }

    public Warehouse loadFromJson(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new Warehouse(); 
        }
        return objectMapper.readValue(file, Warehouse.class);
    }

    public void saveToText(Warehouse warehouse, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("type;id;color;weight\n"); 
            for (FilamentSpool spool : warehouse.getSpools()) {
                writer.write(String.format("%s;%d;%s;%d\n",
                    spool.getMaterialType(), spool.getId(), spool.getColor(), spool.getWeight()));
            }
        }
    }

    public Warehouse loadFromText(String filePath) throws IOException {
        Warehouse warehouse = new Warehouse();
        File file = new File(filePath);

        if (!file.exists() || Files.readAllLines(Paths.get(filePath)).size() <= 1) {
            return warehouse;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            if (parts.length < 4) continue; 

            String type = parts[0];
            long id = Long.parseLong(parts[1]); 
            String color = parts[2];
            int weight = Integer.parseInt(parts[3]);

            if ("PLA".equals(type)) {
                warehouse.addSpool(new PLASpool(id, color, weight, 210)); 
            } else if ("PETG".equals(type)) {
                warehouse.addSpool(new PETGSpool(id, color, weight, true)); 
            }
        }
        return warehouse;
    }
}