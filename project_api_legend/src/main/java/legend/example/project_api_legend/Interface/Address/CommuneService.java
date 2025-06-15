package legend.example.project_api_legend.Interface.Address;

import legend.example.project_api_legend.DataModel.Address.Commune.CommuneDataModel;
import legend.example.project_api_legend.DataModel.Address.Commune.CommuneFilterDataModel;
import legend.example.project_api_legend.Dto.Address.CommuneDto;

import java.util.*;
public interface CommuneService {
    List<CommuneDto> List(CommuneFilterDataModel filter);
    CommuneDto Create(CommuneDataModel model);
    CommuneDto Update(CommuneDataModel model);
    Boolean Delete(Long Id);
}
