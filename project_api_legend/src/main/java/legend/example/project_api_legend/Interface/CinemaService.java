package legend.example.project_api_legend.Interface;

import java.util.List;

import legend.example.project_api_legend.DataModel.Cinema.CinemaDataModel;
import legend.example.project_api_legend.DataModel.Cinema.CinemaFilterDataModel;
import legend.example.project_api_legend.Dto.Cinema.CinemaDto;

public interface CinemaService {
    List<CinemaDto> List(CinemaFilterDataModel filter);
    CinemaDto Create(CinemaDataModel model);
    CinemaDto Update(CinemaDataModel model);
    Boolean CheckCode(String code);
    Boolean DeleteImage(Long Id);
    Boolean Delete(Long Id);
} 
