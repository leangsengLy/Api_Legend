package legend.example.project_api_legend.Specifications.Setting;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.Model.LZHall;

public class HallSpecification {
    public static Specification<LZHall> listByCode(String code) {
        return (root, query, cb) -> {
            if (code == null || code.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("CODE"), code);
        };
    }
    public static Specification<LZHall> IsActive(Boolean isActive){
        return (Root,query,cb)->{
            return cb.equal(Root.get("IS_ACTIVE"), isActive);
        };
    }
    public static Specification<LZHall> Search(String search){
        return (root,query,cb)->{
            if(search==null || search.isEmpty()){
                return cb.conjunction();
            }
            return cb.or(
                cb.like(root.get("CODE"), "%" + search + "%"),
                cb.like(root.get("NAME"), "%" + search + "%"),
                cb.like(root.get("EN_NAME"), "%" + search + "%")
            );
        };
    }
}
