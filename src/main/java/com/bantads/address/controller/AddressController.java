package com.bantads.address.controller;

import com.bantads.address.model.AddressModel;
import com.bantads.address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<List<AddressModel>> getAllAddresses() {
        List<AddressModel> addressModelList = this.addressRepository.findAll();
        return ResponseEntity.ok(addressModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable String id) {
        AddressModel addressModel = this.addressRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(addressModel);
    }

    @PostMapping
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressModel addressModel) {
        AddressModel addedAddressModel = this.addressRepository.save(addressModel);
        return ResponseEntity.ok(addedAddressModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable String id, @RequestBody AddressModel addressModel) {
        AddressModel address = this.addressRepository.findById(id).orElseThrow();
        address.setType(addressModel.getType());
        address.setStreet(addressModel.getStreet());
        address.setNumber(addressModel.getNumber());
        address.setCity(addressModel.getCity());
        address.setComplement(addressModel.getComplement());
        address.setCep(addressModel.getCep());
        address.setState(addressModel.getState());
        return ResponseEntity.ok(this.addressRepository.save(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable String id) {
        this.addressRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressModel> patchAddress(@PathVariable String id, @RequestBody AddressModel addressModel) {
        AddressModel address = this.addressRepository.findById(id).orElseThrow();
        if (addressModel.getUuid() != null) {
            return ResponseEntity.notFound().build();
        }
        if (addressModel.getType() != null) {
            address.setType(addressModel.getType());
        }
        if (addressModel.getStreet() != null) {
            address.setStreet(addressModel.getStreet());
        }
        if (addressModel.getNumber() != null) {
            address.setNumber(addressModel.getNumber());
        }
        if (addressModel.getCity() != null) {
            address.setCity(addressModel.getCity());
        }
        if (addressModel.getComplement() != null) {
            address.setComplement(addressModel.getComplement());
        }
        return ResponseEntity.ok(this.addressRepository.save(address));
    }

}
