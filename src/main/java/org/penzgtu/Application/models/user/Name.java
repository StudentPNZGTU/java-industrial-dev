package org.penzgtu.Application.models.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Name {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
}
