package anubis.lab.anubisproject.features.customer.service;

import java.util.List;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;

public interface CustomerService {

    CustomerDTO addCustomer(Customer customer);
    CustomerDTO updateCustomer(String idCustomer, Customer requestCustomer);
    Customer getCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    Boolean deleteCustomer(String idCustomer);
}
