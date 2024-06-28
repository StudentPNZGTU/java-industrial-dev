package org.penzgtu.Application.models.cart;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class ProductQuantity {
    private long productId;
    private int quantity;
}

