package legend.example.project_api_legend.Implement.Movie;

import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Movie.Movie.MovieDataModel;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.Dto.MovieDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.MovieService;
import legend.example.project_api_legend.MappingData.MovieMap;
import legend.example.project_api_legend.Model.LZMovie;
import legend.example.project_api_legend.Repository.LZMovieRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MovieImplement implements MovieService {
    private LZMovieRepository lzMovieRepository;
    @Override
    public java.util.List<MovieDto> List(MovieFilterDataModel filter) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public MovieDto Create(MovieDataModel model) {
        model.setCreateBy(LZGlobalHelper.Text.Admin);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        var create =  lzMovieRepository.save(MovieMap.MapToTable(model));
        return MappingData(create,1);
    }
 
    @Override
    public MovieDto Update(MovieDataModel model) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean Delete(Long Id) {
        // TODO Auto-generated method stub
        return false;
    }
    public static MovieDto MappingData(LZMovie d,int RecordCount){
        var data = new MovieDto();
        data.setId(d.getId());
        data.setMovieTypeId(d.getMovieTypeId());
        data.setName(d.getName());
        data.setEnglishName(d.getEnglishName());
        data.setDuration(d.getDuration());
        data.setRelease(d.getRelease());
        data.setImagePath(d.getImagePath());
        data.setFromDate(d.getFromDate());
        data.setToDate(d.getToDate());
        data.setUrlYT(d.getUrlYoutube());
        data.setDescription(d.getDescription());
        data.setRecordCount(RecordCount);
        data.setCreateBy(d.getCreateBy());
        data.setUpdateBy(d.getUpdateBy());
        data.setCreateDate(d.getCreateDate());
        data.setUpdateDate(d.getUpdateDate());
        return data;
    }
}
