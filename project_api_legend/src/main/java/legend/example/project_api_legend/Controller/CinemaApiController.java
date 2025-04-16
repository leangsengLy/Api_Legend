package legend.example.project_api_legend.Controller;

import java.io.File;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import legend.example.project_api_legend.DataModel.Cinema.CinemaDataModel;
import legend.example.project_api_legend.Dto.CinemaDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.LoginHelper.CinemaHelper;
import legend.example.project_api_legend.Interface.CinemaService;
import legend.example.project_api_legend.Model.LZCinema;
import legend.example.project_api_legend.Repository.LZCinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


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
            return ResponseEntity.ok("upload successfuly");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

     @PostMapping(CinemaHelper.Url.Create)
     public  ResponseEntity<?>  Create(@RequestBody CinemaDataModel model) {
         try{
            if(model.getName()=="" || model.getAddress()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field name  is required."),HttpStatus.BAD_REQUEST);
            if(model.getCode()==null || model.getCode()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Code  is required."),HttpStatus.BAD_REQUEST);
            Boolean isExited = cinemaService.CheckCode(model.getCode());
            if(isExited) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already exited!"),HttpStatus.FOUND);
            CinemaDto cinema = cinemaService.Create(model);
            return new ResponseEntity<>(cinema,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }


     
     @PostMapping(CinemaHelper.Url.Update)
     public  ResponseEntity<?>  Update(@RequestBody CinemaDataModel model) {
         try{
            if(model.getName()=="" || model.getAddress()=="")return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field name or address is required."),HttpStatus.BAD_REQUEST);
            if(model.getId()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Id is required."),HttpStatus.BAD_REQUEST);
            if(model.getCode()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Code  is required."),HttpStatus.BAD_REQUEST);
            Optional<LZCinema> checkCinema = lzCinemaRepository.findById(model.getId());
            if(!checkCinema.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Cinema not found!"),HttpStatus.NOT_FOUND);
            CinemaDto cinema = cinemaService.Update(model);
            return new ResponseEntity<>(cinema,HttpStatus.OK);
         }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
     
}
