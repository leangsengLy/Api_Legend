package legend.example.project_api_legend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import legend.example.project_api_legend.Model.LZFood;
public interface LZFoodRepository extends JpaRepository<LZFood,Long> {
}
