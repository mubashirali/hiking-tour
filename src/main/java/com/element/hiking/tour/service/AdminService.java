package com.element.hiking.tour.service;

import com.element.hiking.tour.entity.TourGroup;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    List<TourGroup> viewBooking(LocalDate date);
}
