package org.penzgtu.Application.models.product;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Rating {
    @Column(name="rating_rate")
    private double rate;
    @Column(name="rating_count")
    private int count;
}