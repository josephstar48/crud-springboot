package com.springcheckpoint.springcheckpoint;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDTO {
    private boolean authenticated;
    private UserDTO user;

    public AuthUserDTO(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
