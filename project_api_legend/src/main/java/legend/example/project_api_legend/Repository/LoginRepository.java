package legend.example.project_api_legend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import legend.example.project_api_legend.Model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
    @Query("SELECT l FROM Login l WHERE l.email = ?1")
    Optional<Login> getDataByEmail(String email);
    Optional<Login> findByEmail(String email);
    Optional<Login> findByEmailAndPassword(String email,String password);
}
