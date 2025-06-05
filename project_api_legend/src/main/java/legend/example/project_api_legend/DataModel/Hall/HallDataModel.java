package legend.example.project_api_legend.DataModel.Hall;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HallDataModel extends BaseDataModel {
    private Long Id;
    private String Code;
    private String Name;
    private String EnglishName;
    private Boolean IsActive;
    private Long TotalChairs;
}
