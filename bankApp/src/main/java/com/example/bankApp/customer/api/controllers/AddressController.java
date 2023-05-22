package com.example.bankApp.customer.api.controllers;

import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.customer.business.abstracts.AddressService;
import com.example.bankApp.customer.core.dto.request.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PostMapping
    public GeneralResult create(@RequestBody AddressRequest request){
        return addressService.add(request);
    }
    @PutMapping
    public GeneralResult update(@PathVariable int id,@RequestBody AddressRequest request){
        return addressService.update(id,request);
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
