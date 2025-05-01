package legend.example.project_api_legend.Implement;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Dto.OfferDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.OfferService;
import legend.example.project_api_legend.Model.LZOffer;
import legend.example.project_api_legend.Repository.OfferRepository;
import legend.example.project_api_legend.Specifications.OfferSpecification;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OfferImplement implements OfferService {
    private OfferRepository offerRepository;
    @Override
    public List<OfferDto> List(OfferFilterDataModel model) {
        Specification<LZOffer> all = Specification.where(OfferSpecification.Search(model.getSearch()));
        Sort sort = Sort.by(Direction.DESC,"id");
        List<LZOffer> list = offerRepository.findAll(all,sort);
        if(model.getPages()>0 && model.getRecords()>0){
           var listOffer = list.stream().skip((model.getPages()-1) * model.getRecords()).limit(model.getRecords()).toList();
           return listOffer.stream().map((val)->MappingData(val, list.size())).toList();
        }
        return list.stream().map((val)->MappingData(val, list.size())).toList();
    }
    @Override
    public OfferDto Create(OfferDataModel model) {
        LZOffer obj = new LZOffer();
        obj.setLabel(model.getLabel());
        obj.setDetail(model.getDetail());
        obj.setPathImage(model.getPathImage());
        obj.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        obj.setCreateBy(LZGlobalHelper.Text.Admin);
        obj.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        offerRepository.save(obj);
        return MappingData(obj,1);
    }
  
    @Override
    public OfferDto Update(OfferDataModel model) {
        LZOffer find = offerRepository.findById(model.getId()).get();
        find.setLabel(model.getLabel());
        find.setPathImage(model.getPathImage());
        find.setDetail(model.getDetail());
        find.setUpdateBy(LZGlobalHelper.Text.Admin);
        find.setUpdateDate(LZGlobalHelper.LZDate.DateNow);
        offerRepository.save(find);
        return MappingData(find,1);
    }

    public static OfferDto  MappingData(LZOffer obj,int RecordCount){
        OfferDto data = new OfferDto();
        data.setId(obj.getId());
        data.setLabel(obj.getLabel());
        data.setDetail(obj.getDetail());
        data.setDatabase(obj.getDatabase());
        data.setPathImage(obj.getPathImage());
        data.setCreateBy(obj.getCreateBy());
        data.setCreateDate(obj.getCreateDate());
        data.setUpdateBy(obj.getUpdateBy());
        data.setRecordCount(RecordCount);
        return data;
    }
    @Override
    public Boolean Delete(Long Id) {
        offerRepository.deleteById(Id);
        return true;
    }
} 
