package legend.example.project_api_legend.DataModel.Food;

import legend.example.project_api_legend.DataModel.BaseFilterDataModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodFilterDataModel extends BaseFilterDataModel {
    public Long Id;
    public String Name;
    public Double Price;
}
