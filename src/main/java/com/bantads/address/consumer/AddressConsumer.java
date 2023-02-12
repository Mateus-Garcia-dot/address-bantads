package com.bantads.address.consumer;

import com.bantads.address.model.AddressModel;
import com.bantads.address.repository.AddressRepository;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
