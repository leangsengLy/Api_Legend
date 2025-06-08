package legend.example.project_api_legend.Implement.Setting;

import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.Dto.LZModuleSetting.UserProfileDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.UserProfileService;
import legend.example.project_api_legend.Model.LZUserProfile;
import legend.example.project_api_legend.Repository.LZUserProfileRepository;
@Service
public class UserProfileImplement implements UserProfileService  {
        private LZUserProfileRepository lZUserProfileRepository;
        @Override
        public UserProfileDto getUserProfileInfo(String loginId) {
        // TODO Auto-generated method stub
        return null;
        }
        @Override
        public String UpdateName(String name,Long loginId) {
           var db = new LZUserProfile(); 
            db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setLOGIN_ID(loginId);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setNAME(name);
            lZUserProfileRepository.save(db);
            return db.getNAME();
        }
        @Override
        public String UpdateAddress(String address,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setADDRESS(address);
            lZUserProfileRepository.save(db);
            return db.getADDRESS();
        }
        @Override
        public String UpdateCareer(String career,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setMAJOR(career);
            lZUserProfileRepository.save(db);
            return db.getMAJOR();
        }
        @Override
        public String UpdateCodeProgram(String codeProgram,Long loginId) {
            var db = new LZUserProfile(); 
               db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setEXPERIENCE_DESC(codeProgram);
            lZUserProfileRepository.save(db);
            return db.getEXPERIENCE_DESC();
        }
        @Override
        public String UpdateDescription(String desciption,Long loginId) {
            var db = new LZUserProfile(); 
            db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setDESCRIPTION(desciption);
            lZUserProfileRepository.save(db);
            return db.getDESCRIPTION();
        }
        @Override
        public String UpdateEnglishAddress(String englishAddress,Long loginId) {
            var db = new LZUserProfile(); 
               db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setEN_ADDRESS(englishAddress);
            lZUserProfileRepository.save(db);
            return db.getEN_ADDRESS();
        }
        @Override
        public String UpdateEnglishName(String englishName,Long loginId) {
            var db = new LZUserProfile(); 
             db.setLOGIN_ID(loginId);
             db.setUPDATED_BY(LZGlobalHelper.Text.Admin);
            db.setUPDATED_DATE(LZGlobalHelper.Text.DateNow);
            db.setEN_NAME(englishName);
            lZUserProfileRepository.save(db);
            return db.getEN_NAME();
        }
        @Override
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
