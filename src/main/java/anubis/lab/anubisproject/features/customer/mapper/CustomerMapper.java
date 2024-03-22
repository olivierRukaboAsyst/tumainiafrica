package anubis.lab.anubisproject.features.customer.mapper;

import anubis.lab.anubisproject.features.customer.dto.CustomerToArticleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;

@Service
public class CustomerMapper {
    
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        return customer;
    }

    public CustomerToArticleDTO fromCustomerArticle(Customer customer){
        CustomerToArticleDTO c = new CustomerToArticleDTO();
        BeanUtils.copyProperties(customer, c);

        return c;
    }
}
