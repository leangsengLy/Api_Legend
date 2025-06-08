package legend.example.project_api_legend.DataModel.UserProfile;

import legend.example.project_api_legend.DataModel.BaseFilterDataModel;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserProfileFilterDataModel extends BaseFilterDataModel {
    private Long Id;
    private Long LoginId;
}
