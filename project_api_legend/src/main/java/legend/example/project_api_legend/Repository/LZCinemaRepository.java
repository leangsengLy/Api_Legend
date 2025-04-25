package legend.example.project_api_legend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import legend.example.project_api_legend.Model.LZCinema;


public interface LZCinemaRepository extends JpaRepository<LZCinema,Long>,JpaSpecificationExecutor<LZCinema> {
    Optional<LZCinema> findByCode(String code);
}
