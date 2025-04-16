package legend.example.project_api_legend.Dto;

import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDto {
    private Long Id;
    private String Name;
    private String Code;
    private String EnglishName;
    private String PathImage;
    private String Address;
    private LocalTime StartTime;
    private LocalTime EndTime;
    private String LatMap;
    private String LongMap;
    private Long RecordCount;
    private String Database;
    private String CreateBy;
    private String UpdateBy;
    private Date UpdateDate;
    private Date CreateDate;
}
