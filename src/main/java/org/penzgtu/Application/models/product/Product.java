package org.penzgtu.Application.models.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    @Column(length = 2048)
    private String description;
    private String category;
    @Column(length = 2048)
    private String image;

    @Embedded
    private Rating rating;
}
