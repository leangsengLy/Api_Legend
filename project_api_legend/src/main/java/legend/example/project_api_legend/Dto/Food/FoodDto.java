package legend.example.project_api_legend.Dto.Food;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class FoodDto {
    public Long Id;
    public String Name;
    public String EnglishName;
    public Long Qty;
    public Double Price;
    public String PathImage;
    public String Localhost;
    public String Database;
    public String CreateBy;
    public String UpdateBy;
    public Integer RecordCount;
    public Date CreateDate;
    public Date UpdateDate;
}
