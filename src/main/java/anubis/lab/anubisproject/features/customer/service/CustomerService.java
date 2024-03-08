package anubis.lab.anubisproject.features.customer.service;

import java.time.LocalDate;
import java.util.List;

import anubis.lab.anubisproject.features.customer.dto.CustomerDTO;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface CustomerService extends GraphQLQueryResolver, GraphQLMutationResolver {

    CustomerDTO addCustomer(Customer customer);

    CustomerDTO updateCustomer(String idCustomer, Customer requestCustomer);

    Customer getCustomer(String id);

    List<CustomerDTO> getAllCustomers();

    Boolean deleteCustomer(String idCustomer);
}
