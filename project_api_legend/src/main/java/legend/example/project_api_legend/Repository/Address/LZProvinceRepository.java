package legend.example.project_api_legend.Repository.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import legend.example.project_api_legend.Model.Address.LZProvince;

public interface LZProvinceRepository extends JpaRepository<LZProvince,Long>,JpaSpecificationExecutor<LZProvince> {
}
