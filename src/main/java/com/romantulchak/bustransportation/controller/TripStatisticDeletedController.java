package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripStatisticDeletedDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.service.TripStatisticDeletedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping(value = "/api/trip-deleted-statistic")
public class TripStatisticDeletedController {

    private final TripStatisticDeletedService tripStatisticDeletedService;

    @Autowired
    public TripStatisticDeletedController(TripStatisticDeletedService tripStatisticDeletedService) {
        this.tripStatisticDeletedService = tripStatisticDeletedService;
    }

    @GetMapping("/get-statistics")
    @PreAuthorize("hasRole('USER')")
    public PageableDTO<TripStatisticDeletedDTO> getFullStatistics(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size",
                                                                                defaultValue = "5",
                                                                                required = false) int size,
                                                                  Authentication authentication) {
        return tripStatisticDeletedService.getFullStatistics(page, size, authentication);
    }

    @GetMapping("/get-statistics-details/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<CityStop> getStatisticsDetails(@PathVariable("id") long id){
        return tripStatisticDeletedService.findCityStops(id);
    }
}
