package com.element.hiking.tour.service;

import com.element.hiking.tour.dto.HikersDetailDTO;
import com.element.hiking.tour.dto.TourGroupDTO;
import com.element.hiking.tour.entity.TourGroup;

import java.util.Optional;

public interface HikerService {

    TourGroupDTO createHikerBooking(HikersDetailDTO detailDTO);

    Optional<TourGroup> viewGroupBooking(Long groupId);

    Boolean deleteBooking(Long groupId);
}
