package legend.example.project_api_legend.DataModel.Login;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDataModel{
    private Long Id;
    private String Password;
    private String OldPassword;
    private String Email;
    public String CreateBy;
    public Date CreateDate;
    public String UpdateBy;
    public Date UpdateDate;
    public String Database;
    
}
