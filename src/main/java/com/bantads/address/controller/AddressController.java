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
        addressModelList.sort((a, b) -> a.getId().compareTo(b.getId()));
        return ResponseEntity.ok(addressModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        AddressModel addressModel = this.addressRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(addressModel);
    }

    @PostMapping
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressModel addressModel) {
        AddressModel addedAddressModel = this.addressRepository.save(addressModel);
        return ResponseEntity.ok(addedAddressModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long id, @RequestBody AddressModel addressModel) {
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
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        this.addressRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

}
