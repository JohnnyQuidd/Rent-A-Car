package com.group56.postingservice.repository;

import com.group56.postingservice.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType,Long> {
    FuelType findFuelTypeById(Long id);
}
