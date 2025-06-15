package legend.example.project_api_legend.Interface;

import java.util.List;

import legend.example.project_api_legend.DataModel.Food.FoodDataModel;
import legend.example.project_api_legend.DataModel.Food.FoodFilterDataModel;
import legend.example.project_api_legend.Dto.Food.FoodDetailDto;
import legend.example.project_api_legend.Dto.Food.FoodDto;

public interface FoodService {
    List<FoodDto> List(FoodFilterDataModel model);
    List<FoodDetailDto> ListDetail(FoodFilterDataModel model);
    FoodDto Create(FoodDataModel model);
    FoodDto Update(FoodDataModel model);
    Boolean Delete(Long Id);
}
