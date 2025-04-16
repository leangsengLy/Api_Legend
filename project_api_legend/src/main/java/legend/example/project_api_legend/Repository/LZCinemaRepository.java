package legend.example.project_api_legend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import legend.example.project_api_legend.Model.LZCinema;


public interface LZCinemaRepository extends JpaRepository<LZCinema,Long> {
    Optional<LZCinema> findByCode(String code);
}
