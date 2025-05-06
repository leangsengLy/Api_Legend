package legend.example.project_api_legend.Dto;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
public class MovieDto {
    public Long Id;
    public Long MovieTypeId;
    public String Name;
    public String EnglishName;
    public Double Duration;
    public Date Release;
    public String ImagePath;
    public Date FromDate;
    public Date ToDate;
    public String UrlYT;
    public String Description;
    public int RecordCount;
    public String CreateBy;
    public String UpdateBy;
    public Date CreateDate;
    public Date UpdateDate;
    public String Database;
}
