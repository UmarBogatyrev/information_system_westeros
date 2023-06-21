package com.westerostax.westerostax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westerostax.westerostax.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

}