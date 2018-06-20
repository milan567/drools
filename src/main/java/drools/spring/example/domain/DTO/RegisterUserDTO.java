package drools.spring.example.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO
{
    private String username;
    private String password;
    private String role;
    private String firstname;
    private String lastname;
    private String institutionName;
    private String firmName;
    private String firmDescription;
}
