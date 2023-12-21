package com.ra.md05ss08bt.payload.response;

import com.ra.md05ss08bt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInforResponse {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
