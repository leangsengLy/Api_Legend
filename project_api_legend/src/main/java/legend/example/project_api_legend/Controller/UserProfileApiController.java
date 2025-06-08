package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.UserProfile.UserProfileDataModel;
import legend.example.project_api_legend.Dto.LZModuleCinema.CinemaDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import legend.example.project_api_legend.Helper.LZModuleCinema.CinemaHelper;
import legend.example.project_api_legend.Helper.LZModuleSetting.UserProfileHelper;
import legend.example.project_api_legend.Interface.UserProfileService;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@NoArgsConstructor
public class UserProfileApiController {
    private UserProfileService userProfileService;
    // @PostMapping(UserProfileHelper.URL.List)
    // public ResponseEntity<?> List(@RequestBody UserProfileDataModel model ) {
    //    try{
    //         if(model.getName()=="" || model.getAddress()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field name  is required."),HttpStatus.BAD_REQUEST);
    //         if(model.getPhone1()==null || model.getPhone1()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Phone1  is required."),HttpStatus.BAD_REQUEST);
    //         if(model.getAddress()==null || model.getAddress()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Address  is required."),HttpStatus.BAD_REQUEST);
    //         if(model.getLoginId() < 1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field loginId not found!"),HttpStatus.BAD_REQUEST);
    //         if(model.getUploadFileDataModel()!=null){
    //             var folder = model.get
    //            try{
    //               UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), UserProfileHelper.Folder, model.getUploadFileDataModel().getBase64Data());
    //               model.getUploadFileDataModel().setFolderName(CinemaHelper.StrText.FolderBranch);
    //                StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
    //                 if(fileName.getStatus()!="error"){
    //                     model.setPathImage("/Image/"+CinemaHelper.StrText.FolderBranch+"/"+fileName.getDetail());
    //                 }
    //            }catch(Exception ex){
    //               return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    //            }
    //         }
    //         return new ResponseEntity<>(true,HttpStatus.OK);
    //      }catch(Exception e){
    //         return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    //      }
    // }

    @GetMapping(UserProfileHelper.URL.UpdateName)
    public ResponseEntity<?> UpdateName(@RequestParam String name,Long loginId) {
        try{
            if(name=="" || name.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field name is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateName(name,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdatePhone)
    public ResponseEntity<?> UpdatePhone(@RequestParam String phone,Long loginId) {
        try{
            if(phone=="" || phone.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field phone is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdatePhone(phone,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateDesc)
    public ResponseEntity<?> UpdateDescription(@RequestParam String Description,Long loginId) {
        try{
            if(Description=="" || Description.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Description is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateDescription(Description,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateAddress)
    public ResponseEntity<?> UpdateAddress(@RequestParam String address,Long loginId) {
        try{
            if(address=="" || address.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field address is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateAddress(address,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateCareer)
    public ResponseEntity<?> UpdateCareer(@RequestParam String major,Long loginId) {
        try{
            if(major=="" || major.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field major is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateCareer(major,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateCodeProgram)
    public ResponseEntity<?> UpdateCodeProgram(@RequestParam String codeProgram,Long loginId) {
        try{
            if(codeProgram=="" || codeProgram.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field codeProgram is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateCodeProgram(codeProgram,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateEnglishName)
    public ResponseEntity<?> UpdateEnglishName(@RequestParam String englishname,Long loginId) {
        try{
            if(englishname=="" || englishname.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Engish Name is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateEnglishName(englishname,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(UserProfileHelper.URL.UpdateEnglishAddress)
    public ResponseEntity<?> getMethodName(@RequestParam String enAddress,Long loginId) {
        try{
            if(enAddress=="" || enAddress.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field English Address is required!"),HttpStatus.BAD_REQUEST);
            if(loginId<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field loginId is required!"),HttpStatus.BAD_REQUEST);
            var res = userProfileService.UpdateEnglishAddress(enAddress,loginId);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
