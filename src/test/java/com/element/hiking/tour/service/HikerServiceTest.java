package com.element.hiking.tour.service;

import com.element.hiking.tour.TestData;
import com.element.hiking.tour.dto.HikersDetailDTO;
import com.element.hiking.tour.dto.TourGroupDTO;
import com.element.hiking.tour.entity.Hiker;
import com.element.hiking.tour.exception.InvalidAgeException;
import com.element.hiking.tour.exception.InvalidPlaceException;
import com.element.hiking.tour.service.impl.HikerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HikerServiceTest {

    @Mock
    private HikerRepository hikerRepository;

    @Mock
    private TourGroupRepository tourGroupRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Captor
    private ArgumentCaptor<List<Hiker>> captor;

    @InjectMocks
    private HikerServiceImpl hikerService;

    @Test
    void createHikerBookingTest() {
        //Given
        final HikersDetailDTO hikersDetailDTO = TestData.getHikersDetailDTO();

        //When
        when(tourGroupRepository.save(any())).thenReturn(TestData.getTourGroup());
        when(placeRepository.findById(any())).thenReturn(TestData.getPlace());
        final TourGroupDTO hikerBooking = hikerService.createHikerBooking(hikersDetailDTO);

        //Then
        verify(hikerRepository, times(1)).saveAll(captor.capture());
        assertEquals(hikerBooking.getBookingDate(), LocalDate.now());
        assertEquals(hikerBooking.getPaymentAmount(), BigDecimal.valueOf(59.8));
    }

    @Test
    void createHikerBookingFailedTest() {
        //Given
        final HikersDetailDTO hikersDetailDTO = new HikersDetailDTO("Shire", TestData.getInvalidHikers());

        //When
        when(placeRepository.findById(any())).thenReturn(TestData.getPlace());

        Assertions.assertThrows(InvalidAgeException.class,
                () -> hikerService.createHikerBooking(hikersDetailDTO));

    }

    @Test
    void createHikerBookingInvalidPlace() {
        //Given
        final HikersDetailDTO hikersDetailDTO = new HikersDetailDTO("Shisre", TestData.getHikers());

        //When
        Assertions.assertThrows(InvalidPlaceException.class,
                () -> hikerService.createHikerBooking(hikersDetailDTO));

    }

}
