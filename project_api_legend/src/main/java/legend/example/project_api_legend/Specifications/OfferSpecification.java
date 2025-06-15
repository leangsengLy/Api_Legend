package legend.example.project_api_legend.Specifications;

import org.springframework.data.jpa.domain.Specification;
import legend.example.project_api_legend.Model.LZOffer;

public class OfferSpecification {
    public static Specification<LZOffer> Search(String Search ){
        return (root,query,cb)->{
            if(Search ==null || Search==""){
                return cb.conjunction();
            }
            return cb.or(
                cb.like(cb.lower(root.get("label")),"%"+Search+"%" ),
                cb.like(cb.lower(root.get("detail")),"%"+Search+"%" )
            ); 
        };
    }
    public static Specification<LZOffer> SearchDetail(String Search ){
        return (root,query,cb)->{
            if(Search==null || Search==""){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("detail")),"%"+Search+"%" );
        };
    }
   
}
