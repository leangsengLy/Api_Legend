package legend.example.project_api_legend.Implement;

import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Dto.OfferDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.OfferService;
import legend.example.project_api_legend.Model.LZOffer;
import legend.example.project_api_legend.Repository.OfferRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OfferImplement implements OfferService {
    private OfferRepository offerRepository;
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
    public java.util.List<LZOffer> List(OfferFilterDataModel model) {
        // TODO Auto-generated method stub
        return null;
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
