package org.penzgtu.Application.models.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductQuantity> products;

    @Version
    private int __v;
}