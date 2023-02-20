package com.bantads.address.repository;

import com.bantads.address.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<AddressModel, String> {
    @Query("SELECT a FROM AddressModel a WHERE a.customer = ?1")
    AddressModel findByCustomer(String customer);
}
