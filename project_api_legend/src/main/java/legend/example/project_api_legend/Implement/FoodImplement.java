package legend.example.project_api_legend.Implement;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.*;
import legend.example.project_api_legend.DataModel.Food.FoodDataModel;
import legend.example.project_api_legend.DataModel.Food.FoodFilterDataModel;
import legend.example.project_api_legend.Dto.FoodDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.FoodService;
import legend.example.project_api_legend.Model.LZFood;
import legend.example.project_api_legend.Repository.LZFoodRepository;
import legend.example.project_api_legend.Specifications.FoodSpecification;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class FoodImplement implements FoodService{
    private LZFoodRepository lzFoodRepository;
    @Override
    public List<FoodDto> List(FoodFilterDataModel filter) {
        Specification<LZFood> listfood = Specification.where(FoodSpecification.ListFood(filter.getId()));
        if(filter.getPrice()>0){
            listfood = listfood.and(FoodSpecification.GetPriceBy(filter.getPrice()));
        } 
        List<LZFood> result = lzFoodRepository.findAll(listfood);
        return result.stream().map(val->MappingData(val, result.size())).toList();
    }
    @Override
    public FoodDto Create(FoodDataModel model) {
        LZFood food = new LZFood();
        food.setName(model.getName());
        food.setEnglishName(model.getEnglishName());
        food.setQty(model.getQty());
        food.setPrice(model.getPrice());
        food.setCinemaId(model.getCinemaId());
        food.setImagePath(model.getImagePath());
        food.setLocalhost(LZGlobalHelper.Text.localUrl);
        food.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        food.setCreateBy(LZGlobalHelper.Text.Admin);
        food.setCreateDate(LZGlobalHelper.Text.DateNow);
        food.setName(model.getName());
        lzFoodRepository.save(food);
        return MappingData(food,1);
    }
    @Override
    public Boolean Delete(Long Id) {
        lzFoodRepository.deleteById(Id);
        return true;
    }

    @Override
    public FoodDto Update(FoodDataModel model) {
        LZFood food = lzFoodRepository.findById(model.getId()).get();
        food.setName(model.getName());
        food.setEnglishName(model.getEnglishName());
        food.setUpdateBy(LZGlobalHelper.Text.Admin);
        food.setUpdateDate(LZGlobalHelper.Text.DateNow);
        food.setQty(model.getQty());
        food.setPrice(model.getPrice());
        food.setCinemaId(model.getCinemaId());
        lzFoodRepository.save(food);
        return MappingData(food,1);
    }

    public FoodDto MappingData(LZFood data,Integer RecordCount){
        FoodDto dto = new FoodDto();
        dto.setId(data.getId());
        dto.setName(data.getName());
        dto.setEnglishName(data.getEnglishName());
        dto.setQty(data.getQty());
        dto.setPrice(data.getPrice());
        dto.setImagePath(data.getImagePath());
        dto.setLocalhost(data.getLocalhost());
        dto.setDatabase(data.getDatabase());
        dto.setCreateBy(data.getCreateBy());
        dto.setCreateDate(data.getCreateDate());
        dto.setRecordCount(RecordCount);
        dto.setUpdateBy(data.getUpdateBy());
        dto.setUpdateDate(data.getUpdateDate());
        return dto;
    }
} 
