package legend.example.project_api_legend.Interface.Movie;
import java.util.*;

import legend.example.project_api_legend.DataModel.Movie.MovieTypeDataModel;
import legend.example.project_api_legend.DataModel.Movie.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Dto.MovieTypeDto;

public interface MovieTypeService {
    List<MovieTypeDto> List(MovieTypeFilterDataMode filter);
    MovieTypeDto Create(MovieTypeDataModel model);
    MovieTypeDto Update(MovieTypeDataModel model);
    boolean Delete(Long Id);
}
