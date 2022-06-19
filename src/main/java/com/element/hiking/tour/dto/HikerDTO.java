package com.element.hiking.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikerDTO implements Serializable {
    private static final long serialVersionUID = 8336719891326275417L;

    @NotEmpty
    private String name;
    private int age;

}
