package legend.example.project_api_legend.Implement.Address;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Address.Commune.CommuneDataModel;
import legend.example.project_api_legend.DataModel.Address.Commune.CommuneFilterDataModel;
import legend.example.project_api_legend.Dto.Address.CommuneDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.Address.CommuneService;
import legend.example.project_api_legend.MappingData.Address.CommuneDataMapping;
import legend.example.project_api_legend.Repository.Address.LZCommuneRepository;
import legend.example.project_api_legend.Specifications.Address.CommuneSpecification;
import lombok.AllArgsConstructor;

import java.util.*;
@Service
@AllArgsConstructor
public class CommuneImplement implements CommuneService {
    private LZCommuneRepository lzCommuneRepository;
     @Override
    public List<CommuneDto> List(CommuneFilterDataModel filter) {
        var spec = Specification.where(CommuneSpecification.Search(filter.getSearch()));
        Sort sort = Sort.by(Direction.DESC, "id");

         var list = lzCommuneRepository.findAll(spec,sort);
        if(filter.getRecords()>0 && filter.getPages()>0){
            var  datas = list.stream().skip((filter.getPages() - 1 )* filter.getRecords()).limit(filter.getRecords()).toList();
            return datas.stream().map(s->CommuneDataMapping.MappingToDto(s, datas.size())).toList();
        }
        return list.stream().map(s->CommuneDataMapping.MappingToDto(s, list.size())).toList();
    }
    @Override
    public CommuneDto Create(CommuneDataModel model) {
       model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
       var dt = CommuneDataMapping.MappingToTable(model);
       var data = lzCommuneRepository.save(dt);
        return CommuneDataMapping.MappingToDto(data, 1);
    }
     @Override
    public CommuneDto Update(CommuneDataModel model) {
        var data = lzCommuneRepository.findById(model.getId()).get();
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdatedBy(model.getCreateBy());
        data.setUpdatedDate(LZGlobalHelper.LZDate.DateNow);
        var result = lzCommuneRepository.save(data);
        return CommuneDataMapping.MappingToDto(result,1);
    }
    @Override
    public Boolean Delete(Long Id) {
        lzCommuneRepository.deleteById(Id);
        return true;
    }
}
