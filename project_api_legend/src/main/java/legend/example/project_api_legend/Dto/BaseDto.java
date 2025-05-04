package legend.example.project_api_legend.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BaseDto {
    public int RecordCount;
    public String CreateBy;
    public String UpdateBy;
    public Date CreateDate;
    public Date UpdateDate;
    public String Database;
}
