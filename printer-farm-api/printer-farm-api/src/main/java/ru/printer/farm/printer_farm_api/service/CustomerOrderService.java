package ru.printer.farm.printer_farm_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.printer.farm.printer_farm_api.model.CustomerOrder;
import ru.printer.farm.printer_farm_api.repository.CustomerOrderRepository;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private final CustomerOrderRepository orderRepository;

    public List<CustomerOrder> findAll() { return orderRepository.findAll(); }
    public CustomerOrder findById(Long id) { return orderRepository.findById(id).orElse(null); }
    public CustomerOrder save(CustomerOrder order) { return orderRepository.save(order); }
    public void deleteById(Long id) { orderRepository.deleteById(id); }

    @Transactional
    public CustomerOrder completeOrder(Long orderId) {
        CustomerOrder order = findById(orderId);
        if (order == null || !"IN_PROGRESS".equals(order.getStatus())) {
            throw new IllegalStateException("Заказ не найден или не находится в процессе выполнения.");
        }
        order.setStatus("COMPLETED");
        if (order.getTasks() != null) {
            order.getTasks().forEach(task -> task.setStatus("COMPLETED_WITH_ORDER"));
        }
        return save(order);
    }
}