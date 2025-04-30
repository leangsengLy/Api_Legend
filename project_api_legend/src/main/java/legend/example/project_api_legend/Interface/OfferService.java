package legend.example.project_api_legend.Interface;

import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Dto.OfferDto;
import legend.example.project_api_legend.Model.LZOffer;
import java.util.*;
public interface OfferService {
    OfferDto Create(OfferDataModel model);
    OfferDto Update(OfferDataModel model);
    Boolean Delete(Long Id);
    List<LZOffer> List(OfferFilterDataModel model);
} 
