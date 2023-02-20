package com.bantads.address.consumer;

import com.bantads.address.model.AddressModel;
import com.bantads.address.repository.AddressRepository;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Data
@Component
public class AddressConsumer {

    private final AddressRepository addressRepository;

    @RabbitListener(queues = "address.create")
    public void createAddress(@RequestBody AddressModel addressModel) {
       this.addressRepository.save(addressModel);
    }

    @RabbitListener(queues = "address.update")
    public void updateAddress(@PathVariable String id, @RequestBody AddressModel addressModel) {
        AddressModel address = this.addressRepository.findById(id).orElse(null);
        if (address == null) {
            return;
        }
        address.setType(addressModel.getType());
        address.setStreet(addressModel.getStreet());
        address.setNumber(addressModel.getNumber());
        address.setCity(addressModel.getCity());
        address.setComplement(addressModel.getComplement());
        address.setCep(addressModel.getCep());
        address.setState(addressModel.getState());
    }

    @RabbitListener(queues = "address.delete")
    public void deleteAddress(@PathVariable String id) {
        this.addressRepository.deleteById(id);
    }

    @RabbitListener(queues = "address.patch")
    public void patchAddress(AddressModel addressModel) {
        AddressModel address = this.addressRepository.findByCustomer(addressModel.getCustomer());
        if (address == null) {
            return;
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
        if (addressModel.getCep() != null) {
            address.setCep(addressModel.getCep());
        }
        if (addressModel.getState() != null) {
            address.setState(addressModel.getState());
        }
    }

}
