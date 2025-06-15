package legend.example.project_api_legend.DataModel.Address.Commune;

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
public class CommuneDataModel extends BaseDataModel{
    private Long Id;
    private String EnglishName; 
    private String Name;
    private Long districtId;
    private Long CountryId;
}
