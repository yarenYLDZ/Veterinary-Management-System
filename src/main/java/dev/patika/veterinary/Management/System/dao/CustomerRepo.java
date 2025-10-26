package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

    List<Customer> findAllByNameContainingIgnoreCase(String name);



}
