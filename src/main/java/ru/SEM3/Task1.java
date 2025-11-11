package ru.SEM3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task1{

    public static void main(String[] args) throws InterruptedException {

        createTestFiles();

        List<String> filenames = List.of("temp1.txt", "temp2.txt", "temp3.txt");
        Map<String, FileAnalysisResult> results = new ConcurrentHashMap<>();
        List<Thread> threads = new ArrayList<>();

        for (String filename : filenames) {
            FileAnalyzerTask task = new FileAnalyzerTask(filename, results);
            Thread thread = new Thread(task, "Analyzer-" + filename); 
            threads.add(thread);
            thread.start(); 
        }


        for (Thread thread : threads) {
            thread.join();
        }
        results.forEach((filename, result) -> System.out.println(filename + " -> " + result));
    }

    private static void createTestFiles() {
        try (FileWriter writer1 = new FileWriter("temp1.txt")) {
            writer1.write("Иванини Горянини,Dev,800000\n");
            writer1.write("Шма Смит,HR,95000\n");
        } catch (IOException e) { e.printStackTrace(); }

        try (FileWriter writer2 = new FileWriter("temp2.txt")) {
            writer2.write("Сем Ён,QA,75000\n");
        } catch (IOException e) { e.printStackTrace(); }

        try (FileWriter writer3 = new FileWriter("temp3.txt")) {
            writer3.write("Альберт Епштейн,Designer,85000\n");
            writer3.write("Айлара Анаитн,Dev,110000\n");
            writer3.write("Анаит Айларн,Manager,120000\n");
        } catch (IOException e) { e.printStackTrace(); }
    }

    static class FileAnalysisResult {
        long totalSalary = 0;
        int employeeCount = 0;

        @Override
        public String toString() {
            return "Сотрудников: " + employeeCount + ", общая зарплата: " + totalSalary;
        }
    }

    static class FileAnalyzerTask implements Runnable {
        private final String filename;
        private final Map<String, FileAnalysisResult> resultsMap;

        public FileAnalyzerTask(String filename, Map<String, FileAnalysisResult> resultsMap) {
            this.filename = filename;
            this.resultsMap = resultsMap;
        }

        @Override
        public void run() {
            System.out.println("Поток '" + Thread.currentThread().getName() + "' начал анализ файла: " + filename);
            FileAnalysisResult result = new FileAnalysisResult();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        result.totalSalary += Long.parseLong(parts[2].trim());
                        result.employeeCount++;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Ошибка" + filename + ": " + e.getMessage());
            }
            resultsMap.put(filename, result);
            System.out.println("Поток '" + Thread.currentThread().getName() + "' закончил анализ файла: " + filename);
        }
    }
}