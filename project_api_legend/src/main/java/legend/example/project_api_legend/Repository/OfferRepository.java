package legend.example.project_api_legend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import legend.example.project_api_legend.Model.LZOffer;
@Repository
public interface OfferRepository extends JpaRepository<LZOffer,Long>,JpaSpecificationExecutor<LZOffer> {
    
}
