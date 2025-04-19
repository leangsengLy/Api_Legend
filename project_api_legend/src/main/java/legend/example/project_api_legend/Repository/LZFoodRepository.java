package legend.example.project_api_legend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import legend.example.project_api_legend.Model.LZFood;
import java.util.List;

public interface LZFoodRepository extends JpaRepository<LZFood,Long> , JpaSpecificationExecutor<LZFood> {
    List<LZFood> findByCinemaId(Long cinemaId);
}
