package legend.example.project_api_legend.DataModel.Cinema;

import java.time.LocalTime;
import java.util.Date;

import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CinemaDataModel {
    private Long Id;
    private String Name;
    private String EnglishName;
    private String Code;
    private String Localhost;
    private String Address;
    private String PathImage;
    private LocalTime StartTime;
    private LocalTime EndTime;
    private UploadFileDataModel uploadFileDataModel;
    private String LatMap;
    private String LongMap;
    private String Createby;
    private String UpdateBy;
    private Date UpdateDate;
    private Date CreateDate;
    private String Database;

}
