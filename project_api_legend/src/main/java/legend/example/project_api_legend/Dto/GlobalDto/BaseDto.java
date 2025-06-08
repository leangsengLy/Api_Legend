package legend.example.project_api_legend.Dto.GlobalDto;

import java.util.Date;

import lombok.Setter;
@Setter
public class BaseDto {
    public int RecordCount;
    public String CreateBy;
    public String UpdateBy;
    public Date CreateDate;
    public Date UpdateDate;
    public String Database;
}
