package ru.printer.farm.printer_farm_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PrinterFarmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrinterFarmApiApplication.class, args);
	}

}
