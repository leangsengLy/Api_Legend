package legend.example.project_api_legend.Implement.Address;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Address.Country.CountryDataModel;
import legend.example.project_api_legend.DataModel.Address.Country.CountryFilterDataModel;
import legend.example.project_api_legend.Dto.Address.CountryDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Address.CountryHelper;
import legend.example.project_api_legend.Interface.Address.CountryService;
import legend.example.project_api_legend.MappingData.Address.CountryDataMapping;
import legend.example.project_api_legend.Model.Address.LZCountry;
import legend.example.project_api_legend.Repository.Address.LZCountryRepository;
import legend.example.project_api_legend.Specifications.Address.CountrySpecification;
import lombok.AllArgsConstructor;

import java.util.*;
@Service
@AllArgsConstructor
public class CountryImplement implements CountryService {
    private LZCountryRepository lzCountryRepository;
     @Override
    public List<CountryDto> List(CountryFilterDataModel filter) {
        var spec = Specification.where(CountrySpecification.SearchFood(filter.getSearch()));
         Sort sort = Sort.by(Direction.DESC, "id");
         var list = lzCountryRepository.findAll(spec,sort);
        if(filter.getRecords()>0 && filter.getPages()>0){
            var  datas = list.stream().skip((filter.getPages() - 1 )* filter.getRecords()).limit(filter.getRecords()).toList();
            return datas.stream().map(s->CountryDataMapping.MappingToDto(s, datas.size())).toList();
        }
        return list.stream().map(s->CountryDataMapping.MappingToDto(s, list.size())).toList();
    }
    @Override
    public CountryDto Create(CountryDataModel model) {
       model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
       var dt = CountryDataMapping.MappingToTable(model);
       var data = lzCountryRepository.save(dt);
        return CountryDataMapping.MappingToDto(data, 1);
    }
     @Override
    public CountryDto Update(CountryDataModel model) {
        var data = lzCountryRepository.findById(model.getId()).get();
        data.setCode(model.getCode());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setUpdatedBy(model.getCreateBy());
        data.setUpdatedDate(LZGlobalHelper.LZDate.DateNow);
        var result = lzCountryRepository.save(data);
        return CountryDataMapping.MappingToDto(result,1);
    }
    @Override
    public Boolean Delete(Long Id) {
        lzCountryRepository.deleteById(Id);
        return true;
    }
    @Override
    public boolean CheckCode(String code, Long Id,String database) {
        var list = lzCountryRepository.findAll();
        if(Id>1)  list = list.stream().filter(s->s.getId()!=Id).toList();
        list = list.stream().filter(s->s.getCode().equals(code)).toList();
        List<LZCountry> isHasMore = null;
        if(database==LZGlobalHelper.Text.GlobalDatabase)isHasMore = list.stream().filter(s->s.getDatabase().equals(LZGlobalHelper.Text.GlobalDatabase )|| s.getDatabase().equals(database)).toList();
        else  isHasMore = list.stream().filter(s->s.getDatabase().equals(database)).toList();
        return isHasMore.size()>0;
    }

    @Override
    public Boolean RemoveImage(Long Id){
        LZCountry find = lzCountryRepository.findById(Id).get();
        List<String> fileName = Arrays.asList(find.getImagePath().split("/"));
        int size = Arrays.asList(find.getImagePath().split("/")).size();
        UploadFileData.deleteImage(fileName.get(size-1), CountryHelper.StrText.folderCountry);
        find.setImagePath(null);
        lzCountryRepository.save(find);
        return true;
    }
    
}
