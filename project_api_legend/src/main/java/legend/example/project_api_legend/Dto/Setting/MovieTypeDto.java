package legend.example.project_api_legend.Dto.Setting;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class MovieTypeDto {
    public Long Id;
    public String Name;
    public String EnglishName;
    public int RecordCount;
    public String CreateBy;
    public String UpdateBy;
    public Date CreateDate;
    public Date UpdateDate;
    public String Database;
}
