package com.element.hiking.tour.service.impl;

import com.element.hiking.tour.dto.HikerDTO;
import com.element.hiking.tour.dto.HikersDetailDTO;
import com.element.hiking.tour.dto.TourGroupDTO;
import com.element.hiking.tour.entity.Hiker;
import com.element.hiking.tour.entity.Place;
import com.element.hiking.tour.entity.TourGroup;
import com.element.hiking.tour.exception.InvalidAgeException;
import com.element.hiking.tour.exception.InvalidPlaceException;
import com.element.hiking.tour.helper.PlacesEnum;
import com.element.hiking.tour.service.HikerRepository;
import com.element.hiking.tour.service.HikerService;
import com.element.hiking.tour.service.PlaceRepository;
import com.element.hiking.tour.service.TourGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class HikerServiceImpl implements HikerService {

    private final HikerRepository hikerRepository;
    private final TourGroupRepository tourGroupRepository;
    private final PlaceRepository placeRepository;

    @Override
    public TourGroupDTO createHikerBooking(HikersDetailDTO detailDTO) {
        final Place place = getPlace(detailDTO.getPlace());
        final TourGroup group = createNewTourGroup();
        log.info("Created new Group: {}", group);

        final List<Hiker> hikers = detailDTO.getHikers()
                .stream()
                .filter(hikerDTO -> validateHiker(hikerDTO, place.getAgeMin(), place.getAgeMax()))
                .map(hikerDTO -> toHiker(hikerDTO, group, place.getName()))
                .collect(Collectors.toList());

        log.debug("Saving hikers {}", hikers);

        hikerRepository.saveAll(hikers);

        return toTourGroupDTO(group, hikers.size(), place.getPrice());
    }

    @Override
    public Optional<TourGroup> viewGroupBooking(Long groupId) {
        return tourGroupRepository.findById(groupId);
    }

    @Override
    public Boolean deleteBooking(Long groupId) {
        if (tourGroupRepository.findById(groupId).isPresent()) {
            log.info("Deleting group: {}", groupId);
            
            tourGroupRepository.deleteById(groupId);

            return true;
        }

        log.info("Unable to delete group {}", groupId);

        return false;
    }

    private Place getPlace(String placeName) {
        log.info("Checking for the place from DB {}", placeName);

        if (EnumUtils.isValidEnum(PlacesEnum.class, placeName.toUpperCase())) {
            final Optional<Place> place = getPlaceEntity(placeName);
            if (place.isPresent()) {
                return place.get();
            }
        }

        throw new InvalidPlaceException("Invalid place: " + placeName);
    }

    private Optional<Place> getPlaceEntity(String placeName) {
        final String placeValue = PlacesEnum.valueOf(placeName.toUpperCase()).getValue();
        return placeRepository.findById(placeValue);
    }

    private boolean validateHiker(HikerDTO hiker, int ageMin, int ageMax) {
        if (hiker.getAge() >= ageMin && hiker.getAge() <= ageMax) {
            return true;
        }

        throw new InvalidAgeException(format("Hiker: %s age does't matches the required age limit [%d - %d]}",
                hiker.getName(), ageMin, ageMax));
    }

    private TourGroup createNewTourGroup() {
        final TourGroup tourGroup = new TourGroup();
        tourGroup.setBookingDate(LocalDate.now());

        return tourGroupRepository.save(tourGroup);
    }

    private TourGroupDTO toTourGroupDTO(TourGroup group, int groupSize, BigDecimal price) {
        return new TourGroupDTO(group.getId(), price.multiply(new BigDecimal(groupSize)), group.getBookingDate());
    }

    private Hiker toHiker(HikerDTO hikerDTO, TourGroup group, String place) {
        return Hiker.builder()
                .name(hikerDTO.getName()).age(hikerDTO.getAge()).place(place)
                .tourGroup(group)
                .build();
    }
}
