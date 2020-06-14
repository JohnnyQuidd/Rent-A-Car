package com.group56.searchservice.repository;

import com.group56.searchservice.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    Advert findAdvertById(Long id);
}
