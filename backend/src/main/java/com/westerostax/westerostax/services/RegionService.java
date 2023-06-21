package com.westerostax.westerostax.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.westerostax.westerostax.entity.Region;
import com.westerostax.westerostax.repositories.RegionRepository;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(int id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(int id) {
        regionRepository.deleteById(id);
    }
}