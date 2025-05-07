package legend.example.project_api_legend.Specifications.Movie;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.Model.LZMovie;

public class MovieSpecification {
    public static Specification<LZMovie> Search(MovieFilterDataModel filter){
        return (root,query,cb)->{
            if(filter.getSearch()==null) return cb.conjunction();
            return cb.or(
                cb.like(cb.lower(root.get("englishName")), "%"+filter.getSearch()+"%"),
                cb.like(cb.lower(root.get("name")), "%"+filter.getSearch()+"%")
            );
        };
    }
    public static Specification<LZMovie> FromDateToDate(MovieFilterDataModel filter){
        return (root,query,cb)->{
            if(filter.getFromDate()==null || filter.getToDate() == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get("fromDate"), filter.getFromDate());
        };
    }

    public static Specification<LZMovie> ToDate(MovieFilterDataModel filter){
        return (root,query,cb)->{
            if(filter.getFromDate()==null || filter.getToDate() == null) return cb.conjunction();
            return cb.lessThanOrEqualTo(root.get("toDate"), filter.getToDate());
        };
    }
    public static Specification<LZMovie> getById(Long Id){
     
        return (root,query,cb)->{
            if(Id<1) return cb.conjunction();
            return cb.equal(root.get("id"), Id);
        };
    }
    public static Specification<LZMovie> ReleaseDate(Date date){
        return (root,query,cb)->{
            return cb.equal(root.get("release"), date);
        };
    }
    public static Specification<LZMovie> Duration(Long duration){
        return (root,query,cb)->{
            return cb.equal(root.get("duration"), duration);
        };
    }
}
