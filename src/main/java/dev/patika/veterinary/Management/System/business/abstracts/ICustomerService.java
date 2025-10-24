package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.entities.Customer;

public interface ICustomerService {

    Customer save (Customer customer);
    Customer get (Long id);



}
