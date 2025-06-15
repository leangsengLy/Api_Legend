package legend.example.project_api_legend.Implement.Setting;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.Dto.Setting.UserProfileDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Setting.UserProfileHelper;
import legend.example.project_api_legend.Interface.UserProfileService;
import legend.example.project_api_legend.MappingData.Setting.UserProfileMapping;
import legend.example.project_api_legend.Model.LZUserProfile;
import legend.example.project_api_legend.Repository.LZUserProfileRepository;
import legend.example.project_api_legend.Specifications.Setting.UserProfileSpecification;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileImplement implements UserProfileService  {

        private LZUserProfileRepository lZUserProfileRepository;
        public UserProfileDto getUserByLoginId(Long loginId) {
           Specification<LZUserProfile> info = Specification.where(UserProfileSpecification.GetDataByLoginId(loginId));
           var data = lZUserProfileRepository.findAll(info);
           if(data.size()==0){
                    var save = new LZUserProfile();
                    save.setLOGIN_ID(loginId);
                    save.setNAME("Your Name");
                    save.setEN_NAME("Your English Name");
                    save.setDESCRIPTION("Description default I need to make a something to you.");
                    save.setPHONE1("099855645");
                    save.setMAJOR("IT developer or doctor teacher");
                    save.setEXPERIENCE_DESC("language that you have learn or study...");
                    save.setADDRESS("Address default tek tla.");
                    save.setCREATED_BY(LZGlobalHelper.Text.Admin);
                    save.setCREATED_DATE(LZGlobalHelper.Text.DateNow);
                    save.setDATABASE(LZGlobalHelper.Text.GlobalDatabase);
                    lZUserProfileRepository.save(save);
                    return UserProfileMapping.MappingDto(save, 1);
           }
           return UserProfileMapping.MappingDto(data.get(0), 1);
        }

        public String UploadImage(UserProfileDataModel model){
             var data = lZUserProfileRepository.findById(model.getId()).get();
            data.setPROFILE_IMG_PATH(model.getProfileImagePath());
            lZUserProfileRepository.save(data);
            return data.getPROFILE_IMG_PATH();
        }
           public String UploadCoverImage(UserProfileDataModel model){
             var data = lZUserProfileRepository.findById(model.getId()).get();
            data.setCV_IMG_PATH(model.getProfileImagePath());
            lZUserProfileRepository.save(data);
            return data.getCV_IMG_PATH();
        }
        public String DeleteImage(Long Id){
            var profile = lZUserProfileRepository.findById(Id).get();
            if(profile.getPROFILE_IMG_PATH()!=""){
                List<String> fileName = Arrays.asList(profile.getPROFILE_IMG_PATH().split("/"));
                Integer LastIndex = fileName.size()-1;
                UploadFileData.deleteImage(fileName.get(LastIndex), UserProfileHelper.Folder.Profile);
            }
            profile.setPROFILE_IMG_PATH(null);
              lZUserProfileRepository.save(profile);
            return "Delete image successfuly";
        }
           public String DeleteCoverImage(Long Id){
            var profile = lZUserProfileRepository.findById(Id).get();
            if(profile.getCV_IMG_PATH()!=""){
                List<String> fileName = Arrays.asList(profile.getCV_IMG_PATH().split("/"));
                Integer LastIndex = fileName.size()-1;
                UploadFileData.deleteImage(fileName.get(LastIndex), UserProfileHelper.Folder.Cover);
            }
            profile.setCV_IMG_PATH(null);
              lZUserProfileRepository.save(profile);
            return "Delete image successfuly";
        }
       
        public String UpdateName(String name,Long Id) {
           var data = lZUserProfileRepository.findById(Id).get();
           data.setNAME(name);
            lZUserProfileRepository.save(data);
            return data.getNAME();
        }
        public String UpdateAddress(String address,Long Id) {
             var data = lZUserProfileRepository.findById(Id).get();
            data.setADDRESS(address);
            lZUserProfileRepository.save(data);
            return data.getADDRESS();
        }
        public String UpdateCareer(String career,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
            data.setMAJOR(career);
            lZUserProfileRepository.save(data);
            return data.getMAJOR();
        }
        public String UpdateCodeProgram(String codeProgram,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
            data.setEXPERIENCE_DESC(codeProgram);
            lZUserProfileRepository.save(data);
            return data.getEXPERIENCE_DESC();
        }
        public String UpdateDescription(String desciption,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
           data.setDESCRIPTION(desciption);
                lZUserProfileRepository.save(data);
            return data.getDESCRIPTION();
        }
       
        public String UpdateEnglishName(String englishName,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
            data.setEN_NAME(englishName);
            lZUserProfileRepository.save(data);
            return data.getEN_NAME();
        }
        public String UpdatePhone(String phone,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
            data.setPHONE1(phone);
            lZUserProfileRepository.save(data);
            return data.getPHONE1();
        }

}
