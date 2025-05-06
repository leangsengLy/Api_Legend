package legend.example.project_api_legend.DataModel.Movie.Movie;

import java.util.Date;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDataModel extends BaseDataModel {
    private Long Id;
    private Long MovieTypeId;
    private String Name;
    private String EnglishName;
    private Double Duration;
    private Date Release;
    private String ImagePath;
    private Date FromDate;
    private Date ToDate;
    private String UrlYT;
    private String Description;
    private UploadFileDataModel uploadFileDataModel;

}
