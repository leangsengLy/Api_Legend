package legend.example.project_api_legend.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileData {
    private String fileName;
    private String fileType;
    private String folderName;
    private String Base64Data;
    public StatusMessage UploadFile(UploadFileDataModel request){
        try {
           Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String formatTime = formatter.format(date);
            // Validate the request
            if (request.getFileName() == null || request.getFileName().isEmpty())  return LZGlobalHelper.Message.DataInvalid.setDetail("File name is required.");
            if (request.getBase64Data() == null || request.getBase64Data().isEmpty()) return LZGlobalHelper.Message.DataInvalid.setDetail("File data is required."); 

            // Decode Base64 string to byte array
            byte[] fileBytes;
            try {
                fileBytes = Base64.getDecoder().decode(request.getBase64Data());
            } catch (IllegalArgumentException e) {
                return LZGlobalHelper.Message.DataInvalid.setDetail("Invalid Base64 data."); 
            }
            System.out.println(request.getFolderName());
            String pathForImage = LZGlobalHelper.Text.pathFolderImage;
            if(request.getFolderName()!=null) pathForImage+="/"+this.folderName+"/";
            // Optional: Save the byte array to a file
            Path uploadPath = Paths.get(pathForImage + formatTime+"_"+request.getFileName());
            Files.createDirectories(uploadPath.getParent()); // Create directory if it doesn't exist
            Files.write(uploadPath, fileBytes);
            
            
            // Optional: Process the byte array (e.g., save to database, manipulate, etc.)
            return LZGlobalHelper.Message.Successfuly.setDetail(formatTime+"_"+ request.getFileName());
        } catch (IOException e) {
            return LZGlobalHelper.Message.DataInvalid.setDetail("Failed to upload file: "+e.getMessage()); 
        }
    }

    public static String deleteImage(String filename,String folderName) {
        try {
            // Construct the file path
            Path filePath = Paths.get(LZGlobalHelper.Text.pathFolderImage+"/"+folderName+"/", filename);

            // Check if the file exists
            if (Files.exists(filePath)) {
                // Delete the file
                Files.delete(filePath);
                return "File deleted successfully: " + filename;
            } else {
                return "File not found: " + filename;
            }
        } catch (Exception e) {
            return "Error deleting file: " + e.getMessage();
        }
    }
    public static String getFileNameByPath(String path){
        List<String> fileName = Arrays.asList(path.split("/"));
        int size = Arrays.asList(path.split("/")).size();
        return fileName.get(size-1);
    }
}
