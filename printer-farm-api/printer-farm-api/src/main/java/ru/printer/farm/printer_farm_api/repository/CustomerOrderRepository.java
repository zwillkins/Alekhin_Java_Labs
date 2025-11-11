package ru.printer.farm.printer_farm_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.printer.farm.printer_farm_api.model.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomerNameContainingIgnoreCase(String customerName);
    List<CustomerOrder> findByStatus(String status);
    @Query("SELECT co FROM CustomerOrder co WHERE co.dueDate < :currentDate AND co.status <> 'COMPLETED'")
    List<CustomerOrder> findOverdueOrders(LocalDate currentDate);
}