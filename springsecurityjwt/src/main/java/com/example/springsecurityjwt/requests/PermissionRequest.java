package com.example.springsecurityjwt.requests;

import com.example.springsecurityjwt.enums.PermissionsEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest {

    @NotBlank
    private PermissionsEnum permission;

}
