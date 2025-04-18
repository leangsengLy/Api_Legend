package legend.example.project_api_legend.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    private Long Id;
    private String Name;
    private String EnglishName;
    private Long Qty;
    private Double Price;
    private String ImagePath;
    private String Localhost;
    private String Database;
    private String CreateBy;
    private String UpdateBy;
    private Integer RecordCount;
    private Date CreateDate;
    private Date UpdateDate;
}
