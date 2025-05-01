package legend.example.project_api_legend.DataModel.Offer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferFilterDataModel {
    private Long Id;
    private String PathImage;
    private String Search;
    private String Label;
    private String Detail;
    private String Database;
    private String CreateBy;
    private Date CreateDate;
    private String UpdateBy;
    private Date UpdateDate;
}
