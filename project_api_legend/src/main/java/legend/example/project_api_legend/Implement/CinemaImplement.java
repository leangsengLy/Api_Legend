package legend.example.project_api_legend.Implement;

import java.util.*;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Cinema.CinemaDataModel;
import legend.example.project_api_legend.DataModel.Cinema.CinemaFilterDataModel;
import legend.example.project_api_legend.Dto.Cinema.CinemaDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Cinema.CinemaHelper;
import legend.example.project_api_legend.Interface.CinemaService;
import legend.example.project_api_legend.Model.LZCinema;
import legend.example.project_api_legend.Repository.LZCinemaRepository;
import legend.example.project_api_legend.Specifications.CinemaSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

@Service
@AllArgsConstructor
public class CinemaImplement implements CinemaService  {
    private LZCinemaRepository lzCinemaRepository;
    @Override
    public CinemaDto Create(CinemaDataModel model) {
        model.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        model.setCreateby(LZGlobalHelper.Text.Admin);
        model.setLocalhost(LZGlobalHelper.Text.localUrl);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        LZCinema cinema = lzCinemaRepository.save(MapToTable(model));
        return MappingData(cinema,1);
    }
    @Override
    public List<CinemaDto> List(CinemaFilterDataModel filter) {
        Specification<LZCinema> list = Specification.where(CinemaSpecification.ListFood(filter.getId())).and(CinemaSpecification.Search(filter.getSearch()));
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<LZCinema> result = lzCinemaRepository.findAll(list,sort);
        return result.stream().map(val->MappingData(val,result.size())).toList();   
    }

    @Override
    public Boolean CheckCode(String code){
        Optional<LZCinema> checkCode = lzCinemaRepository.findByCode(code);
        return checkCode.isPresent();
    }
    @Override
    public Boolean DeleteImage(Long Id){
        LZCinema find = lzCinemaRepository.findById(Id).get();
        List<String> fileName = Arrays.asList(find.getPathImage().split("/"));
        int size = Arrays.asList(find.getPathImage().split("/")).size();
        find.setPathImage(null);
        find.setLocalhost(null);
        UploadFileData.deleteImage(fileName.get(size-1), CinemaHelper.StrText.FolderBranch);
        lzCinemaRepository.save(find);
        return true;
    }

    @Override
    public CinemaDto Update(CinemaDataModel model) {
        LZCinema cinema = lzCinemaRepository.findById(model.getId()).get();
        cinema.setAddress(model.getAddress());
        cinema.setName(model.getName());
        cinema.setEnName(model.getEnglishName());
        cinema.setStartTime(model.getStartTime());
        cinema.setEndTime(model.getEndTime());
        cinema.setPathImage(model.getPathImage());
        cinema.setLatMap(model.getLatMap());
        cinema.setLongMap(model.getLongMap());
        cinema.setStartTime(model.getStartTime());
        cinema.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        cinema.setUpdateBy(LZGlobalHelper.Text.Admin);
        cinema.setUpdateDate(LZGlobalHelper.LZDate.DateNow);
        lzCinemaRepository.save(cinema);
        return MappingData(cinema,1);
    }

    public static CinemaDto MappingData(LZCinema data,int recordCount){
        var obj = new CinemaDto();
            obj.setId(data.getId());
            obj.setName(data.getName());
            obj.setCode(data.getCode());
            obj.setLocalhost(data.getLocalhost());
            obj.setEnglishName(data.getEnName());
            obj.setPathImage(data.getPathImage());
            obj.setAddress(data.getAddress());
            obj.setStartTime(data.getStartTime());
            obj.setEndTime(data.getEndTime());
            obj.setLatMap(data.getLatMap());
            obj.setLongMap(null);
            obj.setRecordCount(recordCount);
            obj.setDatabase(data.getDatabase());
            obj.setCreateBy(data.getCreateBy());
            obj.setUpdateBy(data.getUpdateBy());
            obj.setUpdateDate(data.getUpdateDate());
            obj.setCreateDate(data.getCreateDate());
        return obj;
        
        
    }
    public static LZCinema MapToTable(CinemaDataModel data){
        return new LZCinema(data.getId(), data.getName(), data.getEnglishName(),data.getCode(),data.getPathImage(),data.getLocalhost(), data.getAddress(), data.getStartTime(), data.getEndTime(), data.getLatMap(), data.getLongMap(), data.getDatabase(), data.getCreateby(), data.getUpdateBy(), data.getCreateDate(), data.getUpdateDate());
    }
    @Override
    public Boolean Delete(Long Id) {
        lzCinemaRepository.deleteById(Id);
        return true;
    }
}
