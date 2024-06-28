package org.penzgtu.Application.models.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    @Embedded
    private GeoLocation geolocation;

    private String city;
    private String street;
    private int number;
    private String zipcode;
}

