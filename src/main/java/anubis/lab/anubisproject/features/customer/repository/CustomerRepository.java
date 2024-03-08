package anubis.lab.anubisproject.features.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anubis.lab.anubisproject.features.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
