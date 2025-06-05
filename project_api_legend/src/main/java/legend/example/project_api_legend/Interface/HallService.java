package legend.example.project_api_legend.Interface;
import java.util.*;

import legend.example.project_api_legend.DataModel.Hall.HallDataModel;
import legend.example.project_api_legend.DataModel.Hall.HallFilterDataModel;
import legend.example.project_api_legend.Dto.LZModuleSetting.HallDto;

public interface HallService {
    List<HallDto> List(HallFilterDataModel filter);
    HallDto Create(HallDataModel model);
    HallDto Update(HallDataModel model);
    String Delete(Long Id);
    Boolean CheckCode(String Code,Long Id);
} 
