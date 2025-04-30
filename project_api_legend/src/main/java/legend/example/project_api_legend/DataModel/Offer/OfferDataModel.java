package legend.example.project_api_legend.DataModel.Offer;

import java.util.Date;

import legend.example.project_api_legend.DataModel.UploadFileDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDataModel {
    private Long Id;
    private String Label;
    private String Detail;
    private String pathImage;
    private UploadFileDataModel uploadFileDataModel;
    private String Database;
    private String CreateBy;
    private Date CreateDate;
    private String UpdateBy;
    private Date UpdateDate;
}
