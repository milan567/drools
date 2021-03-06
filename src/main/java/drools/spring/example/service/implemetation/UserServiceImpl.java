package drools.spring.example.service.implemetation;

import drools.spring.example.domain.*;
import drools.spring.example.domain.DTO.LoginRequestDTO;
import drools.spring.example.domain.DTO.LoginResponseDTO;
import drools.spring.example.domain.DTO.RegisterUserDTO;
import drools.spring.example.repository.*;
import drools.spring.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    OrdinaryUserRepository odOrdinaryUserRepository;



    @Override
    public User save(User user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponseDTO registerUser(RegisterUserDTO registerUser)
    {
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        Authority authority = this.authorityRepository.findByName(registerUser.getRole());  //u bazi se nalaze predefinisane uloge
        User user = new User(registerUser.getUsername(),registerUser.getPassword());
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority(authority);
        this.userAuthorityRepository.save(userAuthority);
        switch (registerUser.getRole()) {
            case "ROLE_ADMIN":
            {

                Admin admin = new Admin(registerUser);
                admin.addUserAuthority(userAuthority);
                this.adminRepository.save(admin);
                userAuthority.setUser(admin);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(admin.getId());
                user.addUserAuthority(userAuthority);
                break;
            }
            case "ORDINARY_USER":
            {
                OrdinaryUser ordinaryUser = new OrdinaryUser(registerUser);
                ordinaryUser.addUserAuthority(userAuthority);
                this.odOrdinaryUserRepository.save(ordinaryUser);
                userAuthority.setUser(ordinaryUser);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(ordinaryUser.getId());
                user.addUserAuthority(userAuthority);
                break;
            }
            default:
                break;
        }
        return new LoginResponseDTO(user);
    }

    @Override
    public User getUserById(Integer userId)
    {
        User user = this.userRepository.getOne(userId);
        return user;
    }

    @Override
    public User getUserByUsername(String username)
    {
        User user = null;
        user = this.userRepository.findByUsername(username);
        return user;
    }

}
