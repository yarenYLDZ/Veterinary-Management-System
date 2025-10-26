package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.ICustomerService;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.core.utilies.Messages;
import dev.patika.veterinary.Management.System.dao.CustomerRepo;
import dev.patika.veterinary.Management.System.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<Customer> getAllByName(String name) {
        return customerRepo.findAllByNameContainingIgnoreCase(name);
    }



}
