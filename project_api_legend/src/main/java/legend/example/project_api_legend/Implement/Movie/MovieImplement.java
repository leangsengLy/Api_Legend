package legend.example.project_api_legend.Implement.Movie;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieDataModel;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.Dto.Setting.MovieDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Setting.MovieHelper;
import legend.example.project_api_legend.Interface.MovieService;
import legend.example.project_api_legend.MappingData.MovieMap;
import legend.example.project_api_legend.Model.LZMovie;
import legend.example.project_api_legend.Repository.LZMovieRepository;
import legend.example.project_api_legend.Specifications.Movie.MovieSpecification;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MovieImplement implements MovieService {
    private LZMovieRepository lzMovieRepository;
    @Override
    public java.util.List<MovieDto> List(MovieFilterDataModel filter) {
        Specification<LZMovie> list = Specification.where(MovieSpecification.Search(filter)).and(MovieSpecification.FromDateToDate(filter));
        if(filter.getId()!=null) list = list.and(MovieSpecification.getById(filter.getId()));
        if(filter.getFromDate()!=null && filter.getToDate()!=null) {
            list = list.and(MovieSpecification.FromDateToDate(filter)).and(MovieSpecification.ToDate(filter));
        }
        if(filter.getRelease()!=null) list = list.and(MovieSpecification.ReleaseDate(filter.getRelease()));
        if(filter.getDuration()!=null) list = list.and(MovieSpecification.Duration(filter.getDuration()));
        if(filter.getMovieTypeId() > 0) list = list.and(MovieSpecification.MovieTypeId(filter.getMovieTypeId()));
        Sort sort = Sort.by(Direction.DESC, "id");
        var data= lzMovieRepository.findAll(list,sort);
        // if(filter.getRecords()!=null && filter.getPages()!=null && filter.getPages()>0 && filter.getRecords()>0){
        //    var listRecord = data.stream().skip((filter.getPages()-1) * filter.getRecords()).limit(filter.getRecords()).toList();
        //    return listRecord.stream().map(s->MappingData(s, data.size())).toList();
        // }
        return data.stream().map(s->MappingData(s, data.size())).toList();
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
        var findMovie = lzMovieRepository.findById(model.getId()).get();
        findMovie.setMovieTypeId(model.getMovieTypeId());
        findMovie.setName(model.getName());
        findMovie.setEnglishName(model.getEnglishName());
        findMovie.setDuration(model.getDuration());
        findMovie.setRelease(model.getRelease());
        if(model.getUploadFileDataModel().getBase64Data()!=null){
            findMovie.setImagePath(model.getImagePath());
        }
        findMovie.setFromDate(model.getFromDate());
        findMovie.setToDate(model.getToDate());
        findMovie.setUrlYoutube(model.getUrlYT());
        findMovie.setDescription(model.getDescription());
        findMovie.setUpdateBy(LZGlobalHelper.Text.Admin);
        findMovie.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        var data = lzMovieRepository.save(findMovie);
        return MappingData(data,1);
    }
    @Override
    public boolean RemoveImage(Long Id) {
        var findMovie = lzMovieRepository.findById(Id).get();
        List<String> fileName = Arrays.asList(findMovie.getImagePath().split("/"));
        var LastIndex = fileName.size()-1;
        UploadFileData.deleteImage(fileName.get(LastIndex), MovieHelper.Text.folderMovie);
        findMovie.setImagePath(null);
        lzMovieRepository.save(findMovie);
        return true;
    }
    @Override
    public Boolean UploadImage(MovieDataModel model) {
        var find = lzMovieRepository.findById(model.getId()).get();
        find.setImagePath(model.getImagePath());
        lzMovieRepository.save(find);
        return true;
    }
    @Override
    public boolean Delete(Long Id) {
        lzMovieRepository.deleteById(Id);
        return true;
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
