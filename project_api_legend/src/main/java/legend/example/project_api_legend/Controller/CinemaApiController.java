package legend.example.project_api_legend.Controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Cinema.CinemaDataModel;
import legend.example.project_api_legend.DataModel.Cinema.CinemaFilterDataModel;
import legend.example.project_api_legend.Dto.LZModuleCinema.CinemaDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import legend.example.project_api_legend.Helper.LZModuleCinema.CinemaHelper;
import legend.example.project_api_legend.Helper.LZModuleFood.FoodHelper;
import legend.example.project_api_legend.Helper.LZModuleSetting.MovieHelper;
import legend.example.project_api_legend.Interface.CinemaService;
import legend.example.project_api_legend.Model.LZCinema;
import legend.example.project_api_legend.Model.LZFood;
import legend.example.project_api_legend.Repository.LZCinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@AllArgsConstructor
public class CinemaApiController {
    private CinemaService cinemaService;
    private LZCinemaRepository lzCinemaRepository;
      @PostMapping(value = CinemaHelper.Url.Upload,consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> handleUploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(LZGlobalHelper.Text.pathFolderImage +fileName));
            return ResponseEntity.ok("upload Successfuly");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
     @PostMapping(CinemaHelper.Url.List)
     public ResponseEntity<?> List(@RequestBody CinemaFilterDataModel filter){
      try{
         java.util.List<CinemaDto> list = cinemaService.List(filter);
         return new ResponseEntity<>(list, HttpStatus.OK);
      }catch(Exception ex){
         return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
     }

     @PostMapping(CinemaHelper.Url.Create)
     public ResponseEntity<?> Create(@RequestBody CinemaDataModel model) {
         try{
            if(model.getName()=="" || model.getAddress()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field name  is required."),HttpStatus.BAD_REQUEST);
            if(model.getCode()==null || model.getCode()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Code  is required."),HttpStatus.BAD_REQUEST);
            Boolean isExited = cinemaService.CheckCode(model.getCode());
            if(isExited) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already exited!"),HttpStatus.FOUND);
            if(model.getUploadFileDataModel()!=null){
               try{
                  UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), CinemaHelper.StrText.FolderBranch, model.getUploadFileDataModel().getBase64Data());
                  model.getUploadFileDataModel().setFolderName(CinemaHelper.StrText.FolderBranch);
                   StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setPathImage("/Image/"+CinemaHelper.StrText.FolderBranch+"/"+fileName.getDetail());
                    }
               }catch(Exception ex){
                  return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
               }
            }
            CinemaDto cinema = cinemaService.Create(model);
            return new ResponseEntity<>(cinema,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
      @PostMapping(CinemaHelper.Url.Update)
     public ResponseEntity<?> Update(@RequestBody CinemaDataModel model) {
         try{
            if(model.getName()=="" || model.getAddress()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field name  is required."),HttpStatus.BAD_REQUEST);
            if(model.getCode()==null || model.getCode()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Code  is required."),HttpStatus.BAD_REQUEST);
            Boolean isExited = cinemaService.CheckCode(model.getCode());
            if(isExited) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already exited!"),HttpStatus.FOUND);
            if(model.getUploadFileDataModel()!=null){
               try{
                  UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), CinemaHelper.StrText.FolderBranch, model.getUploadFileDataModel().getBase64Data());
                  model.getUploadFileDataModel().setFolderName(CinemaHelper.StrText.FolderBranch);
                   StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setPathImage("/Image/"+CinemaHelper.StrText.FolderBranch+"/"+fileName.getDetail());
                    }
               }catch(Exception ex){
                  return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
               }
            }
            var find = lzCinemaRepository.findById(model.getId());
            if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("cinema not found!"),HttpStatus.BAD_REQUEST);
            CinemaDto cinema = cinemaService.Update(model);
            return new ResponseEntity<>(cinema,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @PostMapping(CinemaHelper.Url.DeleteImage)
     public ResponseEntity<?> Update(Long Id) {
         try{
            Boolean isDeleteSuccess = false;
            if(Id < 1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Id is required."),HttpStatus.BAD_REQUEST);
            Optional<LZCinema> find = lzCinemaRepository.findById(Id);
            if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Data not found!"),HttpStatus.BAD_REQUEST);
            if(find.get().getPathImage()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Currently this Cinema doesn't have image for branch!"),HttpStatus.BAD_REQUEST);
           try{
               isDeleteSuccess = cinemaService.DeleteImage(Id);
           }catch(Exception ex){
               return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
           }
            return new ResponseEntity<>(isDeleteSuccess,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @GetMapping(CinemaHelper.Url.CheckCode)
     public ResponseEntity<?> CheckExistedCode(@RequestParam String param) {
         try{
            if(param=="" || param==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Please input param also."),HttpStatus.BAD_REQUEST);
            var isExisted = cinemaService.CheckCode(param);
            return new ResponseEntity<>(isExisted,HttpStatus.OK);
         }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
     

     @PostMapping(CinemaHelper.Url.UploadImage)
     public ResponseEntity<?> UploadImage(@RequestBody CinemaDataModel model) {
         try{
            if(model.getId() == null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Id is required."),HttpStatus.BAD_REQUEST);
            Optional<LZCinema> find = lzCinemaRepository.findById(model.getId());
            if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Data not found!"),HttpStatus.BAD_REQUEST);
            if(find.get().getPathImage()!=null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Cinema have image already!"),HttpStatus.BAD_REQUEST);
            if(model.getUploadFileDataModel()!=null){
               try{
                  UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), CinemaHelper.StrText.FolderBranch, model.getUploadFileDataModel().getBase64Data());
                  model.getUploadFileDataModel().setFolderName(CinemaHelper.StrText.FolderBranch);
                   StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        find.get().setPathImage("/Image/"+CinemaHelper.StrText.FolderBranch+"/"+fileName.getDetail());
                    }
                  lzCinemaRepository.save(find.get());
               }catch(Exception ex){
                  return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
               }
            }
            return new ResponseEntity<>("Upload image success!",HttpStatus.OK);
         }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
     
     @GetMapping(CinemaHelper.Url.Delete)
    public ResponseEntity<?> Delete(Long Id) {
       try{
        if(Id<1)return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The field id is required!"),HttpStatus.BAD_REQUEST);
        Optional<LZCinema> cinema = lzCinemaRepository.findById(Id);
        if(!cinema.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Cinema not found!"),HttpStatus.BAD_REQUEST);
        Boolean isDeleteSuccess = cinemaService.Delete(Id);
        if(isDeleteSuccess) {
            try{
                List<String> fileName = Arrays.asList(cinema.get().getPathImage().split("/"));
                Integer LastIndex = fileName.size()-1;
                UploadFileData.deleteImage(fileName.get(LastIndex), CinemaHelper.StrText.FolderBranch);
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(isDeleteSuccess ? "Delete cinema was successfully!":"",HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
