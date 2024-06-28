package org.penzgtu.Application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.penzgtu.Application.models.user.User;

@Setter
@Getter
public class RegisterRequest {
    @NotNull
    private User user;

    public RegisterRequest(User u) {
        this.user = u;
    }
}
