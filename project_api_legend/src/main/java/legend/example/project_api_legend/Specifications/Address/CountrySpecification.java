package legend.example.project_api_legend.Specifications.Address;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.Model.Address.LZCountry;

public class CountrySpecification {
    public static Specification<LZCountry> SearchFood(String search){
        var searching = search.toLowerCase();
        return (root,query,cb)->{
            return cb.or(
                    cb.like(cb.lower(root.get("Name")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("Code")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("EnglishName")), "%"+searching+"%")
                );
        };
    }
}
