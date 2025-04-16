package legend.example.project_api_legend.Controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.LoginHelper.CinemaHelper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class CinemaApiController {
      @PostMapping(value = CinemaHelper.Url.Upload,consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> handleUploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(LZGlobalHelper.Text.pathFolderImage +fileName));
            return ResponseEntity.ok("upload successfuly");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
     }

     @PostMapping(CinemaHelper.Url.Create)
     public String postMethodName(@RequestBody String entity) {
         //TODO: process POST request
         
         return entity;
     }
     
}
