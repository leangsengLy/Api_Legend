package legend.example.project_api_legend.Implement.Setting;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.*;
import legend.example.project_api_legend.DataModel.Hall.HallDataModel;
import legend.example.project_api_legend.DataModel.Hall.HallFilterDataModel;
import legend.example.project_api_legend.Dto.Setting.HallDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.HallService;
import legend.example.project_api_legend.MappingData.Setting.HallMapping;
import legend.example.project_api_legend.Model.LZHall;
import legend.example.project_api_legend.Repository.LZHallRepository;
import legend.example.project_api_legend.Specifications.Setting.HallSpecification;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HallImplement implements HallService {
    private LZHallRepository lzHallRepository;
    public List<HallDto> List(HallFilterDataModel filter){
        Sort sort = Sort.by(Sort.Direction.DESC, "ID");
        Specification<LZHall> spec = Specification.where(HallSpecification.Search(filter.getSearch()));
        if(filter.getIsActive()!=null){
            spec = spec.and(HallSpecification.IsActive(filter.getIsActive()));
        }
        var list = lzHallRepository.findAll(spec,sort);
        var size = list.size();
        if(filter.getPages() !=null && filter.getPages()>0 && filter.getRecords() !=null && filter.getRecords()>0 ){
            list = list.stream().skip((filter.getPages() - 1) * filter.getRecords()).limit(filter.getRecords()).toList();
        }
        return list.stream().map(s->HallMapping.MapToDto(s,size)).toList();
    }
    public HallDto Create(HallDataModel model){
        model.setCreateBy(LZGlobalHelper.Text.Admin);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        model.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        var data = HallMapping.MappToTable(model);
        lzHallRepository.save(data);
        return HallMapping.MapToDto(data,1);
        
    }
    public HallDto Update(HallDataModel model){
         var data = lzHallRepository.findById(model.getId()).get();
            data.setCODE(model.getCode());
            data.setNAME(model.getName());
            data.setEN_NAME(model.getEnglishName());
            data.setTOTAL_CHAIRS(model.getTotalChairs());
            data.setIS_ACTIVE(model.getIsActive());
            data.setUPDATE_BY(LZGlobalHelper.Text.Admin);
            data.setUPDATE_DATE(LZGlobalHelper.LZDate.DateNow);
            lzHallRepository.save(data);
        return HallMapping.MapToDto(data,1);
    }
    public String Delete(Long Id){
        lzHallRepository.deleteById(Id);
        return "Success";
    }
    public Boolean CheckCode(String Code,Long Id){
        var checkCode =lzHallRepository.findAll(HallSpecification.listByCode(Code));
        if(Id>0) checkCode = checkCode.stream().filter(s->s.getID()!=Id).toList();
        if(checkCode.isEmpty()) return false;
        return true;
    }  
}
