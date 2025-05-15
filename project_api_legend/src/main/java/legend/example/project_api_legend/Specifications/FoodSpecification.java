package legend.example.project_api_legend.Specifications;

import org.springframework.data.jpa.domain.Specification;
import legend.example.project_api_legend.Model.LZFood;

public class FoodSpecification {

    public static Specification<LZFood> ListFood(Long cinemaId){
        return (root,query,cb)->{
            return cb.equal(root.get("cinemaId"),cinemaId);
        };
    }
    public static Specification<LZFood> SearchFood(String search){
        var searching = search.toLowerCase();
        return (root,query,cb)->{
            return cb.or(
                    cb.like(cb.lower(root.get("name")), "%"+searching+"%"),
                    cb.like(cb.lower(root.get("englishName")), "%"+searching+"%")
                );
        };
    }
    public static Specification<LZFood> GetPriceBy(Double price){
        return (root,query,cb)->{
            return cb.equal(root.get("price"), price);
        };
    }
   
}
