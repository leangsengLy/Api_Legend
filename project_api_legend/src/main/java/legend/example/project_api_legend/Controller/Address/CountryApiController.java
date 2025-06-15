package legend.example.project_api_legend.Controller.Address;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Address.Country.CountryDataModel;
import legend.example.project_api_legend.DataModel.Address.Country.CountryFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import legend.example.project_api_legend.Helper.Address.CountryHelper;
import legend.example.project_api_legend.Helper.Cinema.CinemaHelper;
import legend.example.project_api_legend.Interface.Address.CountryService;
import legend.example.project_api_legend.Repository.Address.LZCountryRepository;
import lombok.AllArgsConstructor;
import java.util.*;
@RestController
@AllArgsConstructor
public class CountryApiController {
    private CountryService countryService;
    private LZCountryRepository lzCountryRepository;
    @PostMapping(CountryHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody CountryFilterDataModel filter){
        try{
                var list = countryService.List(filter);
                return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PostMapping(CountryHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody CountryDataModel model){
        try{
            var msg = "";
            if(model.getName()=="" || model.getName()==null) msg ="name";
            else if(model.getCode()=="" || model.getCode()==null) msg ="code";
            else if(model.getCreateBy()=="" || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase()=="" || model.getDatabase()==null) msg ="database";
            if(msg!="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
            boolean isExistedCode = countryService.CheckCode(model.getCode(), 0L, model.getDatabase());
            if(isExistedCode) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already existed"),HttpStatus.NOT_FOUND);
            if(model.getUploadFileDataModel()!=null){
               try{
                  UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), CountryHelper.StrText.folderCountry, model.getUploadFileDataModel().getBase64Data());
                  model.getUploadFileDataModel().setFolderName(CountryHelper.StrText.folderCountry);
                   StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setImagePath("/Image/"+CountryHelper.StrText.folderCountry+"/"+fileName.getDetail());
                    }
               }catch(Exception ex){
                  return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
               }
            }
            var data = countryService.Create(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            UploadFileData.deleteImage(UploadFileData.getFileNameByPath(model.getImagePath()), CountryHelper.StrText.folderCountry);
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @PostMapping(CountryHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody CountryDataModel model){
        try{
            var msg = "";
            if(model.getName().isEmpty() || model.getName()==null) msg ="name";
            else if(model.getCode().isEmpty() || model.getCode()==null) msg ="code";
            else if(model.getCreateBy().isEmpty() || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase().isEmpty() || model.getDatabase()==null) msg ="database";
            else if(model.getId()<1 || model.getId()==null) msg ="id";
            if(!msg.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
             boolean isExistedCode = countryService.CheckCode(model.getCode(), 0L, model.getDatabase());
            if(isExistedCode) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already existed"),HttpStatus.NOT_FOUND);
            var find = lzCountryRepository.findById(model.getId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            if(model.getUploadFileDataModel()!=null){
                if(find.get().getImagePath()==null){
                    try{
                        UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), CountryHelper.StrText.folderCountry, model.getUploadFileDataModel().getBase64Data());
                        model.getUploadFileDataModel().setFolderName(CountryHelper.StrText.folderCountry);
                        StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                            if(fileName.getStatus()!="error"){
                                model.setImagePath("/Image/"+CountryHelper.StrText.folderCountry+"/"+fileName.getDetail());
                            }
                    }catch(Exception ex){
                        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
                    }
                }
               
            }
                var data = countryService.Update(model);
                return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping(CountryHelper.Url.CheckCode)
    public ResponseEntity<?> CheckCode(@RequestParam String code,@RequestParam Long id,@RequestParam String database){
        try{
            if(code.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field code is required"),HttpStatus.BAD_REQUEST); 
            boolean isExistedCode = countryService.CheckCode(code, id, database);
                return new ResponseEntity<>(isExistedCode,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping(CountryHelper.Url.Delete)
    public ResponseEntity<?> Delete(@RequestParam Long id){
        try{
                if(id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field id is required"),HttpStatus.BAD_REQUEST); 
                var find = lzCountryRepository.findById(id);
                if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
                if(find.get().getImagePath()!="") UploadFileData.deleteImage(UploadFileData.getFileNameByPath(find.get().getImagePath()), CountryHelper.StrText.folderCountry);
                var isSussess = countryService.Delete(id);
                return new ResponseEntity<>(isSussess,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
