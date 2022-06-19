package com.element.hiking.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tour_group", schema = "hiking_tour")
@AllArgsConstructor
@NoArgsConstructor
public class TourGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate bookingDate;

    @OneToMany(mappedBy = "tourGroup")
    private Set<Hiker> hikers;
}
