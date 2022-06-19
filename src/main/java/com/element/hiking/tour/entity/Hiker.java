package com.element.hiking.tour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "hiker", schema = "hiking_tour")
@NoArgsConstructor
@AllArgsConstructor
public class Hiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String place;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @JsonIgnore
    private TourGroup tourGroup;
}
