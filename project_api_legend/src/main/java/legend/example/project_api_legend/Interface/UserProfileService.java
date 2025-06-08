package legend.example.project_api_legend.Interface;

import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.Dto.LZModuleSetting.UserProfileDto;

public interface UserProfileService {
    UserProfileDto getUserProfileInfo(String loginId);
    String UpdateName(String name,Long loginId);
    String UpdateEnglishName(String englishName,Long loginId);
    String UpdateAddress(String address,Long loginId);
    String UpdateEnglishAddress(String englishAddress,Long loginId);
    String UpdatePhone(String phone,Long loginId);
    String UpdateDescription(String desciption,Long loginId);
    String UpdateCareer(String career,Long loginId);
    String UpdateCodeProgram(String codeProgram,Long loginId);
}
