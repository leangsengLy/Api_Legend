package legend.example.project_api_legend.Specifications.Address;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.DataModel.Address.District.DistrictFilterDataModel;
import legend.example.project_api_legend.Model.Address.LZDistrict;

public class DistrictSpecification {
    public static Specification<LZDistrict> Search(String search){
        var searching = search.toLowerCase();
        return (root,query,cb)->{
            return cb.or(
                    cb.like(cb.lower(root.get("Name")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("Code")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("EnglishName")), "%"+searching+"%")
                );
        };
    }
    public static Specification<LZDistrict> getFilterByProvinceAndCountry(DistrictFilterDataModel filter){
        return (root,query,cb)->{
            if(filter.getCountryId()==null || filter.getProvinceId() == null) {
                return cb.conjunction();
            }
            return cb.and(
                    cb.equal(root.get("CountryId"), filter.getCountryId()),
                    cb.equal(root.get("ProvinceId"), filter.getProvinceId())
                );
        };
    }
}
