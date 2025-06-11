package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.DataModel.UserProfile.UserProfileFilterDataModel;
import legend.example.project_api_legend.Dto.LZModuleFood.FoodDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import legend.example.project_api_legend.Helper.LZModuleFood.FoodHelper;
import legend.example.project_api_legend.Helper.LZModuleSetting.UserProfileHelper;
import legend.example.project_api_legend.Interface.UserProfileService;
import legend.example.project_api_legend.Repository.LZUserProfileRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@AllArgsConstructor
public class UserProfileApiController {
    private UserProfileService userProfileService;
    private LZUserProfileRepository lzUserProfileRepository;
    @PostMapping(UserProfileHelper.URL.List)
    public ResponseEntity<?> Info(@RequestBody UserProfileFilterDataModel filter ) {
       try{
            if(filter.getLoginId()<1 || filter.getLoginId()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field LoginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.getUserByLoginId(filter.getLoginId());
            return new ResponseEntity<>(res,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping(UserProfileHelper.URL.UpdateName)
    public ResponseEntity<?> UpdateName(@RequestParam String name,@RequestParam Long Id) {
        try{
            if(name=="" || name.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field name is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateName(name,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(UserProfileHelper.URL.UploadImage)
    public ResponseEntity<?> UploadImage(@RequestBody UserProfileDataModel model ) {
        try{
             if(model.getId()<1 || model.getId()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field LoginId is required!"),HttpStatus.BAD_REQUEST);
             var find = lzUserProfileRepository.findById(model.getId());
             if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("User profile not found!"),HttpStatus.BAD_REQUEST);
            if(model.getUploadFileDataModel()!=null){
                String Folder = model.getType()=="Profile"?UserProfileHelper.Folder.Profile:UserProfileHelper.Folder.Cover;
            try{
                UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), Folder, model.getUploadFileDataModel().getBase64Data());
                model.getUploadFileDataModel().setFolderName(Folder);
                 StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setProfileImagePath("/Image/"+Folder+"/"+fileName.getDetail());
                    }
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        String data = userProfileService.UploadImage(model);
        return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      @PostMapping(UserProfileHelper.URL.UploadCoverImage)
    public ResponseEntity<?> UploadCoverImage(@RequestBody UserProfileDataModel model ) {
        try{
             if(model.getId()<1 || model.getId()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field LoginId is required!"),HttpStatus.BAD_REQUEST);
             var find = lzUserProfileRepository.findById(model.getId());
             if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("User profile not found!"),HttpStatus.BAD_REQUEST);
            if(model.getUploadFileDataModel()!=null){
            try{
                UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), UserProfileHelper.Folder.Cover, model.getUploadFileDataModel().getBase64Data());
                model.getUploadFileDataModel().setFolderName(UserProfileHelper.Folder.Cover);
                 StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setProfileImagePath("/Image/"+UserProfileHelper.Folder.Cover+"/"+fileName.getDetail());
                    }
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        String data = userProfileService.UploadCoverImage(model);
        return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping(UserProfileHelper.URL.DeleteImage)
    public ResponseEntity<?> DeleteImage(@RequestParam Long Id) {
        try{
            if(Id<1 || Id==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field LoginId is required!"),HttpStatus.BAD_REQUEST);
            // if(Type!="Profile" && Type!="Cover") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Type is invalid!"),HttpStatus.BAD_REQUEST);
            var find = lzUserProfileRepository.findById(Id);
             if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("User profile not found!"),HttpStatus.BAD_REQUEST);
              String data = userProfileService.DeleteImage(Id);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.DeleteImageCover)
    public ResponseEntity<?> DeleteImageCover(@RequestParam Long Id) {
        try{
            if(Id<1 || Id==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field LoginId is required!"),HttpStatus.BAD_REQUEST);
            // if(Type!="Profile" && Type!="Cover") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Type is invalid!"),HttpStatus.BAD_REQUEST);
            var find = lzUserProfileRepository.findById(Id);
             if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("User profile not found!"),HttpStatus.BAD_REQUEST);
              String data = userProfileService.DeleteCoverImage(Id);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdatePhone)
    public ResponseEntity<?> UpdatePhone(@RequestParam String phone,@RequestParam Long Id) {
        try{
            if(phone=="" || phone.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field phone is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            if(phone.length()>10)  return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field phone number should be 10length!"),HttpStatus.BAD_REQUEST);
            if(!phone.matches("^[0-9]+$")){
                return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Phone number we need only number!"),HttpStatus.BAD_REQUEST);
            }
            var res = userProfileService.UpdatePhone(phone,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateDesc)
    public ResponseEntity<?> UpdateDescription(@RequestParam String Description,@RequestParam Long Id) {
        try{
            if(Description=="" || Description.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Description is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Id is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateDescription(Description,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateAddress)
    public ResponseEntity<?> UpdateAddress(@RequestParam String address,@RequestParam Long Id) {
        try{
            if(address=="" || address.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field address is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateAddress(address,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateCareer)
    public ResponseEntity<?> UpdateCareer(@RequestParam String major,@RequestParam Long Id) {
        try{
            if(major=="" || major.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field major is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateCareer(major,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateCodeProgram)
    public ResponseEntity<?> UpdateCodeProgram(@RequestParam String codeProgram,@RequestParam Long Id) {
        try{
            if(codeProgram=="" || codeProgram.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field codeProgram is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateCodeProgram(codeProgram,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateEnglishName)
    public ResponseEntity<?> UpdateEnglishName(@RequestParam String englishName,@RequestParam Long Id) {
        try{
            if(englishName=="" || englishName.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Engish Name is required!"),HttpStatus.BAD_REQUEST);
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateEnglishName(englishName,Id);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
