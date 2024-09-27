package com.tracker.recordSearch.service.impl;

import com.tracker.recordSearch.domain.City;
import com.tracker.recordSearch.repository.CityRepository;
import com.tracker.recordSearch.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
