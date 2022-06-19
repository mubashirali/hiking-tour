package com.element.hiking.tour.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "place", schema = "hiking_tour")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int ageMax;
    private int ageMin;
    private BigDecimal price;
}
