package legend.example.project_api_legend.MappingData;

import legend.example.project_api_legend.DataModel.Movie.Movie.MovieDataModel;
import legend.example.project_api_legend.Model.LZMovie;

public class MovieMap {
    public static LZMovie MapToTable(MovieDataModel model){
        var data = new LZMovie();
        data.setMovieTypeId(model.getMovieTypeId());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setDuration(model.getDuration());
        data.setRelease(model.getRelease());
        data.setImagePath(model.getImagePath());
        data.setFromDate(model.getFromDate());
        data.setToDate(model.getToDate());
        data.setUrlYoutube(model.getUrlYT());
        data.setDescription(model.getDescription());
        data.setCreateBy(model.getCreateBy());
        data.setCreateDate(model.getCreateDate());
        data.setUpdateBy(model.getUpdateBy());
        data.setUpdateDate(model.getUpdateDate());
        return data;
    }
}
