package com.westerostax.westerostax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.westerostax.westerostax.models.Household;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

}