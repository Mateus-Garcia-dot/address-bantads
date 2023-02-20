package com.bantads.address.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressModel {
    @Id
    private String uuid = java.util.UUID.randomUUID().toString();
    private String customer;
    private String type;
    private String street;
    private Integer number;
    private String city;
    private String complement;
    private String cep;
    private String state;
}
