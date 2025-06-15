package legend.example.project_api_legend.Interface.Address;

import legend.example.project_api_legend.DataModel.Address.District.DistrictDataModel;
import legend.example.project_api_legend.DataModel.Address.District.DistrictFilterDataModel;
import legend.example.project_api_legend.Dto.Address.DistrictDto;

import java.util.*;
public interface DistrictService {
    List<DistrictDto> List(DistrictFilterDataModel filter);
    DistrictDto Create(DistrictDataModel model);
    DistrictDto Update(DistrictDataModel model);
    Boolean Delete(Long Id);
    boolean CheckCode(String code,Long Id,String database);
}
