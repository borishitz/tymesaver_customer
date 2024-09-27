package com.tracker.recordSearch.service;


import com.tracker.recordSearch.domain.Trip;
import com.tracker.recordSearch.dto.TripDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    List<TripDto> trips();

    List<TripDto> allTrip();

    public List<Trip> getTripByTravelAgencyId(Long id);

    Trip save(MultipartFile imageProduct, TripDto product);

    Trip update(MultipartFile imageProduct, TripDto productDto);

    void enableById(Long id);

    void deleteById(Long id);

    TripDto getById(Long id);

    Trip findById(Long id);


    List<TripDto> randomTrip();

    Page<TripDto> searchTrips(int pageNo, String keyword);

    Page<TripDto> getAllTrips(int pageNo);

    Page<TripDto> getAllTripsForCustomer(int pageNo);


    List<TripDto> findAllByCategory(String category);


    List<TripDto> filterHighTrips();

    List<TripDto> filterLowerTrips();

    List<TripDto> listViewTrips();

    List<TripDto> findByCategoryId(Long id);

    List<TripDto> searchTrips(String keyword);


}
