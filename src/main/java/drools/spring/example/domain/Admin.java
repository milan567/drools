package drools.spring.example.domain;

import drools.spring.example.domain.DTO.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "admin1")
public class Admin extends User {

    public Admin(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    public Admin(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
    }
}
