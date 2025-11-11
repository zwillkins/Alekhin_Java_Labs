package ru.printer.farm.printer_farm_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.printer.farm.printer_farm_api.dto.CreateCustomerOrderRequest;
import ru.printer.farm.printer_farm_api.dto.CustomerOrderDto;
import ru.printer.farm.printer_farm_api.dto.PrintTaskDto;
import ru.printer.farm.printer_farm_api.model.CustomerOrder;
import ru.printer.farm.printer_farm_api.service.CustomerOrderService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Customer Orders", description = "API для управления заказами клиентов")
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    @GetMapping
    public List<CustomerOrderDto> getAllOrders() {
        return orderService.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public CustomerOrderDto createOrder(@RequestBody CreateCustomerOrderRequest requestDto) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(requestDto.getCustomerName());
        order.setDueDate(requestDto.getDueDate());
        order.setStatus(requestDto.getStatus());
        return convertToDto(orderService.save(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderDto> getOrderById(@PathVariable Long id) {
        CustomerOrder order = orderService.findById(id);
        return order != null ? ResponseEntity.ok(convertToDto(order)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerOrderDto> updateOrder(@PathVariable Long id, @RequestBody CreateCustomerOrderRequest requestDto) {
        CustomerOrder existingOrder = orderService.findById(id);
        if (existingOrder == null) return ResponseEntity.notFound().build();
        existingOrder.setCustomerName(requestDto.getCustomerName());
        existingOrder.setDueDate(requestDto.getDueDate());
        existingOrder.setStatus(requestDto.getStatus());
        return ResponseEntity.ok(convertToDto(orderService.save(existingOrder)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id) == null) return ResponseEntity.notFound().build();
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/complete")
    public ResponseEntity<CustomerOrderDto> completeOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(convertToDto(orderService.completeOrder(id)));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private CustomerOrderDto convertToDto(CustomerOrder order) {
        CustomerOrderDto dto = new CustomerOrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setDueDate(order.getDueDate());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        if (order.getTasks() != null) {
            dto.setTasks(order.getTasks().stream().map(this::convertTaskToDto).collect(Collectors.toList()));
        }
        return dto;
    }

    private PrintTaskDto convertTaskToDto(ru.printer.farm.printer_farm_api.model.PrintTask task) {
        PrintTaskDto dto = new PrintTaskDto();
        dto.setId(task.getId());
        dto.setPartName(task.getPartName());
        dto.setStatus(task.getStatus());
        dto.setOrderId(task.getOrder() != null ? task.getOrder().getId() : null);
        return dto;
    }
}