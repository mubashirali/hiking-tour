package com.element.hiking.tour.controller;

import com.element.hiking.tour.entity.TourGroup;
import com.element.hiking.tour.service.AdminService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Timed
public class AdminController {

    private final AdminService service;

    @GetMapping("/hikers/{date}")
    public List<TourGroup> viewBooking(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.viewBooking(date);
    }
}
