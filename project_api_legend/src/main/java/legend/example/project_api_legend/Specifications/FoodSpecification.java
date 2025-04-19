package legend.example.project_api_legend.Specifications;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import legend.example.project_api_legend.Model.LZFood;

public class FoodSpecification {
    public static Specification<LZFood> hasName(String name) {
        return (Root<LZFood> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (name == null || name.isEmpty()) {
                return cb.conjunction(); // Return true (no filtering) if name is null/empty
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<LZFood> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) {
                return cb.conjunction();
            }
            if (minPrice == null) {
                return cb.le(root.get("price"), maxPrice);
            }
            if (maxPrice == null) {
                return cb.ge(root.get("price"), minPrice);
            }
            return cb.between(root.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<LZFood> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("category"), category);
        };
    }
}
