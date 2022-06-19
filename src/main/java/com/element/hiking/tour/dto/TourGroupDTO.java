package com.element.hiking.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourGroupDTO implements Serializable {
    private static final long serialVersionUID = 8336719891326275417L;

    private Long groupId;
    private BigDecimal paymentAmount;
    private LocalDate bookingDate;
}
