package com.element.hiking.tour.service;

import com.element.hiking.tour.entity.TourGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourGroupRepository extends JpaRepository<TourGroup, Long> {

    List<TourGroup> findAllByBookingDate(LocalDate date);

    TourGroup findByBookingDate(LocalDate date);
}
