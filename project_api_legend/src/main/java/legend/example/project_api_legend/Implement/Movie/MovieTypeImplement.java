package legend.example.project_api_legend.Implement.Movie;

import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Movie.MovieTypeDataModel;
import legend.example.project_api_legend.DataModel.Movie.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Dto.MovieTypeDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.Movie.MovieTypeService;
import legend.example.project_api_legend.MappingData.MovieTypeMap;
import legend.example.project_api_legend.Model.LZMovieType;
import legend.example.project_api_legend.Repository.LZMovieTypeRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MovieTypeImplement implements MovieTypeService{
    private LZMovieTypeRepository lzMovieTypeRepository;
    @Override
    public java.util.List<MovieTypeDto> List(MovieTypeFilterDataMode filter) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public MovieTypeDto Create(MovieTypeDataModel model) {
        model.setCreateBy(LZGlobalHelper.Text.Admin);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        model.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        var data =  lzMovieTypeRepository.save(MovieTypeMap.MapToTable(model));
        return MappingData(data);
    }
  
    @Override
    public MovieTypeDto Update(MovieTypeDataModel model) {
        var data =  lzMovieTypeRepository.findById(model.getId()).get();
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdateBy(LZGlobalHelper.Text.Admin);
        data.setUpdateDate(LZGlobalHelper.LZDate.DateNow);
        return MappingData(data);
    }
    @Override
    public boolean Delete(MovieTypeDataModel model) {
        // TODO Auto-generated method stub
        return false;
    }
    public static MovieTypeDto MappingData(LZMovieType db){
        MovieTypeDto data = new MovieTypeDto();
        data.setId(db.getId());
        data.setName(db.getName());
        data.setEnglishName(db.getEnglishName());
        data.setCreateBy(db.getCreateBy());
        data.setUpdateBy(db.getUpdateBy());
        data.setCreateDate(db.getCreateDate());
        data.setUpdateDate(db.getUpdateDate());
        data.setDatabase(db.getDatabase());
        return data;
    }
   
}

