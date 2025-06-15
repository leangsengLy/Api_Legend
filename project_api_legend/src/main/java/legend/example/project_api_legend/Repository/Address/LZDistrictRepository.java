package legend.example.project_api_legend.Repository.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import legend.example.project_api_legend.Model.Address.LZDistrict;
@Repository
public interface LZDistrictRepository extends JpaRepository<LZDistrict,Long>,JpaSpecificationExecutor<LZDistrict> {
}
