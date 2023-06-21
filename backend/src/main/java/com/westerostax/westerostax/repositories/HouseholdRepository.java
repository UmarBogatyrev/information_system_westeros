package com.westerostax.westerostax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.entity.Household;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

}