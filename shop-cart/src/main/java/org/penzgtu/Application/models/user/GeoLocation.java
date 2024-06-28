package org.penzgtu.Application.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class GeoLocation {
    @Column(name = "geolocation_latitude")
    private String lat;

    @Column(name = "geolocation_longitude")
    @JsonProperty("long")
    private String lng;
}