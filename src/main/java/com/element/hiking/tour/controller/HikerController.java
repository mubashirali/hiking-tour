package com.element.hiking.tour.controller;

import com.element.hiking.tour.dto.HikersDetailDTO;
import com.element.hiking.tour.dto.TourGroupDTO;
import com.element.hiking.tour.entity.TourGroup;
import com.element.hiking.tour.service.HikerService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/hikers")
@RequiredArgsConstructor
@Timed
public class HikerController {

    private final HikerService hikerService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TourGroupDTO createBooking(@RequestBody @Valid HikersDetailDTO hikersDetailDTO) {
        return hikerService.createHikerBooking(hikersDetailDTO);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<TourGroup> viewBooking(@PathVariable("groupId") Long groupId) {
        final Optional<TourGroup> tourGroup = hikerService.viewGroupBooking(groupId);

        return tourGroup.map(group -> ResponseEntity.ok().body(group))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{groupId}")
    public Boolean cancelBooking(@PathVariable("groupId") Long groupId) {
        return hikerService.deleteBooking(groupId);
    }
}
