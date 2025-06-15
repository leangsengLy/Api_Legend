package legend.example.project_api_legend.DataModel.Address.Country;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDataModel extends BaseDataModel{
    public Long Id;
    public String Code;
    public String EnglishName; 
    public String Name;
}
