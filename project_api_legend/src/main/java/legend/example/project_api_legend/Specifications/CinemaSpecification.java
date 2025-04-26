package legend.example.project_api_legend.Specifications;
import org.springframework.data.jpa.domain.Specification;
import legend.example.project_api_legend.Model.LZCinema;

public class CinemaSpecification {
    public static Specification<LZCinema> ListFood(Long Id){
        return (root,query,cb)->{
            if(Id==null || Id<1){
                return cb.conjunction();
            }
            return cb.equal(root.get("id"),Id);
        };
    }
    public static Specification<LZCinema> Search(String search){
        return (root,query,cb)-> {
            if(search=="" || search == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("name")), "%"+search+"%");
        };
    }

    // public static Specification<User> withName(String name) {
    //     return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> 
    //         name == null ? null : cb.equal(root.get("name"), name);
    // }

    // public static Specification<User> withMinAge(Integer minAge) {
    //     return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> 
    //         minAge == null ? null : cb.greaterThanOrEqualTo(root.get("age"), minAge);
    // }

    // public static Specification<User> withEmailLike(String emailPattern) {
    //     return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> 
    //         emailPattern == null ? null : cb.like(root.get("email"), "%" + emailPattern + "%");
    // }
}
