package com.element.hiking.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikersDetailDTO implements Serializable {
    private static final long serialVersionUID = -9031129800040079140L;

    @NotEmpty
    private String place;

    @NotEmpty
    private List<HikerDTO> hikers;
}
