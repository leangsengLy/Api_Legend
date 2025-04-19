package legend.example.project_api_legend.DataModel.Food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodFilterDataModel {
    private Long Id;
    private String Name;
    private Double minPrice;
    private Double maxPrice;
    private String category;
}
