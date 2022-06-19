package com.element.hiking.tour.service;

import com.element.hiking.tour.entity.Hiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HikerRepository extends JpaRepository<Hiker, Long> {

}
