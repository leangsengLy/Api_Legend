package legend.example.project_api_legend.DataModel.Hall;

import legend.example.project_api_legend.DataModel.BaseFilterDataModel;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HallFilterDataModel extends BaseFilterDataModel {
    private Long Id;
    private Boolean IsActive;
}

