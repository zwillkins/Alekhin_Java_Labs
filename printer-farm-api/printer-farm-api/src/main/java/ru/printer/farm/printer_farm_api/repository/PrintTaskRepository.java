package ru.printer.farm.printer_farm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.printer.farm.printer_farm_api.model.PrintTask;

@Repository
public interface PrintTaskRepository extends JpaRepository<PrintTask, Long> {}