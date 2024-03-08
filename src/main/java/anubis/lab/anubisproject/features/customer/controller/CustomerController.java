package anubis.lab.anubisproject.features.customer.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import anubis.lab.anubisproject.features.customer.service.CustomerService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping()
    public CustomerDTO addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{idCustomer}")
    public CustomerDTO updateCustomer(@PathVariable String idCustomer, @RequestBody Customer customer) {
        return customerService.updateCustomer(idCustomer, customer);
    }

    @GetMapping("/{idCustomer}")
    public Customer getCustomer(@PathVariable String idCustomer) {
        Customer customer = customerService.getCustomer(idCustomer);
        return customer;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
        return customerDTOs;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCustomer(String customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
