package legend.example.project_api_legend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import legend.example.project_api_legend.Model.LZMovieType;
@Repository
public interface LZMovieTypeRepository extends JpaRepository<LZMovieType,Long>,JpaSpecificationExecutor<LZMovieType> {
} 
