package legend.example.project_api_legend.Specifications;

import org.springframework.data.jpa.domain.Specification;

import legend.example.project_api_legend.DataModel.Movie.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Model.LZMovieType;

public class MovieTypeSpecification {
    public static Specification<LZMovieType> Search(MovieTypeFilterDataMode filter){
        return (root,query,cb)->{
            if(filter.getSearch()==null) return cb.conjunction();
            return cb.or(
                cb.like(cb.lower(root.get("name")), "%"+filter.getSearch()+"%"),
                cb.like(cb.lower(root.get("englishName")), "%"+filter.getSearch()+"%")
            );
        };
    }
}
