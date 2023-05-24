package com.example.bankApp.customer.api.controllers;

import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.CustomerService;
import com.example.bankApp.customer.core.dto.request.CreateCustomerRequest;
import com.example.bankApp.customer.core.dto.request.UpdateCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService addressService;

    @PostMapping
    public GeneralResult create(@RequestBody CreateCustomerRequest request) throws EntityNotFoundException {
       return addressService.add(request);
    }
    @PutMapping
    public GeneralResult update(@RequestParam int customerId,@RequestBody UpdateCustomerRequest request){
        return addressService.update(customerId,request);
    }
    @DeleteMapping
    public void delete(@RequestParam int id){
        addressService.delete(id);
    }
    @GetMapping
    public GeneralResult getAll(){
        return  addressService.getAll();
    }

}
