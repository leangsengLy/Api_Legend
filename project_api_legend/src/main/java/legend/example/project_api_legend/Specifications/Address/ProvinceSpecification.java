package legend.example.project_api_legend.Specifications.Address;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.Model.Address.LZProvince;

public class ProvinceSpecification {
    public static Specification<LZProvince> SearchProvince(String search){
        var searching = search.toLowerCase();
        return (root,query,cb)->{
            return cb.or(
                    cb.like(cb.lower(root.get("Name")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("EnglishName")), "%"+searching+"%")
                );
        };
    }
    public static Specification<LZProvince> GetByCountryId(Long countryId){
        return (root,query,cb)->{
            return cb.equal(root.get("CountryId"), countryId);
        };
    }
}
