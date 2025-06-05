package legend.example.project_api_legend.Implement.Movie;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeDataModel;
import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Dto.LZModuleSetting.MovieTypeDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.MovieTypeService;
import legend.example.project_api_legend.MappingData.MovieTypeMap;
import legend.example.project_api_legend.Model.LZMovieType;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import legend.example.project_api_legend.Repository.LZMovieTypeRepository;
import legend.example.project_api_legend.Specifications.MovieTypeSpecification;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MovieTypeImplement implements MovieTypeService{
    private LZMovieTypeRepository lzMovieTypeRepository;
    @Override
    public java.util.List<MovieTypeDto> List(MovieTypeFilterDataMode filter) {
        Specification<LZMovieType> list =  Specification.where(MovieTypeSpecification.Search(filter));
        Sort sort = Sort.by(Direction.DESC, "id");
        var types= lzMovieTypeRepository.findAll(list,sort);
        if(filter.getPages()!=null && filter.getRecords()!=null){
            System.out.println("work here");
            var skip = types.stream().skip((filter.getPages()-1) * filter.getRecords()).limit(filter.getRecords()).toList();
            return skip.stream().map(s->MappingData(s,types.size())).toList();
        }
        return types.stream().map(s->MappingData(s,types.size())).toList();
    }
    @Override
    public MovieTypeDto Create(MovieTypeDataModel model) {
        model.setCreateBy(LZGlobalHelper.Text.Admin);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        model.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        var data =  lzMovieTypeRepository.save(MovieTypeMap.MapToTable(model));
        return MappingData(data,1);
    }
  
    @Override
    public MovieTypeDto Update(MovieTypeDataModel model) {
        var data =  lzMovieTypeRepository.findById(model.getId()).get();
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdateBy(LZGlobalHelper.Text.Admin);
        data.setUpdateDate(LZGlobalHelper.LZDate.DateNow);
        lzMovieTypeRepository.save(data);
        return MappingData(data,1);
    }
    @Override
    public boolean Delete(Long Id) {
        lzMovieTypeRepository.deleteById(Id);
        return true;
    }
    public static MovieTypeDto MappingData(LZMovieType db,int recordCount){
        MovieTypeDto data = new MovieTypeDto();
        data.setId(db.getId());
        data.setName(db.getName());
        data.setEnglishName(db.getEnglishName());
        data.setCreateBy(db.getCreateBy());
        data.setUpdateBy(db.getUpdateBy());
        data.setCreateDate(db.getCreateDate());
        data.setUpdateDate(db.getUpdateDate());
        data.setDatabase(db.getDatabase());
        data.setRecordCount(recordCount);
        return data;
    }
   
}

