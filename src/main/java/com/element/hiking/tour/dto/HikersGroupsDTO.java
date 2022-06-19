package com.element.hiking.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikersGroupsDTO implements Serializable {
    private static final long serialVersionUID = -9031129800040079140L;

    private List<HikersDetailDTO> hikersDetailDTOS;
}
