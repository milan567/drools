package drools.spring.example.repository;

import drools.spring.example.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Integer>
{
}

