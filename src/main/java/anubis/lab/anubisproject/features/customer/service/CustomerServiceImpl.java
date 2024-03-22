package anubis.lab.anubisproject.features.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import anubis.lab.anubisproject.features.customer.mapper.CustomerMapper;
import anubis.lab.anubisproject.features.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerDTO addCustomer(Customer customer) {
        try {
            Customer savedCustomer = customerRepository.save(customer);
            return mapper.fromCustomer(savedCustomer);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de l'enregistrement"));
        }
    }

    @Override
    public CustomerDTO updateCustomer(String idCustomer, Customer requestCustomer) {
        Customer customer = getCustomer(idCustomer);
        try {
            if (customer != null) {
                if (requestCustomer.getFullname() != null) {
                    customer.setFullname(requestCustomer.getFullname());
                }
                if (requestCustomer.getEmail() != null) {
                    customer.setEmail(requestCustomer.getEmail());
                }
                if (requestCustomer.getPhoneNumber() != null) {
                    customer.setPhoneNumber(requestCustomer.getPhoneNumber());
                }

            }
            return mapper.fromCustomer(customerRepository.save(customer));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }

    }

    @Override
    public Customer getCustomer(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce client n'existe pas"));
        return customer;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new RuntimeException(String.format("Pas des clients pour l'instant"));
        }
        List<CustomerDTO> customerDTOs = customers.stream().map(c -> mapper.fromCustomer(c))
                .collect(Collectors.toList());
        return customerDTOs;
    }

    @Override
    public Boolean deleteCustomer(String idCustomer) {
        getCustomer(idCustomer);
        try {
            customerRepository.deleteById(idCustomer);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
