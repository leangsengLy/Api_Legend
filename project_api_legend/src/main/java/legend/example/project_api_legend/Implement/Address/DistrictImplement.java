package legend.example.project_api_legend.Implement.Address;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Address.District.DistrictDataModel;
import legend.example.project_api_legend.DataModel.Address.District.DistrictFilterDataModel;
import legend.example.project_api_legend.Dto.Address.DistrictDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.Address.DistrictService;
import legend.example.project_api_legend.MappingData.Address.DistrictDataMapping;
import legend.example.project_api_legend.Model.Address.LZDistrict;
import legend.example.project_api_legend.Repository.Address.LZDistrictRepository;
import legend.example.project_api_legend.Specifications.Address.DistrictSpecification;
import lombok.AllArgsConstructor;

import java.util.*;
@Service
@AllArgsConstructor
public class DistrictImplement implements DistrictService {
    private LZDistrictRepository lzDistrictRepository;
     @Override
    public List<DistrictDto> List(DistrictFilterDataModel filter) {
        var spec = Specification.where(DistrictSpecification.Search(filter.getSearch()));
         Sort sort = Sort.by(Direction.DESC, "id");
         var list = lzDistrictRepository.findAll(spec,sort);
        if(filter.getRecords()>0 && filter.getPages()>0){
            var  datas = list.stream().skip((filter.getPages() - 1 )* filter.getRecords()).limit(filter.getRecords()).toList();
            return datas.stream().map(s->DistrictDataMapping.MappingToDto(s, datas.size())).toList();
        }
        return list.stream().map(s->DistrictDataMapping.MappingToDto(s, list.size())).toList();
    }
    @Override
    public DistrictDto Create(DistrictDataModel model) {
       model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
       var dt = DistrictDataMapping.MappingToTable(model);
       var data = lzDistrictRepository.save(dt);
        return DistrictDataMapping.MappingToDto(data, 1);
    }
     @Override
    public DistrictDto Update(DistrictDataModel model) {
        var data = lzDistrictRepository.findById(model.getId()).get();
        data.setCode(model.getCode());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdatedBy(model.getCreateBy());
        data.setUpdatedDate(LZGlobalHelper.LZDate.DateNow);
        var result = lzDistrictRepository.save(data);
        return DistrictDataMapping.MappingToDto(result,1);
    }
    @Override
    public Boolean Delete(Long Id) {
        lzDistrictRepository.deleteById(Id);
        return true;
    }
    @Override
    public boolean CheckCode(String code, Long Id,String database) {
        var list = lzDistrictRepository.findAll();
        if(Id>1)  list = list.stream().filter(s->s.getId()!=Id).toList();
        list = list.stream().filter(s->s.getCode().equals(code)).toList();
        List<LZDistrict> isHasMore = null;
        if(database==LZGlobalHelper.Text.GlobalDatabase)isHasMore = list.stream().filter(s->s.getDatabase().equals(LZGlobalHelper.Text.GlobalDatabase )|| s.getDatabase().equals(database)).toList();
        else  isHasMore = list.stream().filter(s->s.getDatabase().equals(database)).toList();
        return isHasMore.size()>0;
    }
    
}
