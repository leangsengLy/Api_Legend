package legend.example.project_api_legend.Specifications;

import org.springframework.data.jpa.domain.Specification;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Model.LZOffer;

public class OfferSpecification {
    public static Specification<LZOffer> Search(OfferFilterDataModel model){
        return (root,query,cb)->{
            if(model.getSearch() ==null){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("label")),"%"+model.getSearch()+"%" );
        };
    }
    public static Specification<LZOffer> SearchDetail(OfferFilterDataModel model){
        return (root,query,cb)->{
            if(model.getSearch() ==null){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("detail")),"%"+model.getSearch()+"%" );
        };
    }
}
