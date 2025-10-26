package dev.patika.veterinary.Management.System.api;


import dev.patika.veterinary.Management.System.business.abstracts.ICustomerService;
import dev.patika.veterinary.Management.System.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.Management.System.core.result.Result;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.customer.CustomerFilterRequest;
import dev.patika.veterinary.Management.System.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinary.Management.System.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.Management.System.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
       Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
       this.customerService.save(saveCustomer);
       return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id){
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer,CustomerResponse.class));
    }

    @GetMapping("/by-name")
    public ResultData<List<CustomerResponse>> getCustomersByName(@Valid @RequestBody CustomerFilterRequest request) {
        List<Customer> customers = customerService.getAllByName(request.getName());
        List<CustomerResponse> response = customers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .toList();
        return ResultHelper.success(response);
    }



}
