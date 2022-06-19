package com.element.hiking.tour;

import com.element.hiking.tour.dto.HikerDTO;
import com.element.hiking.tour.dto.HikersDetailDTO;
import com.element.hiking.tour.entity.Place;
import com.element.hiking.tour.entity.TourGroup;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Optional.of;

@UtilityClass
public class TestData {


    public HikersDetailDTO getHikersDetailDTO() {
        return new HikersDetailDTO("shire", getHikers());
    }

    public List<HikerDTO> getHikers() {
        return asList(new HikerDTO("Test01", 15), new HikerDTO("Test02", 20));
    }

    public List<HikerDTO> getInvalidHikers() {
        return asList(new HikerDTO("Test01", 4), new HikerDTO("Test02", 20));
    }

    public Optional<Place> getPlace() {
        return of(Place.builder().name("Shire")
                .startTime(LocalTime.of(7, 0))
                .endTime(LocalTime.of(9, 0))
                .ageMin(5)
                .ageMax(100)
                .price(BigDecimal.valueOf(29.90))
                .build());
    }

    public TourGroup getTourGroup() {
        return new TourGroup(1L, LocalDate.now(), emptySet());
    }

}
