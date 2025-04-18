package legend.example.project_api_legend.DataModel.Food;

import java.util.Date;

import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDataModel {
    private Long Id;
    private String Name;
    private String EnglishName;
    private String ImagePath;
    private Long Qty;
    private  Double Price;
    private Long CinemaId;
    private String CreateBy;
    private String UpdateBy;
    private String Database;
    private Date CreateDate;
    private Date UpdateDate;
    private UploadFileDataModel uploadFileDataModel;
} 
