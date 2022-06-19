package com.element.hiking.tour.service.impl;

import com.element.hiking.tour.entity.TourGroup;
import com.element.hiking.tour.service.AdminService;
import com.element.hiking.tour.service.TourGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TourGroupRepository groupRepository;

    @Override
    public List<TourGroup> viewBooking(LocalDate date) {
        log.info("Searching booking on Date: {}}", date);

        return groupRepository.findAllByBookingDate(date);
    }
}
