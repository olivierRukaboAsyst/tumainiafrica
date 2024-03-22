package anubis.lab.anubisproject.features.customer.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import anubis.lab.anubisproject.features.customer.service.CustomerService;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.addCustomer(customer));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{idCustomer}")
    public ResponseEntity<?> updateCustomer(@PathVariable String idCustomer, @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(idCustomer, customer));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idCustomer}")
    public ResponseEntity<?> getCustomer(@PathVariable String idCustomer) {
        try {
            return ResponseEntity.ok(customerService.getCustomer(idCustomer));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCustomer(String customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
