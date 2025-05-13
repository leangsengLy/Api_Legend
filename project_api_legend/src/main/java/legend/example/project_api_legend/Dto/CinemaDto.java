package legend.example.project_api_legend.Dto;

import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CinemaDto {
    public Long Id;
    public String Name;
    public String Code;
    public String Localhost;
    public String EnglishName;
    public String PathImage;
    public String Address;
    public LocalTime StartTime;
    public LocalTime EndTime;
    public String LatMap;
    public String LongMap;
    public int RecordCount;
    public String Database;
    public String CreateBy;
    public String UpdateBy;
    public Date UpdateDate;
    public Date CreateDate;
}
