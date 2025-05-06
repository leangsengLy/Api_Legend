package legend.example.project_api_legend.Interface;
import java.util.*;

import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeDataModel;
import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Dto.MovieTypeDto;

public interface MovieTypeService {
    List<MovieTypeDto> List(MovieTypeFilterDataMode filter);
    MovieTypeDto Create(MovieTypeDataModel model);
    MovieTypeDto Update(MovieTypeDataModel model);
    boolean Delete(Long Id);
}
