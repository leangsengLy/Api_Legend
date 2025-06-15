package legend.example.project_api_legend.Interface.Address;

import legend.example.project_api_legend.DataModel.Address.Province.ProvinceDataModel;
import legend.example.project_api_legend.DataModel.Address.Province.ProvinceFilterDataModel;
import legend.example.project_api_legend.Dto.Address.ProvinceDto;

import java.util.*;
public interface ProvinceService {
    List<ProvinceDto> List(ProvinceFilterDataModel filter);
    ProvinceDto Create(ProvinceDataModel filter);
    ProvinceDto Update(ProvinceDataModel filter);
    Boolean Delete(Long Id);
}
