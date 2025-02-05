package com.example.springsecurityjwt.requests;

import com.example.springsecurityjwt.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {

    private Long id;
    private RoleEnum role;
}
