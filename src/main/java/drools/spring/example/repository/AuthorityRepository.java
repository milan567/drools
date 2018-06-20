package drools.spring.example.repository;

import drools.spring.example.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    @Query("SELECT w FROM Authority w WHERE w.name = :name")
    Authority findByName(@Param("name") String name);
}
