package legend.example.project_api_legend.Interface;

import java.util.List;

import legend.example.project_api_legend.DataModel.Movie.Movie.MovieDataModel;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.Dto.LZModuleSetting.MovieDto;

public interface MovieService {
    List<MovieDto> List(MovieFilterDataModel filter);
    MovieDto Create(MovieDataModel model);
    MovieDto Update(MovieDataModel model);
    boolean Delete(Long Id);
    boolean RemoveImage(Long ImagePath);
    Boolean UploadImage(MovieDataModel model);
} 
