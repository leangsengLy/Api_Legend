package legend.example.project_api_legend.Interface;

import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Dto.Media.OfferDto;

import java.util.*;
public interface OfferService {
    OfferDto Create(OfferDataModel model);
    OfferDto Update(OfferDataModel model);
    Boolean Delete(Long Id);
    List<OfferDto> List(OfferFilterDataModel model);
} 
