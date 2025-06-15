package legend.example.project_api_legend.DataModel.Address.Country;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDataModel extends BaseDataModel{
    private Long Id;
    private String Code;
    private String EnglishName; 
    private String Name;
    private String ImagePath;
    private UploadFileDataModel uploadFileDataModel;
}
