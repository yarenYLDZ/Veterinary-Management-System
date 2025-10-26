package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.entities.Customer;

import java.util.List;

public interface ICustomerService {

    Customer save (Customer customer);
    Customer get (Long id);
    List<Customer> getAllByName(String name);




}
