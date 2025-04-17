package legend.example.project_api_legend.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.FileUploadRequest;
import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FileUploadController {
    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadFile(@RequestBody FileUploadRequest request) {
        try {
            // Validate the request
            if (request.getFileName() == null || request.getFileName().isEmpty()) {
                return new ResponseEntity<>("File name is required.", HttpStatus.BAD_REQUEST);
            }
            if (request.getBase64Data() == null || request.getBase64Data().isEmpty()) {
                return new ResponseEntity<>("File data is required.", HttpStatus.BAD_REQUEST);
            }

            // Decode Base64 string to byte array
            byte[] fileBytes;
            try {
                fileBytes = Base64.getDecoder().decode(request.getBase64Data());
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Invalid Base64 data.", HttpStatus.BAD_REQUEST);
            }

            // Optional: Save the byte array to a file
            Path uploadPath = Paths.get(LZGlobalHelper.Text.pathFolderImage+"/branch/" + request.getFileName());
            Files.createDirectories(uploadPath.getParent()); // Create directory if it doesn't exist
            Files.write(uploadPath, fileBytes);

            // Optional: Process the byte array (e.g., save to database, manipulate, etc.)
            String message = "File uploaded successfully: " + request.getFileName() + ", Size: " + fileBytes.length + " bytes";

            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("api/cinema/upload_image")
    public ResponseEntity<?> UploadImage(@RequestBody UploadFileDataModel request) {
        try{
            UploadFileData upload = new UploadFileData(request.getFileName(), request.getFileType(), request.getFolderName(), request.getBase64Data());
            return new ResponseEntity<>(upload.UploadFile(request),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
