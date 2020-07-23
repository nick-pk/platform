package app.platform.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private boolean active;
}
