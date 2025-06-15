package legend.example.project_api_legend.DataModel.Address.District;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDataModel extends BaseDataModel{
    private Long Id;
    private String Code;
    private Long CountryId;
    private Long ProvinceId;
    private String Name;
    private String EnglishName; 
}
