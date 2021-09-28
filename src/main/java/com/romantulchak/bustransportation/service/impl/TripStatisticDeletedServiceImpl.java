package com.romantulchak.bustransportation.service.impl;

import com.ecfinder.core.manager.ECFinderInvoker;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripStatisticDeletedDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.TripStatisticDeleted;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.TripStatisticDeletedRepository;
import com.romantulchak.bustransportation.service.TripStatisticDeletedService;
import com.romantulchak.bustransportation.utils.PageableUtils;
import com.romantulchak.bustransportation.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripStatisticDeletedServiceImpl implements TripStatisticDeletedService {

    private final TripStatisticDeletedRepository tripStatisticDeletedRepository;
    private final ECFinderInvoker<CityStop> ecFinderInvoker;
    private final EntityMapperInvoker<TripStatisticDeleted, TripStatisticDeletedDTO> entityMapperInvoker;

    @Autowired
    public TripStatisticDeletedServiceImpl(TripStatisticDeletedRepository tripStatisticDeletedRepository,
                                           ECFinderInvoker<CityStop> ecFinderInvoker,
                                           EntityMapperInvoker<TripStatisticDeleted, TripStatisticDeletedDTO> entityMapperInvoker) {
        this.tripStatisticDeletedRepository = tripStatisticDeletedRepository;
        this.ecFinderInvoker = ecFinderInvoker;
        this.entityMapperInvoker = entityMapperInvoker;
    }


    @Override
    public void create(Trip trip) {
        List<CityStop> stops = ecFinderInvoker.invoke(trip.getId(), CityStop.class, Trip.class);
        LocalDateTime departure = stops.get(0).getDeparture();
        LocalDateTime arrival = stops.get(stops.size() - 1).getArrival();
        TripStatisticDeleted tripStatisticDeleted = new TripStatisticDeleted(trip.getName(), trip.getCreator(), stops, departure, arrival);
        tripStatisticDeletedRepository.save(tripStatisticDeleted);
    }

    @Override
    public PageableDTO<TripStatisticDeletedDTO> getFullStatistics(int page, int size, Authentication authentication) {
        UserDetailsImpl userDetails = UserUtils.userInSystem(authentication);
        page = PageableUtils.getCorrectPage(page);
        Pageable pageable = PageRequest.of(page, size);
        Page<TripStatisticDeleted> deleteTripsPage = tripStatisticDeletedRepository.findAllByUserId(userDetails.getId(), pageable);
        List<TripStatisticDeletedDTO> deletedTrips = deleteTripsPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageableDTO<>(deleteTripsPage.getTotalPages(), deleteTripsPage.getTotalElements(), page, deletedTrips);
    }

    @Override
    public List<CityStop> findCityStops(long id) {
        return ecFinderInvoker.invoke(id, CityStop.class, TripStatisticDeleted.class);
    }

    private TripStatisticDeletedDTO convertToDTO(TripStatisticDeleted tripStatisticDeleted) {
        return entityMapperInvoker.entityToDTO(tripStatisticDeleted, TripStatisticDeletedDTO.class, View.TripDeletedStatistic.class);
    }
}
