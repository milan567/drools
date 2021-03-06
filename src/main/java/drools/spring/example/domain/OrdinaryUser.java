package drools.spring.example.domain;

import drools.spring.example.domain.DTO.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by djuro on 5/25/2018.
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ordinary_user")

public class OrdinaryUser extends User {

    public OrdinaryUser(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    public OrdinaryUser(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
    }
}
