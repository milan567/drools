package drools.spring.example.domain.DTO;

import drools.spring.example.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO
{
    private String token;
    private Integer id;
    private String username;
    private String role;

    public LoginResponseDTO(User user)
    {
        this.token = user.getPassword();
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getFirstUserAuthority().getAuthority().getName();
    }
}
