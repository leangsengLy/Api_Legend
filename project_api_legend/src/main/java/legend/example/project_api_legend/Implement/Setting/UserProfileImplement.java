package legend.example.project_api_legend.Implement.Setting;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.Dto.LZModuleSetting.UserProfileDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.UserProfileService;
import legend.example.project_api_legend.MappingData.LZModuleSetting.UserProfileMapping;
import legend.example.project_api_legend.Model.LZUserProfile;
import legend.example.project_api_legend.Repository.LZUserProfileRepository;
import legend.example.project_api_legend.Specifications.LZModuleSetting.UserProfileSpecification;
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

        public String UpdateName(String name,Long Id) {
           var data = lZUserProfileRepository.findById(Id).get();
           data.setNAME(name);
            lZUserProfileRepository.save(data);
            return data.getNAME();
        }
        public String UpdateAddress(String address,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setADDRESS(address);
            lZUserProfileRepository.save(db);
            return db.getADDRESS();
        }
        public String UpdateCareer(String career,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setMAJOR(career);
            lZUserProfileRepository.save(db);
            return db.getMAJOR();
        }
        public String UpdateCodeProgram(String codeProgram,Long loginId) {
            var db = new LZUserProfile(); 
               db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setEXPERIENCE_DESC(codeProgram);
            lZUserProfileRepository.save(db);
            return db.getEXPERIENCE_DESC();
        }
        public String UpdateDescription(String desciption,Long Id) {
            var data = lZUserProfileRepository.findById(Id).get();
           data.setDESCRIPTION(desciption);
                lZUserProfileRepository.save(data);
            return data.getDESCRIPTION();
        }
       
        public String UpdateEnglishName(String englishName,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setEN_NAME(englishName);
            lZUserProfileRepository.save(db);
            return db.getEN_NAME();
        }
        public String UpdatePhone(String phone,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
            db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setPHONE1(phone);
            lZUserProfileRepository.save(db);
            return db.getPHONE1();
        }

}
