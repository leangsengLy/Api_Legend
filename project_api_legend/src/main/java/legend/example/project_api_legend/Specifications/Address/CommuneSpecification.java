package legend.example.project_api_legend.Specifications.Address;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.Model.Address.LZCommune;

public class CommuneSpecification {
    public static Specification<LZCommune> Search(String search){
        var searching = search.toLowerCase();
        return (root,query,cb)->{
            return cb.or(
                    cb.like(cb.lower(root.get("Name")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("EnglishName")), "%"+searching+"%")
                );
        };
    }
}
