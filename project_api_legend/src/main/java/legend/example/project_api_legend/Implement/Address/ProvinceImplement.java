package legend.example.project_api_legend.Implement.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Address.Province.ProvinceDataModel;
import legend.example.project_api_legend.DataModel.Address.Province.ProvinceFilterDataModel;
import legend.example.project_api_legend.Dto.Address.ProvinceDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.Address.ProvinceService;
import legend.example.project_api_legend.MappingData.Address.ProvinceDataMapping;
import legend.example.project_api_legend.Repository.Address.LZProvinceRepository;
import legend.example.project_api_legend.Specifications.Address.ProvinceSpecification;
import lombok.AllArgsConstructor;

import java.util.*;
@AllArgsConstructor
@Service
public class ProvinceImplement implements ProvinceService {
    private LZProvinceRepository lzProvinceRepository;
     @Override
    public List<ProvinceDto> List(ProvinceFilterDataModel filter) {
        var spec = Specification.where(ProvinceSpecification.SearchProvince(filter.getSearch()));
         Sort sort = Sort.by(Direction.DESC, "id");
         var list = lzProvinceRepository.findAll(spec,sort);
        if(filter.getRecords()>0 && filter.getPages()>0){
            var  datas = list.stream().skip((filter.getPages() - 1 )* filter.getRecords()).limit(filter.getRecords()).toList();
            return datas.stream().map(s->ProvinceDataMapping.MappingToDto(s, list.size())).toList();
        }
        return list.stream().map(s->ProvinceDataMapping.MappingToDto(s, list.size())).toList();
    }
    @Override
    public ProvinceDto Create(ProvinceDataModel model) {
       model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
       var dt = ProvinceDataMapping.MappingToTable(model);
       var data = lzProvinceRepository.save(dt);
        return ProvinceDataMapping.MappingToDto(data, 1);
    }
     @Override
    public ProvinceDto Update(ProvinceDataModel model) {
        var data = lzProvinceRepository.findById(model.getId()).get();
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdatedBy(model.getCreateBy());
        data.setUpdatedDate(LZGlobalHelper.LZDate.DateNow);
        var result = lzProvinceRepository.save(data);
        return ProvinceDataMapping.MappingToDto(result,1);
    }
    @Override
    public Boolean Delete(Long Id) {
        lzProvinceRepository.deleteById(Id);
        return true;
    }
}
