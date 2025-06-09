package legend.example.project_api_legend.Interface;

import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.Dto.LZModuleSetting.UserProfileDto;

public interface UserProfileService {
    // UserProfileDto getUserProfileInfo(String loginId);
    String UpdateName(String name,Long Id);
    String UpdateEnglishName(String englishName,Long Id);
    String UpdateAddress(String address,Long Id);
    String UpdatePhone(String phone,Long Id);
    String UpdateDescription(String desciption,Long Id);
    String UpdateCareer(String career,Long Id);
    String UpdateCodeProgram(String codeProgram,Long Id);
    String UploadImage(UserProfileDataModel model);
    UserProfileDto getUserByLoginId(Long Id);
}
