package legend.example.project_api_legend.MappingData;

import legend.example.project_api_legend.DataModel.Movie.MovieTypeDataModel;
import legend.example.project_api_legend.Model.LZMovieType;

public class MovieTypeMap {
    public static LZMovieType MapToTable(MovieTypeDataModel model){
        var data = new LZMovieType();
        data.setId(model.getId());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setDatabase(model.getDatabase());
        data.setCreateBy(model.getCreateBy());
        data.setCreateDate(model.getCreateDate());
        data.setUpdateBy(model.getUpdateBy());
        data.setUpdateDate(model.getUpdateDate());
        return data;
    }
    
}
