package com.bantads.address.repository;

import com.bantads.address.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, String> {
}
