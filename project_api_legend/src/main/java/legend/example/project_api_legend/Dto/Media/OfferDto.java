package legend.example.project_api_legend.Dto.Media;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
public class OfferDto {
    public Long Id;
    public String PathImage;
    public String Label;
    public String Detail;
    public String Database;
    public String CreateBy;
    public Date CreateDate;
    public String UpdateBy;
    public Date UpdateDate;
    public int RecordCount;
}
