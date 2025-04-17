package legend.example.project_api_legend.DataModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDataModel {
    private String fileName; // Name of the file
    private String folderName; 
    private String fileType; // MIME type (e.g., "image/jpeg")
    private String base64Data; // Base64-encoded file data
}
