package legend.example.project_api_legend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import legend.example.project_api_legend.Model.LZHall;

public interface LZHallRepository extends JpaRepository<LZHall,Long> ,JpaSpecificationExecutor<LZHall> {
}  
