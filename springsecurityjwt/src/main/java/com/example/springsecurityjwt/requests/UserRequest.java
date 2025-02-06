package com.example.springsecurityjwt.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private boolean isEnabled;

    @NotNull
    private boolean accountNoExpired;

    @NotNull
    private boolean accountNoLocked;

    @NotNull
    private boolean credentialNoExpired;

    @NotEmpty
    private Set<RoleRequest> roles;

}
