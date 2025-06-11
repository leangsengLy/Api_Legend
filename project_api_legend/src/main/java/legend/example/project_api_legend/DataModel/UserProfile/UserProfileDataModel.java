package legend.example.project_api_legend.DataModel.UserProfile;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserProfileDataModel extends BaseDataModel {
    private Long Id;
    private Long LoginId;
    private String CoverImagePath;
    private String ProfileImagePath;
    private String Name;
    private String EnglishName;
    private String Description;
    private String Type;
    private String Major;
    private String CareerDescription;
    private String ExperienceDescription;
    private String Phone1;
    private String Phone2;
    private String Address;
    private UploadFileDataModel uploadFileDataModel;
    private String EnglishAddress;
}
