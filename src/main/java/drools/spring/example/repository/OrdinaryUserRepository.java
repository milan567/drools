package drools.spring.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import drools.spring.example.domain.*;

public interface OrdinaryUserRepository extends JpaRepository<OrdinaryUser,Integer> {
}
