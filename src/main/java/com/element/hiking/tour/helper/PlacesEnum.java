package com.element.hiking.tour.helper;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum PlacesEnum {
    SHIRE("Shire"),
    GONDOR("Gondor"),
    MORDOR("Mordor");

    private final String value;

    PlacesEnum(String value) {
        this.value = value;
    }
}
